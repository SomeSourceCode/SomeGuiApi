package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanConstantTest {

	@Test
	@DisplayName("Should return the correct constant value")
	public void testConstantReturnsCorrectValue() {
		final BooleanConstant constantValue = new BooleanConstant(true);
		assertEquals(true, constantValue.get());
	}

}
