package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleConstantTest {

	@Test
	@DisplayName("Should return the correct constant value")
	public void testConstantReturnsCorrectValue() {
		final DoubleConstant constantValue = new DoubleConstant(42.0);
		assertEquals(42.0, constantValue.get());
	}

}
