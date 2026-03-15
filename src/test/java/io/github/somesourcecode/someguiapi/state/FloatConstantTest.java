package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatConstantTest {

	@Test
	@DisplayName("Should return the correct constant value")
	public void testConstantReturnsCorrectValue() {
		final FloatConstant constantValue = new FloatConstant(42.0f);
		assertEquals(42.0f, constantValue.get());
	}

}
