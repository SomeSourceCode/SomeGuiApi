/*
 * Copyright 2025, SomeSourceCode - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.somesourcecode.someguiapi.state;

import java.lang.ref.Cleaner;
import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * This is used to create two-way bindings, called mirrors, between two
 * mutable states.
 *
 * @param <T> the type of the states' values
 * @since 3.0.0
 */
public class Mirror<T> implements Observer<T> {

	private static final Cleaner CLEANER = Cleaner.create();

	private final WeakReference<State<T>> state1;
	private final WeakReference<State<T>> state2;

	private Mirror(State<T> state1, State<T> state2) {
		CLEANER.register(state1, () -> state2.removeObserver(this));
		CLEANER.register(state2, () -> state1.removeObserver(this));

		this.state1 = new WeakReference<>(state1);
		this.state2 = new WeakReference<>(state2);
	}

	@Override
	public void onChange(T oldValue, T newValue) {
		final State<T> state1 = this.state1.get();
		final State<T> state2 = this.state2.get();
		if (state1 != null && state2 != null) {
			state1.set(newValue);
			state2.set(newValue);
			return;
		}
		if (state1 != null) {
			state1.removeObserver(this);
		}
		if (state2 != null) {
			state2.removeObserver(this);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		final Mirror<?> that = (Mirror<?>) obj;
		return Objects.equals(this.state1.get(), that.state1.get()) && Objects.equals(this.state2.get(), that.state2.get())
				|| Objects.equals(this.state1.get(), that.state2.get()) && Objects.equals(this.state2.get(), that.state1.get());
	}

	/**
	 * Creates a new {@link BooleanState} that mirrors the given state, until
	 * the state is detached.
	 * <p>
	 * This has the same effect as calling as creating a new state and manually
	 * mirroring the two states.
	 *
	 * @param other the state to mirror
	 * @return a new {@link BooleanState} that mirrors the given state
	 * @since 3.0.0
	 */
	public static BooleanState ofBoolean(State<Boolean> other) {
		final BooleanState state = new SimpleBooleanState(other.get());
		Mirror.mirror(other, state);
		return state;
	}

	/**
	 * Creates a new {@link IntegerState} that mirrors the given state, until
	 * the state is detached.
	 * <p>
	 * This has the same effect as calling as creating a new state and manually
	 * mirroring the two states.
	 *
	 * @param other the state to mirror
	 * @return a new {@link IntegerState} that mirrors the given state
	 * @since 3.0.0
	 */
	public static IntegerState ofInteger(State<Integer> other) {
		final IntegerState state = new SimpleIntegerState(other.get());
		Mirror.mirror(other, state);
		return state;
	}

	/**
	 * Creates a new {@link LongState} that mirrors the given state, until
	 * the state is detached.
	 * <p>
	 * This has the same effect as calling as creating a new state and manually
	 * mirroring the two states.
	 *
	 * @param state the state to mirror
	 * @return a new {@link LongState} that mirrors the given state
	 * @since 3.0.0
	 */
	public static LongState ofLong(State<Long> state) {
		if (state == null) {
			throw new IllegalArgumentException("state must be non-null");
		}
		final LongState newState = new SimpleLongState(state.get());
		Mirror.mirror(state, newState);
		return newState;
	}

	/**
	 * Creates a new {@link FloatState} that mirrors the given state, until
	 * the state is detached.
	 * <p>
	 * This has the same effect as calling as creating a new state and manually
	 * mirroring the two states.
	 *
	 * @param state the state to mirror
	 * @return a new {@link FloatState} that mirrors the given state
	 * @since 3.0.0
	 */
	public static FloatState ofFloat(State<Float> state) {
		if (state == null) {
			throw new IllegalArgumentException("state must be non-null");
		}
		final FloatState newState = new SimpleFloatState(state.get());
		Mirror.mirror(state, newState);
		return newState;
	}

	/**
	 * Creates a new {@link DoubleState} that mirrors the given state, until
	 * the state is detached.
	 * <p>
	 * This has the same effect as calling as creating a new state and manually
	 * mirroring the two states.
	 *
	 * @param state the state to mirror
	 * @return a new {@link DoubleState} that mirrors the given state
	 * @since 3.0.0
	 */
	public static DoubleState ofDouble(State<Double> state) {
		if (state == null) {
			throw new IllegalArgumentException("state must be non-null");
		}
		final DoubleState newState = new SimpleDoubleState(state.get());
		Mirror.mirror(state, newState);
		return newState;
	}

	/**
	 * Creates a new {@link StringState} that mirrors the given state, until
	 * the state is detached.
	 * <p>
	 * This has the same effect as calling as creating a new state and manually
	 * mirroring the two states.
	 *
	 * @param state the state to mirror
	 * @return a new {@link StringState} that mirrors the given state
	 * @since 3.0.0
	 */
	public static StringState ofString(State<String> state) {
		if (state == null) {
			throw new IllegalArgumentException("state must be non-null");
		}
		final StringState newState = new SimpleStringState(state.get());
		Mirror.mirror(state, newState);
		return newState;
	}

	/**
	 * Creates a new {@link ObjectState} that mirroring the given state, until
	 * the state is detached.
	 * <p>
	 * This has the same effect as calling as creating a new state and manually
	 * mirroring the two states.
	 *
	 * @param state the state to mirror
	 * @param <T> the type of the state's value
	 * @return the new {@code ObjectState} that mirrors the given state
	 * @throws IllegalArgumentException if the given state is null
	 * @since 3.0.0
	 */
	public static <T> ObjectState<T> ofObject(State<T> state) {
		if (state == null) {
			throw new IllegalArgumentException("state must be non-null");
		}
		final ObjectState<T> newState = new SimpleObjectState<>(state.get());
		Mirror.mirror(state, newState);
		return newState;
	}

	/**
	 * Initializes a two-way binding (mirror) between the two states. If the given
	 * states' values are different, the value of the second state will be
	 * updated to the value of the first state.
	 *
	 * @param state1 the first state
	 * @param state2 the second state
	 * @param <T> the type of the states' values
	 * @throws IllegalArgumentException if either state is null or currently reflecting
	 * @since 3.0.0
	 */
	public static <T> void mirror(State<T> state1, State<T> state2) {
		if (state1 == null || state2 == null) {
			throw new IllegalArgumentException("Both states must be non-null");
		}
		if (state1.equals(state2)) {
			return;
		}
		if (state1.isReflecting() || state2.isReflecting()) {
			throw new IllegalArgumentException("Cannot mirror reflecting states. Detach first");
		}
		final Mirror<T> mirror = new Mirror<>(state1, state2);
		state2.set(state1.get());
		state1.observe(mirror);
		state2.observe(mirror);
	}

	/**
	 * Detaches the two states from each other. This will remove the mirror
	 * between the two states, and the states will no longer be updated when one of
	 * them changes.
	 *
	 * @param state1 the first state
	 * @param state2 the second state
	 * @param <T> the type of the states' values
	 * @since 3.0.0
	 */
	public static <T> void detach(State<T> state1, State<T> state2) {
		if (state1 == null || state2 == null) {
			return;
		}
		final Mirror<T> mirror = new Mirror<>(state1, state2);
		state1.removeObserver(mirror);
		state2.removeObserver(mirror);
	}

}
