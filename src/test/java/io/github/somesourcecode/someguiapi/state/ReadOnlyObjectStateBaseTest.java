package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReadOnlyObjectStateBaseTest {

	/* *************************************************************** *
	 *                           NULL CHECKS                           *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly identify null and non-null states and update on change")
	void testNullChecks() {
		final SimpleObjectState<String> baseState = new SimpleObjectState<>(null);

		final ReadOnlyBooleanState isNullResult = baseState.isNull();
		final ReadOnlyBooleanState isNotNullResult = baseState.isNotNull();

		assertTrue(isNullResult.get(), "isNull() failed to calculate correct initial value");
		assertFalse(isNotNullResult.get(), "isNotNull() failed to calculate correct initial value");

		baseState.set("Valid Data");

		assertFalse(isNullResult.get(), "isNull() failed to update when base state changed");
		assertTrue(isNotNullResult.get(), "isNotNull() failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                       FALLBACKS (OR ELSE)                       *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly return the base state value or the fallback value and update on change")
	void testOrElse() {
		final SimpleObjectState<String> baseState = new SimpleObjectState<>(null);
		final SimpleObjectState<String> fallbackState = new SimpleObjectState<>("Fallback State Value");

		final ReadOnlyObjectState<String> rawResult = baseState.orElse("Fallback Raw Value");
		final ReadOnlyObjectState<String> stateResult = baseState.orElse(fallbackState);

		assertEquals("Fallback Raw Value", rawResult.get(), "orElse(T) failed to calculate correct initial value");
		assertEquals("Fallback State Value", stateResult.get(), "orElse(ObservableValue) failed to calculate correct initial value");

		baseState.set("Primary Value");

		assertEquals("Primary Value", rawResult.get(), "orElse(T) failed to update when base state changed");
		assertEquals("Primary Value", stateResult.get(), "orElse(ObservableValue) failed to update when base state changed");

		baseState.set(null);
		fallbackState.set("Updated Fallback State");

		assertEquals("Updated Fallback State", stateResult.get(), "orElse(ObservableValue) failed to update when argument state changed");
	}

}
