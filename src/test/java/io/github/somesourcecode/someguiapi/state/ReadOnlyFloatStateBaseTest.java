package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadOnlyFloatStateBaseTest {

	/* *************************************************************** *
	 *                            ADDITION                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly add primitive values to the base float state and update on change")
	void testAddPrimitives() {
		final FloatState baseFloatState = new SimpleFloatState(10f);

		final ReadOnlyFloatState floatResult = baseFloatState.add(5.5f);
		final ReadOnlyDoubleState doubleResult = baseFloatState.add(5.5);

		assertEquals(15.5f, floatResult.get(), "add(float) failed to calculate correct initial value");
		assertEquals(15.5, doubleResult.get(), "add(double) failed to calculate correct initial value");

		baseFloatState.set(20f);

		assertEquals(25.5f, floatResult.get(), "add(float) failed to update when base state changed");
		assertEquals(25.5, doubleResult.get(), "add(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                           SUBTRACTION                           *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly subtract primitive values from the base float state and update on change")
	void testSubtractPrimitives() {
		final FloatState baseFloatState = new SimpleFloatState(10f);

		final ReadOnlyFloatState floatResult = baseFloatState.subtract(5.5f);
		final ReadOnlyDoubleState doubleResult = baseFloatState.subtract(5.5);

		assertEquals(4.5f, floatResult.get(), "subtract(float) failed to calculate correct initial value");
		assertEquals(4.5, doubleResult.get(), "subtract(double) failed to calculate correct initial value");

		baseFloatState.set(20f);

		assertEquals(14.5f, floatResult.get(), "subtract(float) failed to update when base state changed");
		assertEquals(14.5, doubleResult.get(), "subtract(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                         MULTIPLICATION                          *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly multiply primitive values to the base float state and update on change")
	void testMultiplyPrimitives() {
		final FloatState baseFloatState = new SimpleFloatState(10f);

		final ReadOnlyFloatState floatResult = baseFloatState.multiply(5.5f);
		final ReadOnlyDoubleState doubleResult = baseFloatState.multiply(5.5);

		assertEquals(55.0f, floatResult.get(), "multiply(float) failed to calculate correct initial value");
		assertEquals(55.0, doubleResult.get(), "multiply(double) failed to calculate correct initial value");

		baseFloatState.set(20f);

		assertEquals(110.0f, floatResult.get(), "multiply(float) failed to update when base state changed");
		assertEquals(110.0, doubleResult.get(), "multiply(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                               MIN                               *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly find the minimum with primitive values and update on change")
	void testMinPrimitives() {
		final FloatState baseFloatState = new SimpleFloatState(10f);

		final ReadOnlyFloatState floatResult = baseFloatState.min(15.5f);
		final ReadOnlyDoubleState doubleResult = baseFloatState.min(15.5);

		assertEquals(10.0f, floatResult.get(), "min(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "min(double) failed to calculate correct initial value");

		baseFloatState.set(20f);

		assertEquals(15.5f, floatResult.get(), "min(float) failed to update when base state changed");
		assertEquals(15.5, doubleResult.get(), "min(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                               MAX                               *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly find the maximum with primitive values and update on change")
	void testMaxPrimitives() {
		final FloatState baseFloatState = new SimpleFloatState(10f);

		final ReadOnlyFloatState floatResult = baseFloatState.max(5.5f);
		final ReadOnlyDoubleState doubleResult = baseFloatState.max(5.5);

		assertEquals(10.0f, floatResult.get(), "max(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "max(double) failed to calculate correct initial value");

		baseFloatState.set(0f);

		assertEquals(5.5f, floatResult.get(), "max(float) failed to update when base state changed");
		assertEquals(5.5, doubleResult.get(), "max(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                              CLAMP                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly clamp the base float state within primitive bounds and update on change")
	void testClampPrimitives() {
		final FloatState baseFloatState = new SimpleFloatState(10f);

		final ReadOnlyFloatState floatResult = baseFloatState.clamp(5.0f, 15.0f);
		final ReadOnlyDoubleState doubleResult = baseFloatState.clamp(5.0, 15.0);

		assertEquals(10.0f, floatResult.get(), "clamp(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "clamp(double) failed to calculate correct initial value");

		baseFloatState.set(20f);

		assertEquals(15.0f, floatResult.get(), "clamp(float) failed to update when base state changed");
		assertEquals(15.0, doubleResult.get(), "clamp(double) failed to update when base state changed");

		baseFloatState.set(0f);

		assertEquals(5.0f, floatResult.get(), "clamp(float) failed to update when base state changed again");
		assertEquals(5.0, doubleResult.get(), "clamp(double) failed to update when base state changed again");
	}

	/* *************************************************************** *
	 *                 UNARY OPERATIONS (NEGATE & ABS)                 *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly negate the base float state and update on change")
	void testNegate() {
		final FloatState baseFloatState = new SimpleFloatState(10.5f);
		final ReadOnlyFloatState result = baseFloatState.negate();

		assertEquals(-10.5f, result.get(), "negate() failed to calculate correct initial value");

		baseFloatState.set(-20f);

		assertEquals(20f, result.get(), "negate() failed to update when base state changed");
	}

	@Test
	@DisplayName("Should correctly calculate the absolute value of the base float state and update on change")
	void testAbs() {
		final FloatState baseFloatState = new SimpleFloatState(-10.5f);
		final ReadOnlyFloatState result = baseFloatState.abs();

		assertEquals(10.5f, result.get(), "abs() failed to calculate correct initial value");

		baseFloatState.set(20f);

		assertEquals(20f, result.get(), "abs() failed to update when base state changed");
	}

}
