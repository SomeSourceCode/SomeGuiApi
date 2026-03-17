package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReadOnlyIntegerStateBaseTest {

	/* *************************************************************** *
	 *                            ADDITION                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly add primitive values to the base integer state and update on change")
	void testAddPrimitives() {
		final IntegerState baseIntegerState = new SimpleIntegerState(10);

		final ReadOnlyIntegerState integerResult = baseIntegerState.add(5);
		final ReadOnlyLongState longResult = baseIntegerState.add(5L);
		final ReadOnlyFloatState floatResult = baseIntegerState.add(5.5f);
		final ReadOnlyDoubleState doubleResult = baseIntegerState.add(5.5);

		assertEquals(15, integerResult.get(), "add(int) failed to calculate correct initial value");
		assertEquals(15L, longResult.get(), "add(long) failed to calculate correct initial value");
		assertEquals(15.5f, floatResult.get(), "add(float) failed to calculate correct initial value");
		assertEquals(15.5, doubleResult.get(), "add(double) failed to calculate correct initial value");

		baseIntegerState.set(20);

		assertEquals(25, integerResult.get(), "add(int) failed to update when base state changed");
		assertEquals(25L, longResult.get(), "add(long) failed to update when base state changed");
		assertEquals(25.5f, floatResult.get(), "add(float) failed to update when base state changed");
		assertEquals(25.5, doubleResult.get(), "add(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                           SUBTRACTION                           *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly subtract primitive values from the base integer state and update on change")
	void testSubtractPrimitives() {
		final IntegerState baseIntegerState = new SimpleIntegerState(10);

		final ReadOnlyIntegerState integerResult = baseIntegerState.subtract(5);
		final ReadOnlyLongState longResult = baseIntegerState.subtract(5L);
		final ReadOnlyFloatState floatResult = baseIntegerState.subtract(5.5f);
		final ReadOnlyDoubleState doubleResult = baseIntegerState.subtract(5.5);

		assertEquals(5, integerResult.get(), "subtract(int) failed to calculate correct initial value");
		assertEquals(5L, longResult.get(), "subtract(long) failed to calculate correct initial value");
		assertEquals(4.5f, floatResult.get(), "subtract(float) failed to calculate correct initial value");
		assertEquals(4.5, doubleResult.get(), "subtract(double) failed to calculate correct initial value");

		baseIntegerState.set(20);

		assertEquals(15, integerResult.get(), "subtract(int) failed to update when base state changed");
		assertEquals(15L, longResult.get(), "subtract(long) failed to update when base state changed");
		assertEquals(14.5f, floatResult.get(), "subtract(float) failed to update when base state changed");
		assertEquals(14.5, doubleResult.get(), "subtract(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                         MULTIPLICATION                          *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly multiply primitive values to the base integer state and update on change")
	void testMultiplyPrimitives() {
		final IntegerState baseIntegerState = new SimpleIntegerState(10);

		final ReadOnlyIntegerState integerResult = baseIntegerState.multiply(5);
		final ReadOnlyLongState longResult = baseIntegerState.multiply(5L);
		final ReadOnlyFloatState floatResult = baseIntegerState.multiply(5.5f);
		final ReadOnlyDoubleState doubleResult = baseIntegerState.multiply(5.5);

		assertEquals(50, integerResult.get(), "multiply(int) failed to calculate correct initial value");
		assertEquals(50L, longResult.get(), "multiply(long) failed to calculate correct initial value");
		assertEquals(55.0f, floatResult.get(), "multiply(float) failed to calculate correct initial value");
		assertEquals(55.0, doubleResult.get(), "multiply(double) failed to calculate correct initial value");

		baseIntegerState.set(20);

		assertEquals(100, integerResult.get(), "multiply(int) failed to update when base state changed");
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
		final IntegerState baseIntegerState = new SimpleIntegerState(10);

		final ReadOnlyIntegerState integerResult = baseIntegerState.min(15);
		final ReadOnlyLongState longResult = baseIntegerState.min(15L);
		final ReadOnlyFloatState floatResult = baseIntegerState.min(15.5f);
		final ReadOnlyDoubleState doubleResult = baseIntegerState.min(15.5);

		assertEquals(10, integerResult.get(), "min(int) failed to calculate correct initial value");
		assertEquals(10L, longResult.get(), "min(long) failed to calculate correct initial value");
		assertEquals(10.0f, floatResult.get(), "min(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "min(double) failed to calculate correct initial value");

		baseIntegerState.set(20);

		assertEquals(15, integerResult.get(), "min(int) failed to update when base state changed");
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
		final IntegerState baseIntegerState = new SimpleIntegerState(10);

		final ReadOnlyIntegerState integerResult = baseIntegerState.max(5);
		final ReadOnlyLongState longResult = baseIntegerState.max(5L);
		final ReadOnlyFloatState floatResult = baseIntegerState.max(5.5f);
		final ReadOnlyDoubleState doubleResult = baseIntegerState.max(5.5);

		assertEquals(10, integerResult.get(), "max(int) failed to calculate correct initial value");
		assertEquals(10L, longResult.get(), "max(long) failed to calculate correct initial value");
		assertEquals(10.0f, floatResult.get(), "max(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "max(double) failed to calculate correct initial value");

		baseIntegerState.set(0);

		assertEquals(5, integerResult.get(), "max(int) failed to update when base state changed");
		assertEquals(5L, longResult.get(), "max(long) failed to update when base state changed");
		assertEquals(5.5f, floatResult.get(), "max(float) failed to update when base state changed");
		assertEquals(5.5, doubleResult.get(), "max(double) failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                              CLAMP                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly clamp the base integer state within primitive bounds and update on change")
	void testClampPrimitives() {
		final IntegerState baseIntegerState = new SimpleIntegerState(10);

		final ReadOnlyIntegerState integerResult = baseIntegerState.clamp(5, 15);
		final ReadOnlyLongState longResult = baseIntegerState.clamp(5L, 15L);
		final ReadOnlyFloatState floatResult = baseIntegerState.clamp(5.0f, 15.0f);
		final ReadOnlyDoubleState doubleResult = baseIntegerState.clamp(5.0, 15.0);

		assertEquals(10, integerResult.get(), "clamp(int) failed to calculate correct initial value");
		assertEquals(10L, longResult.get(), "clamp(long) failed to calculate correct initial value");
		assertEquals(10.0f, floatResult.get(), "clamp(float) failed to calculate correct initial value");
		assertEquals(10.0, doubleResult.get(), "clamp(double) failed to calculate correct initial value");

		baseIntegerState.set(20);

		assertEquals(15, integerResult.get(), "clamp(int) failed to update when base state changed");
		assertEquals(15L, longResult.get(), "clamp(long) failed to update when base state changed");
		assertEquals(15.0f, floatResult.get(), "clamp(float) failed to update when base state changed");
		assertEquals(15.0, doubleResult.get(), "clamp(double) failed to update when base state changed");

		baseIntegerState.set(0);

		assertEquals(5, integerResult.get(), "clamp(int) failed to update when base state changed again");
		assertEquals(5L, longResult.get(), "clamp(long) failed to update when base state changed again");
		assertEquals(5.0f, floatResult.get(), "clamp(float) failed to update when base state changed again");
		assertEquals(5.0, doubleResult.get(), "clamp(double) failed to update when base state changed again");
	}

	/* *************************************************************** *
	 *                 UNARY OPERATIONS (NEGATE & ABS)                 *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly negate the base integer state and update on change")
	void testNegate() {
		final IntegerState baseIntegerState = new SimpleIntegerState(10);
		final ReadOnlyIntegerState result = baseIntegerState.negate();

		assertEquals(-10, result.get(), "negate() failed to calculate correct initial value");

		baseIntegerState.set(-20);

		assertEquals(20, result.get(), "negate() failed to update when base state changed");
	}

	@Test
	@DisplayName("Should correctly calculate the absolute value of the base integer state and update on change")
	void testAbs() {
		final IntegerState baseIntegerState = new SimpleIntegerState(-10);
		final ReadOnlyIntegerState result = baseIntegerState.abs();

		assertEquals(10, result.get(), "abs() failed to calculate correct initial value");

		baseIntegerState.set(20);

		assertEquals(20, result.get(), "abs() failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                         BOOLEAN CHECKS                          *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly identify even and odd states and update on change")
	void testIsEvenAndIsOdd() {
		final IntegerState baseIntegerState = new SimpleIntegerState(2);

		final ReadOnlyBooleanState isEvenResult = baseIntegerState.isEven();
		final ReadOnlyBooleanState isOddResult = baseIntegerState.isOdd();

		assertTrue(isEvenResult.get(), "isEven() failed to calculate correct initial value");
		assertFalse(isOddResult.get(), "isOdd() failed to calculate correct initial value");

		baseIntegerState.set(3);

		assertFalse(isEvenResult.get(), "isEven() failed to update when base state changed");
		assertTrue(isOddResult.get(), "isOdd() failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                     GREATEST COMMON DIVISOR                     *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly calculate GCD and update on change")
	void testGcd() {
		final IntegerState baseIntegerState = new SimpleIntegerState(54);
		final IntegerState stateToCompare = new SimpleIntegerState(24);

		final ReadOnlyIntegerState primitiveResult = baseIntegerState.gcd(24);
		final ReadOnlyIntegerState stateResult = baseIntegerState.gcd(stateToCompare);

		assertEquals(6, primitiveResult.get(), "gcd(int) failed to calculate correct initial value");
		assertEquals(6, stateResult.get(), "gcd(ObservableValue) failed to calculate correct initial value");

		baseIntegerState.set(48);

		assertEquals(24, primitiveResult.get(), "gcd(int) failed to update when base state changed");
		assertEquals(24, stateResult.get(), "gcd(ObservableValue) failed to update when base state changed");

		stateToCompare.set(18);

		assertEquals(6, stateResult.get(), "gcd(ObservableValue) failed to update when argument state changed");
	}

	/* *************************************************************** *
	 *                      LEAST COMMON MULTIPLE                      *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly calculate LCM and update on change")
	void testLcm() {
		final IntegerState baseIntegerState = new SimpleIntegerState(4);
		final IntegerState stateToCompare = new SimpleIntegerState(6);

		final ReadOnlyIntegerState primitiveResult = baseIntegerState.lcm(6);
		final ReadOnlyIntegerState stateResult = baseIntegerState.lcm(stateToCompare);

		assertEquals(12, primitiveResult.get(), "lcm(int) failed to calculate correct initial value");
		assertEquals(12, stateResult.get(), "lcm(ObservableValue) failed to calculate correct initial value");

		baseIntegerState.set(10);

		assertEquals(30, primitiveResult.get(), "lcm(int) failed to update when base state changed");
		assertEquals(30, stateResult.get(), "lcm(ObservableValue) failed to update when base state changed");

		stateToCompare.set(15);

		assertEquals(30, stateResult.get(), "lcm(ObservableValue) failed to update when argument state changed");
	}

}
