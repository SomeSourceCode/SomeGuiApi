package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReadOnlyLongStateBaseTest {

	/* *************************************************************** *
	 *                            ADDITION                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly add primitive values to the base long state and update on change")
	void testAddPrimitives() {
		final LongState baseLongState = new SimpleLongState(10L);

		final ReadOnlyLongState longResult = baseLongState.add(5L);
		final ReadOnlyFloatState floatResult = baseLongState.add(5.5f);
		final ReadOnlyDoubleState doubleResult = baseLongState.add(5.5);

		assertEquals(15L, longResult.get(), "add(long) failed to calculate correct initial value");
		assertEquals(15.5f, floatResult.get(), "add(float) failed to calculate correct initial value");
		assertEquals(15.5, doubleResult.get(), "add(double) failed to calculate correct initial value");

		baseLongState.set(20L);

		assertEquals(25L, longResult.get(), "add(long) failed to update when base state changed");
		assertEquals(25.5f, floatResult.get(), "add(float) failed to update when base state changed");
		assertEquals(25.5, doubleResult.get(), "add(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                           SUBTRACTION                           *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly subtract primitive values from the base long state and update on change")
	void testSubtractPrimitives() {
		final LongState baseLongState = new SimpleLongState(10L);

		final ReadOnlyLongState longResult = baseLongState.subtract(5L);
		final ReadOnlyFloatState floatResult = baseLongState.subtract(5.5f);
		final ReadOnlyDoubleState doubleResult = baseLongState.subtract(5.5);

		assertEquals(5L, longResult.get(), "subtract(long) failed to calculate correct initial value");
		assertEquals(4.5f, floatResult.get(), "subtract(float) failed to calculate correct initial value");
		assertEquals(4.5, doubleResult.get(), "subtract(double) failed to calculate correct initial value");

		baseLongState.set(20L);

		assertEquals(15L, longResult.get(), "subtract(long) failed to update when base state changed");
		assertEquals(14.5f, floatResult.get(), "subtract(float) failed to update when base state changed");
		assertEquals(14.5, doubleResult.get(), "subtract(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                         MULTIPLICATION                          *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly multiply primitive values to the base long state and update on change")
	void testMultiplyPrimitives() {
		final LongState baseLongState = new SimpleLongState(10L);

		final ReadOnlyLongState longResult = baseLongState.multiply(5L);
		final ReadOnlyFloatState floatResult = baseLongState.multiply(5.5f);
		final ReadOnlyDoubleState doubleResult = baseLongState.multiply(5.5);

		assertEquals(50L, longResult.get(), "multiply(long) failed to calculate correct initial value");
		assertEquals(55.0f, floatResult.get(), "multiply(float) failed to calculate correct initial value");
		assertEquals(55.0, doubleResult.get(), "multiply(double) failed to calculate correct initial value");

		baseLongState.set(20L);

		assertEquals(100L, longResult.get(), "multiply(long) failed to update when base state changed");
		assertEquals(110.0f, floatResult.get(), "multiply(float) failed to update when base state changed");
		assertEquals(110.0, doubleResult.get(), "multiply(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                               MIN                               *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly find the minimum with primitive values and update on change")
	void testMinPrimitives() {
		final LongState baseLongState = new SimpleLongState(10L);

		final ReadOnlyLongState longResult = baseLongState.min(15L);
		final ReadOnlyFloatState floatResult = baseLongState.min(15.5f);
		final ReadOnlyDoubleState doubleResult = baseLongState.min(15.5);

		assertEquals(10L, longResult.get(), "min(long) failed to calculate correct initial value");
		assertEquals(10.0f, floatResult.get(), "min(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "min(double) failed to calculate correct initial value");

		baseLongState.set(20L);

		assertEquals(15L, longResult.get(), "min(long) failed to update when base state changed");
		assertEquals(15.5f, floatResult.get(), "min(float) failed to update when base state changed");
		assertEquals(15.5, doubleResult.get(), "min(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                               MAX                               *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly find the maximum with primitive values and update on change")
	void testMaxPrimitives() {
		final LongState baseLongState = new SimpleLongState(10L);

		final ReadOnlyLongState longResult = baseLongState.max(5L);
		final ReadOnlyFloatState floatResult = baseLongState.max(5.5f);
		final ReadOnlyDoubleState doubleResult = baseLongState.max(5.5);

		assertEquals(10L, longResult.get(), "max(long) failed to calculate correct initial value");
		assertEquals(10.0f, floatResult.get(), "max(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "max(double) failed to calculate correct initial value");

		baseLongState.set(0L);

		assertEquals(5L, longResult.get(), "max(long) failed to update when base state changed");
		assertEquals(5.5f, floatResult.get(), "max(float) failed to update when base state changed");
		assertEquals(5.5, doubleResult.get(), "max(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                              CLAMP                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly clamp the base long state within primitive bounds and update on change")
	void testClampPrimitives() {
		final LongState baseLongState = new SimpleLongState(10);

		final ReadOnlyLongState longResult = baseLongState.clamp(5L, 15L);
		final ReadOnlyFloatState floatResult = baseLongState.clamp(5.0f, 15.0f);
		final ReadOnlyDoubleState doubleResult = baseLongState.clamp(5.0, 15.0);

		assertEquals(10L, longResult.get(), "clamp(long) failed to calculate correct initial value");
		assertEquals(10.0f, floatResult.get(), "clamp(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "clamp(double) failed to calculate correct initial value");

		baseLongState.set(20L);

		assertEquals(15L, longResult.get(), "clamp(long) failed to update when base state changed");
		assertEquals(15.0f, floatResult.get(), "clamp(float) failed to update when base state changed");
		assertEquals(15.0, doubleResult.get(), "clamp(double) failed to update when base state changed");

		baseLongState.set(0L);

		assertEquals(5L, longResult.get(), "clamp(long) failed to update when base state changed again");
		assertEquals(5.0f, floatResult.get(), "clamp(float) failed to update when base state changed again");
		assertEquals(5.0, doubleResult.get(), "clamp(double) failed to update when base state changed again");
	}

	/* *************************************************************** *
	 *                 UNARY OPERATIONS (NEGATE & ABS)                 *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly negate the base long state and update on change")
	void testNegate() {
		final LongState baseLongState = new SimpleLongState(10L);
		final ReadOnlyLongState result = baseLongState.negate();

		assertEquals(-10L, result.get(), "negate() failed to calculate correct initial value");

		baseLongState.set(-20L);

		assertEquals(20L, result.get(), "negate() failed to update when base state changed");
	}

	@Test
	@DisplayName("Should correctly calculate the absolute value of the base long state and update on change")
	void testAbs() {
		final LongState baseLongState = new SimpleLongState(-10);
		final ReadOnlyLongState result = baseLongState.abs();

		assertEquals(10L, result.get(), "abs() failed to calculate correct initial value");

		baseLongState.set(20L);

		assertEquals(20L, result.get(), "abs() failed to update when base state changed");
	}

}
