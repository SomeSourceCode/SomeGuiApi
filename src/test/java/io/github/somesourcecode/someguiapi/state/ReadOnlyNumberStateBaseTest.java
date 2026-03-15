package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReadOnlyNumberStateBaseTest {

	/* *************************************************************** *
	 *                            EQUALITY                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly compare equality using epsilon across all variants")
	void testIsEqualTo() {
		final DoubleState baseState = new SimpleDoubleState(10.0);
		final IntegerState stateToCompare = new SimpleIntegerState(10);

		final ReadOnlyBooleanState intResult = baseState.isEqualTo(10, 0.0001);
		final ReadOnlyBooleanState longResult = baseState.isEqualTo(10L, 0.0001);
		final ReadOnlyBooleanState floatResult = baseState.isEqualTo(10.0f, 0.0001);
		final ReadOnlyBooleanState doubleResult = baseState.isEqualTo(10.0, 0.0001);
		final ReadOnlyBooleanState stateResult = baseState.isEqualTo(stateToCompare, 0.0001);

		assertTrue(intResult.get(), "isEqualTo(int) failed to calculate correct initial value");
		assertTrue(longResult.get(), "isEqualTo(long) failed to calculate correct initial value");
		assertTrue(floatResult.get(), "isEqualTo(float) failed to calculate correct initial value");
		assertTrue(doubleResult.get(), "isEqualTo(double) failed to calculate correct initial value");
		assertTrue(stateResult.get(), "isEqualTo(ObservableValue) failed to calculate correct initial value");

		baseState.set(10.5);

		assertFalse(intResult.get(), "isEqualTo(int) failed to update when base state changed");
		assertFalse(longResult.get(), "isEqualTo(long) failed to update when base state changed");
		assertFalse(floatResult.get(), "isEqualTo(float) failed to update when base state changed");
		assertFalse(doubleResult.get(), "isEqualTo(double) failed to update when base state changed");
		assertFalse(stateResult.get(), "isEqualTo(ObservableValue) failed to update when base state changed");

		stateToCompare.set(11);

		assertFalse(stateResult.get(), "isEqualTo(ObservableValue) failed to update when argument state changed");
	}

	/* *************************************************************** *
	 *                            LESS THAN                            *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly identify if state is strictly less than a value")
	void testIsLessThan() {
		final DoubleState baseState = new SimpleDoubleState(10.5);
		final IntegerState stateToCompare = new SimpleIntegerState(20);

		final ReadOnlyBooleanState intResult = baseState.isLessThan(20);
		final ReadOnlyBooleanState longResult = baseState.isLessThan(20L);
		final ReadOnlyBooleanState floatResult = baseState.isLessThan(20.0f);
		final ReadOnlyBooleanState doubleResult = baseState.isLessThan(20.0);
		final ReadOnlyBooleanState stateResult = baseState.isLessThan(stateToCompare);

		assertTrue(intResult.get(), "isLessThan(int) failed to calculate correct initial value");
		assertTrue(longResult.get(), "isLessThan(long) failed to calculate correct initial value");
		assertTrue(floatResult.get(), "isLessThan(float) failed to calculate correct initial value");
		assertTrue(doubleResult.get(), "isLessThan(double) failed to calculate correct initial value");
		assertTrue(stateResult.get(), "isLessThan(ObservableValue) failed to calculate correct initial value");

		baseState.set(25.0);

		assertFalse(intResult.get(), "isLessThan(int) failed to update when base state changed");
		assertFalse(longResult.get(), "isLessThan(long) failed to update when base state changed");
		assertFalse(floatResult.get(), "isLessThan(float) failed to update when base state changed");
		assertFalse(doubleResult.get(), "isLessThan(double) failed to update when base state changed");
		assertFalse(stateResult.get(), "isLessThan(ObservableValue) failed to update when base state changed");

		stateToCompare.set(5);

		assertFalse(stateResult.get(), "isLessThan(ObservableValue) failed to update when argument state changed");
	}

	@Test
	@DisplayName("Should correctly identify if state is less than or equal to a value")
	void testIsLessThanOrEqualTo() {
		final DoubleState baseState = new SimpleDoubleState(20.0);
		final IntegerState stateToCompare = new SimpleIntegerState(20);

		final ReadOnlyBooleanState intResult = baseState.isLessThanOrEqualTo(20);
		final ReadOnlyBooleanState longResult = baseState.isLessThanOrEqualTo(20L);
		final ReadOnlyBooleanState floatResult = baseState.isLessThanOrEqualTo(20.0f);
		final ReadOnlyBooleanState doubleResult = baseState.isLessThanOrEqualTo(20.0);
		final ReadOnlyBooleanState stateResult = baseState.isLessThanOrEqualTo(stateToCompare);

		assertTrue(intResult.get(), "isLessThanOrEqualTo(int) failed on the inclusive boundary");
		assertTrue(longResult.get(), "isLessThanOrEqualTo(long) failed on the inclusive boundary");
		assertTrue(floatResult.get(), "isLessThanOrEqualTo(float) failed on the inclusive boundary");
		assertTrue(doubleResult.get(), "isLessThanOrEqualTo(double) failed on the inclusive boundary");
		assertTrue(stateResult.get(), "isLessThanOrEqualTo(ObservableValue) failed on the inclusive boundary");

		baseState.set(25.0);

		assertFalse(intResult.get(), "isLessThanOrEqualTo(int) failed to update when base state changed");
		assertFalse(longResult.get(), "isLessThanOrEqualTo(long) failed to update when base state changed");
		assertFalse(floatResult.get(), "isLessThanOrEqualTo(float) failed to update when base state changed");
		assertFalse(doubleResult.get(), "isLessThanOrEqualTo(double) failed to update when base state changed");
		assertFalse(stateResult.get(), "isLessThanOrEqualTo(ObservableValue) failed to update when base state changed");

		stateToCompare.set(30);

		assertTrue(stateResult.get(), "isLessThanOrEqualTo(ObservableValue) failed to update when argument state changed");
	}

	/* *************************************************************** *
	 *                          GREATER THAN                           *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly identify if state is strictly greater than a value")
	void testIsGreaterThan() {
		final DoubleState baseState = new SimpleDoubleState(25.0);
		final IntegerState stateToCompare = new SimpleIntegerState(20);

		final ReadOnlyBooleanState intResult = baseState.isGreaterThan(20);
		final ReadOnlyBooleanState longResult = baseState.isGreaterThan(20L);
		final ReadOnlyBooleanState floatResult = baseState.isGreaterThan(20.0f);
		final ReadOnlyBooleanState doubleResult = baseState.isGreaterThan(20.0);
		final ReadOnlyBooleanState stateResult = baseState.isGreaterThan(stateToCompare);

		assertTrue(intResult.get(), "isGreaterThan(int) failed to calculate correct initial value");
		assertTrue(longResult.get(), "isGreaterThan(long) failed to calculate correct initial value");
		assertTrue(floatResult.get(), "isGreaterThan(float) failed to calculate correct initial value");
		assertTrue(doubleResult.get(), "isGreaterThan(double) failed to calculate correct initial value");
		assertTrue(stateResult.get(), "isGreaterThan(ObservableValue) failed to calculate correct initial value");

		baseState.set(10.5);

		assertFalse(intResult.get(), "isGreaterThan(int) failed to update when base state changed");
		assertFalse(longResult.get(), "isGreaterThan(long) failed to update when base state changed");
		assertFalse(floatResult.get(), "isGreaterThan(float) failed to update when base state changed");
		assertFalse(doubleResult.get(), "isGreaterThan(double) failed to update when base state changed");
		assertFalse(stateResult.get(), "isGreaterThan(ObservableValue) failed to update when base state changed");

		stateToCompare.set(5);

		assertTrue(stateResult.get(), "isGreaterThan(ObservableValue) failed to update when argument state changed");
	}

	@Test
	@DisplayName("Should correctly identify if state is greater than or equal to a value")
	void testIsGreaterThanOrEqualTo() {
		final DoubleState baseState = new SimpleDoubleState(20.0);
		final IntegerState stateToCompare = new SimpleIntegerState(20);

		final ReadOnlyBooleanState intResult = baseState.isGreaterThanOrEqualTo(20);
		final ReadOnlyBooleanState longResult = baseState.isGreaterThanOrEqualTo(20L);
		final ReadOnlyBooleanState floatResult = baseState.isGreaterThanOrEqualTo(20.0f);
		final ReadOnlyBooleanState doubleResult = baseState.isGreaterThanOrEqualTo(20.0);
		final ReadOnlyBooleanState stateResult = baseState.isGreaterThanOrEqualTo(stateToCompare);

		assertTrue(intResult.get(), "isGreaterThanOrEqualTo(int) failed on the inclusive boundary");
		assertTrue(longResult.get(), "isGreaterThanOrEqualTo(long) failed on the inclusive boundary");
		assertTrue(floatResult.get(), "isGreaterThanOrEqualTo(float) failed on the inclusive boundary");
		assertTrue(doubleResult.get(), "isGreaterThanOrEqualTo(double) failed on the inclusive boundary");
		assertTrue(stateResult.get(), "isGreaterThanOrEqualTo(ObservableValue) failed on the inclusive boundary");

		baseState.set(10.5);

		assertFalse(intResult.get(), "isGreaterThanOrEqualTo(int) failed to update when base state changed");
		assertFalse(longResult.get(), "isGreaterThanOrEqualTo(long) failed to update when base state changed");
		assertFalse(floatResult.get(), "isGreaterThanOrEqualTo(float) failed to update when base state changed");
		assertFalse(doubleResult.get(), "isGreaterThanOrEqualTo(double) failed to update when base state changed");
		assertFalse(stateResult.get(), "isGreaterThanOrEqualTo(ObservableValue) failed to update when base state changed");

		stateToCompare.set(5);

		assertTrue(stateResult.get(), "isGreaterThanOrEqualTo(ObservableValue) failed to update when argument state changed");
	}

	/* *************************************************************** *
	 *                             BETWEEN                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly identify if state is inclusively between two values")
	void testIsBetween() {
		final DoubleState baseState = new SimpleDoubleState(15.0);
		final IntegerState minState = new SimpleIntegerState(10);
		final IntegerState maxState = new SimpleIntegerState(20);

		final ReadOnlyBooleanState intResult = baseState.isBetween(10, 20);
		final ReadOnlyBooleanState longResult = baseState.isBetween(10L, 20L);
		final ReadOnlyBooleanState floatResult = baseState.isBetween(10.0f, 20.0f);
		final ReadOnlyBooleanState doubleResult = baseState.isBetween(10.0, 20.0);
		final ReadOnlyBooleanState stateResult = baseState.isBetween(minState, maxState);

		assertTrue(intResult.get(), "isBetween(int) failed to calculate correct initial value");
		assertTrue(longResult.get(), "isBetween(long) failed to calculate correct initial value");
		assertTrue(floatResult.get(), "isBetween(float) failed to calculate correct initial value");
		assertTrue(doubleResult.get(), "isBetween(double) failed to calculate correct initial value");
		assertTrue(stateResult.get(), "isBetween(ObservableValue) failed to calculate correct initial value");

		baseState.set(20.0);
		assertTrue(intResult.get(), "isBetween(int) failed on the inclusive boundary");

		baseState.set(25.0);
		assertFalse(intResult.get(), "isBetween(int) failed to update when base state changed to out of bounds");
		assertFalse(stateResult.get(), "isBetween(ObservableValue) failed to update when base state changed to out of bounds");

		maxState.set(30);
		assertTrue(stateResult.get(), "isBetween(ObservableValue) failed to update when argument max state changed");
	}

	@Test
	@DisplayName("Should correctly identify if state is strictly between two values")
	void testIsBetweenExclusive() {
		final DoubleState baseState = new SimpleDoubleState(15.0);
		final IntegerState minState = new SimpleIntegerState(10);
		final IntegerState maxState = new SimpleIntegerState(20);

		final ReadOnlyBooleanState intResult = baseState.isBetweenExclusive(10, 20);
		final ReadOnlyBooleanState longResult = baseState.isBetweenExclusive(10L, 20L);
		final ReadOnlyBooleanState floatResult = baseState.isBetweenExclusive(10.0f, 20.0f);
		final ReadOnlyBooleanState doubleResult = baseState.isBetweenExclusive(10.0, 20.0);
		final ReadOnlyBooleanState stateResult = baseState.isBetweenExclusive(minState, maxState);

		assertTrue(intResult.get(), "isBetweenExclusive(int) failed to calculate correct initial value");
		assertTrue(longResult.get(), "isBetweenExclusive(long) failed to calculate correct initial value");
		assertTrue(floatResult.get(), "isBetweenExclusive(float) failed to calculate correct initial value");
		assertTrue(doubleResult.get(), "isBetweenExclusive(double) failed to calculate correct initial value");
		assertTrue(stateResult.get(), "isBetweenExclusive(ObservableValue) failed to calculate correct initial value");

		baseState.set(20.0);
		assertFalse(intResult.get(), "isBetweenExclusive(int) incorrectly returned true on the exclusive boundary");
		assertFalse(stateResult.get(), "isBetweenExclusive(ObservableValue) incorrectly returned true on the exclusive boundary");

		maxState.set(25);
		assertTrue(stateResult.get(), "isBetweenExclusive(ObservableValue) failed to update when argument max state changed");
	}

	/* *************************************************************** *
	 *                           SIGN CHECKS                           *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly identify zero, non-zero, positive, and negative states")
	void testSignChecks() {
		final DoubleState baseState = new SimpleDoubleState(0.0);

		final ReadOnlyBooleanState isZero = baseState.isZero();
		final ReadOnlyBooleanState isNotZero = baseState.isNotZero();
		final ReadOnlyBooleanState isPositive = baseState.isPositive();
		final ReadOnlyBooleanState isNegative = baseState.isNegative();

		assertTrue(isZero.get(), "isZero() failed on 0.0");
		assertFalse(isNotZero.get(), "isNotZero() failed on 0.0");
		assertTrue(isPositive.get(), "isPositive() failed on 0.0 (should be inclusive)");
		assertFalse(isNegative.get(), "isNegative() failed on 0.0");

		baseState.set(-5.5);

		assertFalse(isZero.get(), "isZero() failed to update on negative value");
		assertTrue(isNotZero.get(), "isNotZero() failed to update on negative value");
		assertFalse(isPositive.get(), "isPositive() failed to update on negative value");
		assertTrue(isNegative.get(), "isNegative() failed to update on negative value");

		baseState.set(5.5);

		assertFalse(isZero.get(), "isZero() failed to update on positive value");
		assertTrue(isNotZero.get(), "isNotZero() failed to update on positive value");
		assertTrue(isPositive.get(), "isPositive() failed to update on positive value");
		assertFalse(isNegative.get(), "isNegative() failed to update on positive value");
	}

	/* *************************************************************** *
	 *                     MATH OPERATIONS (STATE)                     *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly perform math operations defined in base using other states")
	void testMathOperationsStates() {
		final DoubleState baseState = new SimpleDoubleState(10.0);
		final IntegerState argState = new SimpleIntegerState(5);

		final ReadOnlyNumberState<?> addResult = baseState.add(argState);
		final ReadOnlyNumberState<?> subResult = baseState.subtract(argState);
		final ReadOnlyNumberState<?> mulResult = baseState.multiply(argState);
		final ReadOnlyNumberState<?> minResult = baseState.min(argState);
		final ReadOnlyNumberState<?> maxResult = baseState.max(argState);

		final IntegerState minClamp = new SimpleIntegerState(0);
		final IntegerState maxClamp = new SimpleIntegerState(8);
		final ReadOnlyNumberState<?> clampResult = baseState.clamp(minClamp, maxClamp);

		assertEquals(15.0, addResult.get().doubleValue(), "add(ObservableValue) failed to calculate correct initial value");
		assertEquals(5.0, subResult.get().doubleValue(), "subtract(ObservableValue) failed to calculate correct initial value");
		assertEquals(50.0, mulResult.get().doubleValue(), "multiply(ObservableValue) failed to calculate correct initial value");
		assertEquals(5.0, minResult.get().doubleValue(), "min(ObservableValue) failed to calculate correct initial value");
		assertEquals(10.0, maxResult.get().doubleValue(), "max(ObservableValue) failed to calculate correct initial value");
		assertEquals(8.0, clampResult.get().doubleValue(), "clamp(ObservableValue, ObservableValue) failed to calculate correct initial value");

		baseState.set(20.0);

		assertEquals(25.0, addResult.get().doubleValue(), "add(ObservableValue) failed to update when base state changed");
		assertEquals(15.0, subResult.get().doubleValue(), "subtract(ObservableValue) failed to update when base state changed");
		assertEquals(100.0, mulResult.get().doubleValue(), "multiply(ObservableValue) failed to update when base state changed");
		assertEquals(5.0, minResult.get().doubleValue(), "min(ObservableValue) failed to update when base state changed");
		assertEquals(20.0, maxResult.get().doubleValue(), "max(ObservableValue) failed to update when base state changed");
		assertEquals(8.0, clampResult.get().doubleValue(), "clamp(ObservableValue, ObservableValue) failed to update when base state changed");

		argState.set(10);
		maxClamp.set(25);

		assertEquals(30.0, addResult.get().doubleValue(), "add(ObservableValue) failed to update when argument state changed");
		assertEquals(10.0, subResult.get().doubleValue(), "subtract(ObservableValue) failed to update when argument state changed");
		assertEquals(200.0, mulResult.get().doubleValue(), "multiply(ObservableValue) failed to update when argument state changed");
		assertEquals(10.0, minResult.get().doubleValue(), "min(ObservableValue) failed to update when argument state changed");
		assertEquals(20.0, maxResult.get().doubleValue(), "max(ObservableValue) failed to update when argument state changed");
		assertEquals(20.0, clampResult.get().doubleValue(), "clamp(ObservableValue, ObservableValue) failed to update when argument state changed");
	}

	/* *************************************************************** *
	 *                            DIVISION                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly divide by primitives and states, returning a double state")
	void testDivide() {
		final DoubleState baseState = new SimpleDoubleState(10.0);
		final IntegerState divisorState = new SimpleIntegerState(2);

		final ReadOnlyDoubleState intResult = baseState.divide(4);
		final ReadOnlyDoubleState longResult = baseState.divide(4L);
		final ReadOnlyDoubleState floatResult = baseState.divide(4.0f);
		final ReadOnlyDoubleState doubleResult = baseState.divide(4.0);
		final ReadOnlyDoubleState stateResult = baseState.divide(divisorState);

		assertEquals(2.5, intResult.get(), "divide(int) failed to calculate correct initial value");
		assertEquals(2.5, longResult.get(), "divide(long) failed to calculate correct initial value");
		assertEquals(2.5, floatResult.get(), "divide(float) failed to calculate correct initial value");
		assertEquals(2.5, doubleResult.get(), "divide(double) failed to calculate correct initial value");
		assertEquals(5.0, stateResult.get(), "divide(ObservableValue) failed to calculate correct initial value");

		baseState.set(20.0);

		assertEquals(5.0, intResult.get(), "divide(int) failed to update when base state changed");
		assertEquals(5.0, longResult.get(), "divide(long) failed to update when base state changed");
		assertEquals(5.0, floatResult.get(), "divide(float) failed to update when base state changed");
		assertEquals(5.0, doubleResult.get(), "divide(double) failed to update when base state changed");
		assertEquals(10.0, stateResult.get(), "divide(ObservableValue) failed to update when base state changed");

		divisorState.set(4);

		assertEquals(5.0, stateResult.get(), "divide(ObservableValue) failed to update when argument state changed");
	}

	/* *************************************************************** *
	 *                            ROUNDING                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly perform standard, ceiling, and floor rounding")
	void testRounding() {
		final DoubleState baseState = new SimpleDoubleState(2.3);

		final ReadOnlyIntegerState roundResult = baseState.round();
		final ReadOnlyIntegerState roundUpResult = baseState.roundUp();
		final ReadOnlyIntegerState roundDownResult = baseState.roundDown();
		final ReadOnlyIntegerState floorResult = baseState.floor();
		final ReadOnlyIntegerState ceilResult = baseState.ceiling();

		assertEquals(2, roundResult.get(), "round() failed to calculate correct initial value");
		assertEquals(3, roundUpResult.get(), "roundUp() failed to calculate correct initial value");
		assertEquals(2, roundDownResult.get(), "roundDown() failed to calculate correct initial value");
		assertEquals(2, floorResult.get(), "floor() failed to calculate correct initial value");
		assertEquals(3, ceilResult.get(), "ceiling() failed to calculate correct initial value");

		baseState.set(-2.3);

		assertEquals(-2, roundResult.get(), "round() failed to update on negative numbers");
		assertEquals(-3, roundUpResult.get(), "roundUp() failed to update on negative numbers");
		assertEquals(-2, roundDownResult.get(), "roundDown() failed to update on negative numbers");
		assertEquals(-3, floorResult.get(), "floor() failed to update on negative numbers");
		assertEquals(-2, ceilResult.get(), "ceiling() failed to update on negative numbers");
	}

	/* *************************************************************** *
	 *                           CONVERSION                            *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly transform and proxy internal values to explicit types")
	void testConversions() {
		final DoubleState baseState = new SimpleDoubleState(10.8);

		final ReadOnlyIntegerState intState = baseState.asIntegerState();
		final ReadOnlyLongState longState = baseState.asLongState();
		final ReadOnlyFloatState floatState = baseState.asFloatState();
		final ReadOnlyDoubleState doubleState = baseState.asDoubleState();

		assertEquals(10, intState.get(), "asIntegerState() failed to calculate correct initial value");
		assertEquals(10L, longState.get(), "asLongState() failed to calculate correct initial value");
		assertEquals(10.8f, floatState.get(), "asFloatState() failed to calculate correct initial value");
		assertEquals(10.8, doubleState.get(), "asDoubleState() failed to calculate correct initial value");

		baseState.set(5.2);

		assertEquals(5, intState.get(), "asIntegerState() failed to update when base state changed");
		assertEquals(5L, longState.get(), "asLongState() failed to update when base state changed");
		assertEquals(5.2f, floatState.get(), "asFloatState() failed to update when base state changed");
		assertEquals(5.2, doubleState.get(), "asDoubleState() failed to update when base state changed");
	}

}
