package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.ref.WeakReference;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectTest {

	/* *************************************************************** *
	 *                        MEMORY LIFECYCLE                         *
	 * *************************************************************** */

	private WeakReference<ReadOnlyBooleanState> createIsolatedReflection(SimpleBooleanState dependency) {
		final ReadOnlyBooleanState reflection = Reflect.createBooleanReflection(dependency::get, dependency);
		return new WeakReference<>(reflection);
	}

	@Test
	@DisplayName("Should unregister observers when reflection is garbage collected")
	void testCleanerUnregistersObserver() throws InterruptedException {
		final SimpleBooleanState dependency = new SimpleBooleanState(false);
		final WeakReference<ReadOnlyBooleanState> weakReflection = createIsolatedReflection(dependency);

		int attempts = 0;
		while (weakReflection.get() != null && attempts < 50) {
			System.gc();
			Thread.sleep(10);
			attempts++;
		}

		assertNull(weakReflection.get(), "Reflection was not garbage collected");

		assertDoesNotThrow(() -> {
			dependency.set(true);
		}, "Cleaner failed to unregister the observer, causing a dead reference invocation");
	}

	/* *************************************************************** *
	 *                       BOOLEAN AGGREGATORS                       *
	 * *************************************************************** */

	static Stream<Arguments> provideBooleanScenarios() {
		return Stream.of(
				// {state1, state2, state3}, Expected Any, Expected All, Expected None, Expected Consensus
				Arguments.of(new boolean[]{false, false, false}, false, false, true, true),
				Arguments.of(new boolean[]{true, false, false}, true, false, false, false),
				Arguments.of(new boolean[]{true, true, true}, true, true, false, true)
		);
	}

	@ParameterizedTest(name = "[{index}] inputs: {0} => any: {1}, all: {2}, none: {3}, consensus: {4}")
	@MethodSource("provideBooleanScenarios")
	@DisplayName("Should correctly evaluate any, all, none, and consensus across permutations")
	void testBasicBooleanAggregators(boolean[] inputs, boolean expectedAny, boolean expectedAll, boolean expectedNone, boolean expectedConsensus) {
		final SimpleBooleanState[] states = new SimpleBooleanState[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			states[i] = new SimpleBooleanState(inputs[i]);
		}

		assertEquals(expectedAny, Reflect.any(states).get(), "any() failed");
		assertEquals(expectedAll, Reflect.all(states).get(), "all() failed");
		assertEquals(expectedNone, Reflect.none(states).get(), "none() failed");
		assertEquals(expectedConsensus, Reflect.consensus(states).get(), "consensus() failed");
	}

	/* *************************************************************** *
	 *                  BOOLEAN COUNTING AGGREGATORS                   *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly evaluate counting operations and update dynamically")
	void testCountingBooleanAggregators() {
		final SimpleBooleanState stateOne = new SimpleBooleanState(true);
		final SimpleBooleanState stateTwo = new SimpleBooleanState(true);
		final SimpleBooleanState stateThree = new SimpleBooleanState(false);

		final ReadOnlyBooleanState exactlyTwo = Reflect.exactly(2, stateOne, stateTwo, stateThree);
		final ReadOnlyBooleanState atLeastTwo = Reflect.atLeast(2, stateOne, stateTwo, stateThree);
		final ReadOnlyBooleanState atMostOne = Reflect.atMost(1, stateOne, stateTwo, stateThree);
		final ReadOnlyBooleanState majority = Reflect.majority(stateOne, stateTwo, stateThree);

		assertTrue(exactlyTwo.get(), "exactly() failed to calculate correct initial value");
		assertTrue(atLeastTwo.get(), "atLeast() failed to calculate correct initial value");
		assertFalse(atMostOne.get(), "atMost() failed to calculate correct initial value");
		assertTrue(majority.get(), "majority() failed to calculate correct initial value");

		stateThree.set(true);

		assertFalse(exactlyTwo.get(), "exactly() failed to update when dependency changed");
		assertTrue(atLeastTwo.get(), "atLeast() failed to update when dependency changed");
		assertFalse(atMostOne.get(), "atMost() failed to update when dependency changed");
		assertTrue(majority.get(), "majority() failed to update when dependency changed");
	}

	@Test
	@DisplayName("Should correctly evaluate counting operations using dynamic state counters")
	void testDynamicCountingAggregators() {
		final SimpleIntegerState requiredCount = new SimpleIntegerState(2);
		final SimpleBooleanState stateOne = new SimpleBooleanState(true);
		final SimpleBooleanState stateTwo = new SimpleBooleanState(true);
		final SimpleBooleanState stateThree = new SimpleBooleanState(false);

		final ReadOnlyBooleanState exactlyState = Reflect.exactly(requiredCount, stateOne, stateTwo, stateThree);

		assertTrue(exactlyState.get(), "exactly(ObservableValue) failed to calculate correct initial value");

		stateThree.set(true);
		assertFalse(exactlyState.get(), "exactly(ObservableValue) failed to update when boolean dependency changed");

		requiredCount.set(3);
		assertTrue(exactlyState.get(), "exactly(ObservableValue) failed to update when target count dependency changed");
	}

	@Test
	@DisplayName("Should correctly evaluate tie conditions")
	void testTieAggregator() {
		final SimpleBooleanState stateOne = new SimpleBooleanState(true);
		final SimpleBooleanState stateTwo = new SimpleBooleanState(false);

		final ReadOnlyBooleanState tieState = Reflect.tie(stateOne, stateTwo);
		assertTrue(tieState.get(), "tie() failed to calculate correct initial value for an even split");

		stateTwo.set(true);
		assertFalse(tieState.get(), "tie() failed to update when dependency changed");
	}

	/* *************************************************************** *
	 *                       NUMBER AGGREGATORS                        *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly calculate min, max, mean, and median and update on change")
	void testNumberAggregators() {
		final SimpleDoubleState valOne = new SimpleDoubleState(10.0);
		final SimpleDoubleState valTwo = new SimpleDoubleState(20.0);
		final SimpleDoubleState valThree = new SimpleDoubleState(30.0);

		final ReadOnlyNumberState<?> minState = Reflect.min(valOne, valTwo, valThree);
		final ReadOnlyNumberState<?> maxState = Reflect.max(valOne, valTwo, valThree);
		final ReadOnlyDoubleState meanState = Reflect.mean(valOne, valTwo, valThree);
		final ReadOnlyNumberState<?> medianState = Reflect.median(valOne, valTwo, valThree);

		assertEquals(10.0, minState.get().doubleValue(), "min() failed to calculate correct initial value");
		assertEquals(30.0, maxState.get().doubleValue(), "max() failed to calculate correct initial value");
		assertEquals(20.0, meanState.get().doubleValue(), "mean() failed to calculate correct initial value");
		assertEquals(20.0, medianState.get().doubleValue(), "median() failed to calculate correct initial value");

		valOne.set(40.0);

		assertEquals(20.0, minState.get().doubleValue(), "min() failed to update when dependency changed");
		assertEquals(40.0, maxState.get().doubleValue(), "max() failed to update when dependency changed");
		assertEquals(30.0, meanState.get().doubleValue(), "mean() failed to update when dependency changed");
		assertEquals(30.0, medianState.get().doubleValue(), "median() failed to update when dependency changed");
	}

	@Test
	@DisplayName("Should correctly calculate median for even array lengths")
	void testMedianEvenLength() {
		final SimpleIntegerState valOne = new SimpleIntegerState(10);
		final SimpleIntegerState valTwo = new SimpleIntegerState(20);
		final SimpleIntegerState valThree = new SimpleIntegerState(30);
		final SimpleIntegerState valFour = new SimpleIntegerState(40);

		final ReadOnlyNumberState<?> medianState = Reflect.median(valOne, valTwo, valThree, valFour);

		assertEquals(25, medianState.get().intValue(), "median() failed to calculate correctly for even lengths");
	}

	/* *************************************************************** *
	 *                       STRING AGGREGATORS                        *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly join and concat strings and update on change")
	void testStringAggregators() {
		final SimpleStringState strOne = new SimpleStringState("Hello");
		final SimpleStringState strTwo = new SimpleStringState("World");
		final SimpleStringState delimiter = new SimpleStringState(" ");

		final ReadOnlyStringState staticJoinState = Reflect.join("-", strOne, strTwo);
		final ReadOnlyStringState dynamicJoinState = Reflect.join(delimiter, strOne, strTwo);
		final ReadOnlyStringState concatState = Reflect.concat(strOne, strTwo);

		assertEquals("Hello-World", staticJoinState.get(), "join(String) failed to calculate correct initial value");
		assertEquals("Hello World", dynamicJoinState.get(), "join(ObservableValue) failed to calculate correct initial value");
		assertEquals("HelloWorld", concatState.get(), "concat() failed to calculate correct initial value");

		strTwo.set("Java");

		assertEquals("Hello-Java", staticJoinState.get(), "join(String) failed to update when dependency changed");
		assertEquals("Hello Java", dynamicJoinState.get(), "join(ObservableValue) failed to update when dependency changed");
		assertEquals("HelloJava", concatState.get(), "concat() failed to update when dependency changed");

		delimiter.set(", ");

		assertEquals("Hello, Java", dynamicJoinState.get(), "join(ObservableValue) failed to update when delimiter changed");
	}

}
