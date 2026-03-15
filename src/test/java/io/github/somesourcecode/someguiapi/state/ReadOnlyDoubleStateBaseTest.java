package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadOnlyDoubleStateBaseTest {

	/* *************************************************************** *
	 *                            ADDITION                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly add primitive values to the base double state and update on change")
	void testAddPrimitives() {
		final DoubleState baseDoubleState = new SimpleDoubleState(10.0);
		final ReadOnlyDoubleState doubleResult = baseDoubleState.add(5.5);

		assertEquals(15.5, doubleResult.get(), "add(double) failed to calculate correct initial value");

		baseDoubleState.set(20.0);

		assertEquals(25.5, doubleResult.get(), "add(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                           SUBTRACTION                           *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly subtract primitive values from the base double state and update on change")
	void testSubtractPrimitives() {
		final DoubleState baseDoubleState = new SimpleDoubleState(10.0);
		final ReadOnlyDoubleState doubleResult = baseDoubleState.subtract(5.5);

		assertEquals(4.5, doubleResult.get(), "subtract(double) failed to calculate correct initial value");

		baseDoubleState.set(20.0);

		assertEquals(14.5, doubleResult.get(), "subtract(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                         MULTIPLICATION                          *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly multiply primitive values to the base double state and update on change")
	void testMultiplyPrimitives() {
		final DoubleState baseDoubleState = new SimpleDoubleState(10.0);
		final ReadOnlyDoubleState doubleResult = baseDoubleState.multiply(5.5);

		assertEquals(55.0, doubleResult.get(), "multiply(double) failed to calculate correct initial value");

		baseDoubleState.set(20.0);

		assertEquals(110.0, doubleResult.get(), "multiply(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                               MIN                               *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly find the minimum with primitive values and update on change")
	void testMinPrimitives() {
		final DoubleState baseDoubleState = new SimpleDoubleState(10.0);
		final ReadOnlyDoubleState doubleResult = baseDoubleState.min(15.5);

		assertEquals(10.0, doubleResult.get(), "min(double) failed to calculate correct initial value");

		baseDoubleState.set(20.0);

		assertEquals(15.5, doubleResult.get(), "min(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                               MAX                               *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly find the maximum with primitive values and update on change")
	void testMaxPrimitives() {
		final DoubleState baseDoubleState = new SimpleDoubleState(10.0);
		final ReadOnlyDoubleState doubleResult = baseDoubleState.max(5.5);

		assertEquals(10.0, doubleResult.get(), "max(double) failed to calculate correct initial value");

		baseDoubleState.set(0.0);

		assertEquals(5.5, doubleResult.get(), "max(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                              CLAMP                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly clamp the base double state within primitive bounds and update on change")
	void testClampPrimitives() {
		final DoubleState baseDoubleState = new SimpleDoubleState(10.0);
		final ReadOnlyDoubleState doubleResult = baseDoubleState.clamp(5.0, 15.0);

		assertEquals(10.0, doubleResult.get(), "clamp(double) failed to calculate correct initial value");

		baseDoubleState.set(20.0);

		assertEquals(15.0, doubleResult.get(), "clamp(double) failed to update when base state changed");

		baseDoubleState.set(0.0);

		assertEquals(5.0, doubleResult.get(), "clamp(double) failed to update when base state changed again");
	}

	/* *************************************************************** *
	 *                 UNARY OPERATIONS (NEGATE & ABS)                 *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly negate the base double state and update on change")
	void testNegate() {
		final DoubleState baseDoubleState = new SimpleDoubleState(10.5);
		final ReadOnlyDoubleState result = baseDoubleState.negate();

		assertEquals(-10.5, result.get(), "negate() failed to calculate correct initial value");

		baseDoubleState.set(-20.0);

		assertEquals(20.0, result.get(), "negate() failed to update when base state changed");
	}

	@Test
	@DisplayName("Should correctly calculate the absolute value of the base double state and update on change")
	void testAbs() {
		final DoubleState baseDoubleState = new SimpleDoubleState(-10.5);
		final ReadOnlyDoubleState result = baseDoubleState.abs();

		assertEquals(10.5, result.get(), "abs() failed to calculate correct initial value");

		baseDoubleState.set(20.0);

		assertEquals(20.0, result.get(), "abs() failed to update when base state changed");
	}

}
