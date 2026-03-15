package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStateTest<T> {

	protected abstract State<T> createState(T initialValue);

	protected abstract List<T> provideDistinctValues();

	@Test
	@DisplayName("Constructor should set initial value")
	void testConstructorSetsInitialValue() {
		final List<T> values = provideDistinctValues();
		final T initialValue = values.get(0);
		final State<T> state = createState(initialValue);

		assertEquals(initialValue, state.get());
	}

	@Test
	@DisplayName("Set method should update value")
	void testSet() {
		final List<T> values = provideDistinctValues();
		final T initialValue = values.get(0);
		final State<T> state = createState(initialValue);

		final T newValue = values.get(1);
		state.set(newValue);

		assertEquals(newValue, state.get());
	}

	@Test
	@DisplayName("Observer should fire on value change")
	void testObserverFiresOnChange() {
		final List<T> values = provideDistinctValues();
		final T initialValue = values.get(0);
		final State<T> state = createState(initialValue);

		final List<T> observedValues = new ArrayList<>();
		state.observe((oldValue, newValue) -> {
			observedValues.add(newValue);
		});

		state.set(initialValue);

		final T newValue = values.get(1);
		state.set(newValue);

		assertEquals(1, observedValues.size());
		assertEquals(newValue, observedValues.get(0));
	}

	@Test
	@DisplayName("Subscription should cancel observation")
	void testSubscriptionCancelsObservation() {
		final List<T> values = provideDistinctValues();
		final T initialValue = values.get(0);
		final State<T> state = createState(initialValue);

		final List<T> observedValues = new ArrayList<>();
		final Subscription subscription = state.observe((oldValue, newValue) -> {
			observedValues.add(newValue);
		});

		subscription.cancel();
		state.set(values.get(1));

		assertTrue(observedValues.isEmpty());
	}

	@Test
	@DisplayName("Set method should throw when state is reflecting another state")
	public void testSetThrowsWhenReflecting() {
		final List<T> values = provideDistinctValues();
		final State<T> sourceState = createState(values.get(0));
		final State<T> targetState = createState(values.get(1));

		targetState.reflect(sourceState);

		assertThrows(IllegalStateException.class, () -> {
			targetState.set(values.get(2));
		});
	}

	@Test
	@DisplayName("Mirror should synchronize values between states")
	public void testMirrorSynchronizesValues() {
		final List<T> values = provideDistinctValues();
		final State<T> primaryState = createState(values.get(0));
		final State<T> secondaryState = createState(values.get(1));

		primaryState.mirror(secondaryState);

		assertEquals(secondaryState.get(), primaryState.get());

		final T updatedValue = values.get(2);
		primaryState.set(updatedValue);

		assertEquals(updatedValue, secondaryState.get());
	}

}
