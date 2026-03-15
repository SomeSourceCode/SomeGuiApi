package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReadOnlyStateBaseTest {

	/* *************************************************************** *
	 *                            EQUALITY                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly compare equality with raw values and update on change")
	void testIsEqualToObject() {
		final SimpleObjectState<String> baseState = new SimpleObjectState<>("Hello");

		final ReadOnlyBooleanState isEqualToResult = baseState.isEqualTo("Hello");
		final ReadOnlyBooleanState isNotEqualToResult = baseState.isNotEqualTo("Hello");

		assertTrue(isEqualToResult.get(), "isEqualTo(T) failed to calculate correct initial value");
		assertFalse(isNotEqualToResult.get(), "isNotEqualTo(T) failed to calculate correct initial value");

		baseState.set("World");

		assertFalse(isEqualToResult.get(), "isEqualTo(T) failed to update when base state changed");
		assertTrue(isNotEqualToResult.get(), "isNotEqualTo(T) failed to update when base state changed");
	}

	@Test
	@DisplayName("Should correctly compare equality with ObservableValues and update on change")
	void testIsEqualToState() {
		final SimpleObjectState<String> baseState = new SimpleObjectState<>("Hello");
		final SimpleObjectState<String> compareState = new SimpleObjectState<>("Hello");

		final ReadOnlyBooleanState isEqualToResult = baseState.isEqualTo(compareState);
		final ReadOnlyBooleanState isNotEqualToResult = baseState.isNotEqualTo(compareState);

		assertTrue(isEqualToResult.get(), "isEqualTo(ObservableValue) failed to calculate correct initial value");
		assertFalse(isNotEqualToResult.get(), "isNotEqualTo(ObservableValue) failed to calculate correct initial value");

		baseState.set("World");

		assertFalse(isEqualToResult.get(), "isEqualTo(ObservableValue) failed to update when base state changed");
		assertTrue(isNotEqualToResult.get(), "isNotEqualTo(ObservableValue) failed to update when base state changed");

		compareState.set("World");

		assertTrue(isEqualToResult.get(), "isEqualTo(ObservableValue) failed to update when argument state changed");
		assertFalse(isNotEqualToResult.get(), "isNotEqualTo(ObservableValue) failed to update when argument state changed");
	}

	/*******************************************************
	 * STRING CONVERSION                                   *
	 *******************************************************/

	@Test
	@DisplayName("Should correctly convert state to string and update on change")
	void testAsString() {
		final SimpleObjectState<Integer> baseState = new SimpleObjectState<>(42);
		final ReadOnlyStringState stringResult = baseState.asString();

		assertEquals("42", stringResult.get(), "asString() failed to calculate correct initial value");

		baseState.set(100);

		assertEquals("100", stringResult.get(), "asString() failed to update when base state changed");

		baseState.set(null);

		assertEquals("null", stringResult.get(), "asString() failed to handle null base state correctly");
	}

	/*******************************************************
	 * MAPPING                                             *
	 *******************************************************/

	@Test
	@DisplayName("Should correctly map to different state types and update on change")
	void testMapping() {
		final SimpleObjectState<String> baseState = new SimpleObjectState<>("Word");

		final ReadOnlyBooleanState booleanMap = baseState.mapToBoolean(str -> str.length() > 3);
		final ReadOnlyIntegerState integerMap = baseState.mapToInteger(String::length);
		final ReadOnlyLongState longMap = baseState.mapToLong(str -> (long) str.length());
		final ReadOnlyFloatState floatMap = baseState.mapToFloat(str -> (float) str.length());
		final ReadOnlyDoubleState doubleMap = baseState.mapToDouble(str -> (double) str.length());
		final ReadOnlyStringState stringMap = baseState.mapToString(String::toUpperCase);
		final ReadOnlyObjectState<Character> objectMap = baseState.mapToObject(str -> str.charAt(0));

		assertTrue(booleanMap.get(), "mapToBoolean() failed to calculate correct initial value");
		assertEquals(4, integerMap.get(), "mapToInteger() failed to calculate correct initial value");
		assertEquals(4L, longMap.get(), "mapToLong() failed to calculate correct initial value");
		assertEquals(4.0f, floatMap.get(), "mapToFloat() failed to calculate correct initial value");
		assertEquals(4.0, doubleMap.get(), "mapToDouble() failed to calculate correct initial value");
		assertEquals("WORD", stringMap.get(), "mapToString() failed to calculate correct initial value");
		assertEquals('W', objectMap.get(), "mapToObject() failed to calculate correct initial value");

		baseState.set("A");

		assertFalse(booleanMap.get(), "mapToBoolean() failed to update when base state changed");
		assertEquals(1, integerMap.get(), "mapToInteger() failed to update when base state changed");
		assertEquals(1L, longMap.get(), "mapToLong() failed to update when base state changed");
		assertEquals(1.0f, floatMap.get(), "mapToFloat() failed to update when base state changed");
		assertEquals(1.0, doubleMap.get(), "mapToDouble() failed to update when base state changed");
		assertEquals("A", stringMap.get(), "mapToString() failed to update when base state changed");
		assertEquals('A', objectMap.get(), "mapToObject() failed to update when base state changed");
	}

}
