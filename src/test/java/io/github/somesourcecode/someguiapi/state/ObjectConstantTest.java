package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectConstantTest {

	@Test
	@DisplayName("Should return the correct constant value")
	public void testConstantReturnsCorrectValue() {
		final ObjectConstant<Object> constantValue = new ObjectConstant<>("42");
		assertEquals("42", constantValue.get());
	}

}
