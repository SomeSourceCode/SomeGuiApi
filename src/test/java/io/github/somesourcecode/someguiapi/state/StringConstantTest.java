package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringConstantTest {

	@Test
	@DisplayName("Should return the correct constant value")
	public void testConstantReturnsCorrectValue() {
		final StringConstant constantValue = new StringConstant("Hello World!");
		assertEquals("Hello World!", constantValue.get());
	}

}
