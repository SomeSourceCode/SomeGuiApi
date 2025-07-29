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
import java.util.Arrays;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * A utility class for creating state transformations.
 *
 * @since 3.0.0
 */
public class Reflect {

	private Reflect() {}

	private static final Cleaner CLEANER = Cleaner.create();

	private static void registerDependencies(ObservableValue<?>[] dependencies, Observer<Object> observer, ObservableValue<?> expression) {
		final ObservableValue<?>[] nonNullDependencies = Arrays.stream(dependencies)
				.filter(Objects::nonNull)
				.toArray(ObservableValue[]::new);

		for (ObservableValue<?> dependency : nonNullDependencies) {
			dependency.observe(observer);
		}

		CLEANER.register(expression, () -> {
			for (ObservableValue<?> dependency : nonNullDependencies) {
				dependency.removeObserver(observer);
			}
		});
	}

	private static ObservableValue<?>[] combineDependencies(ObservableValue<?> dependency, ObservableValue<?>... dependencies) {
		return Stream.concat(Stream.of(dependency), Stream.of(dependencies)).toArray(ObservableValue<?>[]::new);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static ReadOnlyBooleanState createBooleanReflection(Supplier<Boolean> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}

		final BooleanExpression expression = new BooleanExpression(supplier);
		final WeakReference<BooleanExpression> weakExpression = new WeakReference<>(expression);

		final Observer<Object> observer = new Observer<>() {
			@Override
			public void onChange(Object oldValue, Object newValue) {
				BooleanExpression expression = weakExpression.get();
				if (expression == null) {
					for (ObservableValue<?> dependency : dependencies) {
						if (dependency == null) {
							continue;
						}
						dependency.removeObserver(this);
					}
					return;
				}
				expression.update();
			}
		};

		registerDependencies(dependencies, observer, expression);

		return expression;
	}

	/**
	 * Creates a {@link ReadOnlyNumberState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 * <p>
	 * The specific implementation of the state is determined by the type of the
	 * number. Supported types are those specified {@link NumberState#TYPES}.
	 *
	 * @param type the type of the number
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyNumberState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static ReadOnlyNumberState<? extends Number> createNumberReflection(Class<?> type, Supplier<? extends Number> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}
		if (type == null) {
			throw new IllegalArgumentException("type must be non-null");
		}
		if (Integer.class.isAssignableFrom(type)) {
			return createIntegerReflection(() -> supplier.get().intValue(), dependencies);
		}
		if (Long.class.isAssignableFrom(type)) {
			return createLongReflection(() -> supplier.get().longValue(), dependencies);
		}
		if (Float.class.isAssignableFrom(type)) {
			return createFloatReflection(() -> supplier.get().floatValue(), dependencies);
		}
		if (Double.class.isAssignableFrom(type)) {
			return createDoubleReflection(() -> supplier.get().doubleValue(), dependencies);
		}
		throw new IllegalArgumentException("Unsupported number type: " + type);
	}

	/**
	 * Creates a {@link ReadOnlyIntegerState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyIntegerState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static ReadOnlyIntegerState createIntegerReflection(Supplier<Integer> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}

		final IntegerExpression expression = new IntegerExpression(supplier);
		final WeakReference<IntegerExpression> weakExpression = new WeakReference<>(expression);

		final Observer<Object> observer = new Observer<>() {
			@Override
			public void onChange(Object oldValue, Object newValue) {
				IntegerExpression expression = weakExpression.get();
				if (expression == null) {
					for (ObservableValue<?> dependency : dependencies) {
						if (dependency == null) {
							continue;
						}
						dependency.removeObserver(this);
					}
					return;
				}
				expression.update();
			}
		};

		registerDependencies(dependencies, observer, expression);

		return expression;
	}

	/**
	 * Creates a {@link ReadOnlyLongState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyLongState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static ReadOnlyLongState createLongReflection(Supplier<Long> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}

		final LongExpression expression = new LongExpression(supplier);
		final WeakReference<LongExpression> weakExpression = new WeakReference<>(expression);

		final Observer<Object> observer = new Observer<>() {
			@Override
			public void onChange(Object oldValue, Object newValue) {
				LongExpression expression = weakExpression.get();
				if (expression == null) {
					for (ObservableValue<?> dependency : dependencies) {
						if (dependency == null) {
							continue;
						}
						dependency.removeObserver(this);
					}
					return;
				}
				expression.update();
			}
		};

		registerDependencies(dependencies, observer, expression);

		return expression;
	}

	/**
	 * Creates a {@link ReadOnlyFloatState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyLongState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static ReadOnlyFloatState createFloatReflection(Supplier<Float> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}

		final FloatExpression expression = new FloatExpression(supplier);
		final WeakReference<FloatExpression> weakExpression = new WeakReference<>(expression);

		final Observer<Object> observer = new Observer<>() {
			@Override
			public void onChange(Object oldValue, Object newValue) {
				FloatExpression expression = weakExpression.get();
				if (expression == null) {
					for (ObservableValue<?> dependency : dependencies) {
						if (dependency == null) {
							continue;
						}
						dependency.removeObserver(this);
					}
					return;
				}
				expression.update();
			}
		};

		registerDependencies(dependencies, observer, expression);

		return expression;
	}

	/**
	 * Creates a {@link ReadOnlyDoubleState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyDoubleState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static ReadOnlyDoubleState createDoubleReflection(Supplier<Double> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}

		final DoubleExpression expression = new DoubleExpression(supplier);
		final WeakReference<DoubleExpression> weakExpression = new WeakReference<>(expression);

		final Observer<Object> observer = new Observer<>() {
			@Override
			public void onChange(Object oldValue, Object newValue) {
				DoubleExpression expression = weakExpression.get();
				if (expression == null) {
					for (ObservableValue<?> dependency : dependencies) {
						if (dependency == null) {
							continue;
						}
						dependency.removeObserver(this);
					}
					return;
				}
				expression.update();
			}
		};

		registerDependencies(dependencies, observer, expression);

		return expression;
	}

	/**
	 * Creates a {@link ReadOnlyObjectState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyObjectState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static <T> ReadOnlyObjectState<T> createObjectReflection(Supplier<T> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}

		final ObjectExpression<T> expression = new ObjectExpression<>(supplier);
		final WeakReference<ObjectExpression<T>> weakExpression = new WeakReference<>(expression);

		final Observer<Object> observer = new Observer<>() {
			@Override
			public void onChange(Object oldValue, Object newValue) {
				ObjectExpression<T> expression = weakExpression.get();
				if (expression == null) {
					for (ObservableValue<?> dependency : dependencies) {
						if (dependency == null) {
							continue;
						}
						dependency.removeObserver(this);
					}
					return;
				}
				expression.update();
			}
		};

		registerDependencies(dependencies, observer, expression);

		return expression;
	}

	/**
	 * Creates a {@link ReadOnlyStringState} that reflects the value of the given
	 * supplier and updates when any of the given dependencies change.
	 *
	 * @param supplier the supplier that provides the value of the state
	 * @param dependencies the dependencies that trigger updates
	 * @return the newly created {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if supplier or dependencies are null
	 * @since 3.0.0
	 */
	public static ReadOnlyStringState createStringReflection(Supplier<String> supplier, ObservableValue<?>... dependencies) {
		if (supplier == null) {
			throw new IllegalArgumentException("supplier must be non-null");
		}
		if (dependencies == null) {
			throw new IllegalArgumentException("dependencies must be non-null");
		}

		final StringExpression expression = new StringExpression(supplier);
		final WeakReference<StringExpression> weakExpression = new WeakReference<>(expression);

		final Observer<Object> observer = new Observer<>() {
			@Override
			public void onChange(Object oldValue, Object newValue) {
				StringExpression expression = weakExpression.get();
				if (expression == null) {
					for (ObservableValue<?> dependency : dependencies) {
						if (dependency == null) {
							continue;
						}
						dependency.removeObserver(this);
					}
					return;
				}
				expression.update();
			}
		};

		registerDependencies(dependencies, observer, expression);

		return expression;
	}

	private static void checkValuesNonNull(ObservableValue<?>... values) {
		if (values == null || Arrays.stream(values).anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("All values must be non-null");
		}
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if any of the
	 * given {@link ObservableValue}s is {@code true}, {@code false} otherwise.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState any(ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		return createBooleanReflection(() -> {
			for (ObservableValue<? extends Boolean> value : values) {
				if (value.get()) {
					return true;
				}
			}
			return false;
		}, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if all the
	 * given {@link ObservableValue}s are {@code true}, {@code false} otherwise.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState all(ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		return createBooleanReflection(() -> {
			for (ObservableValue<? extends Boolean> value : values) {
				if (!value.get()) {
					return false;
				}
			}
			return true;
		}, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if none of the
	 * given {@link ObservableValue}s are {@code true}, {@code false} otherwise.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState none(ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		return createBooleanReflection(() -> {
			for (ObservableValue<? extends Boolean> value : values) {
				if (value.get()) {
					return false;
				}
			}
			return true;
		}, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if the values of
	 * all the given {@link ObservableValue}s are equal, {@code false} otherwise.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState consensus(ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		final int length = values.length;
		if (length == 0) {
			return new BooleanConstant(true);
		}
		return createBooleanReflection(() -> {
			boolean consensus = values[0].get();
			for (int i = 1; i < length; i++) {
				if (values[i].get() != consensus) {
					return false;
				}
			}
			return true;
		}, values);
	}

	private static int countTrueValues(ObservableValue<? extends Boolean>[] values) {
		int count = 0;
		for (ObservableValue<? extends Boolean> value : values) {
			if (value.get()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if exactly
	 * {@code count} of the given {@link ObservableValue}s are {@code true},
	 * {@code false} otherwise.
	 *
	 * @param count the number of {@link ObservableValue}s that must be {@code true} for the
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState exactly(int count, ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		final int length = values.length;
		if (count < 0 || count > length) {
			return new BooleanConstant(false);
		}
		return createBooleanReflection(() -> countTrueValues(values) == count, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if exactly
	 * {@code count} of the given {@link ObservableValue}s are {@code true},
	 * {@code false} otherwise.
	 *
	 * @param count the {@link ObservableValue} that holds the number of values that must be {@code true}
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if count or any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState exactly(ObservableValue<Integer> count, ObservableValue<? extends Boolean>... values) {
		if (count == null) {
			throw new IllegalArgumentException("count must be non-null");
		}
		checkValuesNonNull(values);
		final int length = values.length;
		return createBooleanReflection(() -> countTrueValues(values) == count.get(), combineDependencies(count, values));
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if exactly half
	 * of the given {@link ObservableValue}s are {@code true}, {@code false}
	 * otherwise.
	 * <p>
	 * If the number of values is odd, this will always return {@code false}.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState tie(ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		final int length = values.length;
		if (length % 2 == 1) {
			return new BooleanConstant(false);
		}
		return exactly(length / 2, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at least
	 * {@code count} of the given {@link ObservableValue}s are {@code true},
	 * {@code false} otherwise.
	 *
	 * @param count the number of {@link ObservableValue}s that must be {@code true}
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState atLeast(int count, ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		final int length = values.length;
		if (count > length) {
			return new BooleanConstant(false);
		}
		return createBooleanReflection(() -> countTrueValues(values) >= count, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at least
	 * {@code count} of the given {@link ObservableValue}s are {@code true},
	 * {@code false} otherwise.
	 *
	 * @param count the {@link ObservableValue} that holds the number of values that must be {@code true}
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if count or any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState atLeast(ObservableValue<Integer> count, ObservableValue<? extends Boolean>... values) {
		if (count == null) {
			throw new IllegalArgumentException("count must be non-null");
		}
		checkValuesNonNull(values);
		final int length = values.length;
		return createBooleanReflection(() -> countTrueValues(values) >= count.get(), combineDependencies(count, values));
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at most
	 * {@code count} of the given {@link ObservableValue}s are {@code true},
	 * {@code false} otherwise.
	 *
	 * @param count the number of {@link ObservableValue}s that must be {@code true}
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState atMost(int count, ObservableValue<? extends Boolean>... values) {
		checkValuesNonNull(values);
		final int length = values.length;
		if (count < 0) {
			return new BooleanConstant(false);
		}
		return createBooleanReflection(() -> countTrueValues(values) <= count, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at most
	 * {@code count} of the given {@link ObservableValue}s are {@code true},
	 * {@code false} otherwise.
	 *
	 * @param count the {@link ObservableValue} that holds the number of values that must be {@code true}
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if count or any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState atMost(ObservableValue<Integer> count, ObservableValue<? extends Boolean>... values) {
		if (count == null) {
			throw new IllegalArgumentException("count must be non-null");
		}
		checkValuesNonNull(values);
		final int length = values.length;
		return createBooleanReflection(() -> countTrueValues(values) <= count.get(), combineDependencies(count, values));
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at least
	 * half of the given {@link ObservableValue}s are {@code true}, {@code false}
	 * otherwise.
	 * <p>
	 * This is inclusive, meaning that if exactly half of the values are
	 * {@code true}, the state will be {@code true}.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState majority(ObservableValue<? extends Boolean>... values) {
		return atLeast((values.length + 1) / 2, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at least
	 * half of the given {@link ObservableValue}s are {@code true}, {@code false}
	 * otherwise.
	 * <p>
	 * This is exclusive, meaning that if exactly half of the values are
	 * {@code true}, the state will be {@code false}.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState strictMajority(ObservableValue<? extends Boolean>... values) {
		return atLeast(values.length / 2 + 1, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at most
	 * half of the given {@link ObservableValue}s are {@code true}, {@code false}
	 * otherwise.
	 * <p>
	 * This is inclusive, meaning that if exactly half of the values are
	 * {@code true}, the state will be {@code true}.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState minority(ObservableValue<? extends Boolean>... values) {
		return atMost((values.length - 1) / 2, values);
	}

	/**
	 * Creates a {@link ReadOnlyBooleanState} that is {@code true} if at most
	 * half of the given {@link ObservableValue}s are {@code true}, {@code false}
	 * otherwise.
	 * <p>
	 * This is exclusive, meaning that if exactly half of the values are
	 * {@code true}, the state will be {@code false}.
	 *
	 * @param values the {@link ObservableValue}s to check
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyBooleanState strictMinority(ObservableValue<? extends Boolean>... values) {
		return atMost(values.length / 2 - 1, values);
	}

	/**
	 * Creates a {@link ReadOnlyNumberState} that holds the smallest of the given
	 * {@link ObservableValue}s.
	 * <p>
	 * The type of the returned state is determined by the most specific applicable type
	 * from the {@link NumberState#TYPES} array.
	 *
	 * @param values the {@link ObservableValue}s to find the minimum of
	 * @return the newly created {@code ReadOnlyNumberState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyNumberState<? extends Number> min(ObservableValue<? extends Number>... values) {
		return minMax(values, true);
	}

	/**
	 * Creates a {@link ReadOnlyNumberState} that holds the largest of the given
	 * {@link ObservableValue}s.
	 * <p>
	 * The type of the returned state is determined by the most specific applicable type
	 * from the {@link NumberState#TYPES} array.
	 *
	 * @param values the {@link ObservableValue}s to find the maximum of
	 * @return the newly created {@code ReadOnlyNumberState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyNumberState<? extends Number> max(ObservableValue<? extends Number>... values) {
		return minMax(values, false);
	}

	private static ReadOnlyNumberState<? extends Number> minMax(ObservableValue<? extends Number>[] values, boolean findMin) {
		checkValuesNonNull(values);
		final Class<?> type = NumberState.findCommonNumberType(values);
		return createNumberReflection(type, () -> {
			final DoubleStream stream = Stream.of(values)
					.mapToDouble(value -> value.get().doubleValue());
			final OptionalDouble minMax = findMin ? stream.min() : stream.max();
			return minMax.orElse(0);
		}, values);
	}

	/**
	 * Creates a {@link ReadOnlyDoubleState} that holds the mean of the given
	 * {@link ObservableValue}s.
	 *
	 * @param values the {@link ObservableValue}s to calculate the mean of
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyDoubleState mean(ObservableValue<? extends Number>... values) {
		checkValuesNonNull(values);
		final int length = values.length;
		if (length == 0) {
			return new DoubleConstant(0);
		}
		return createDoubleReflection(() -> {
			double mean = 0;
			for (ObservableValue<? extends Number> value : values) {
				mean += value.get().doubleValue() / length;
			}
			return mean;
		}, values);
	}

	/**
	 * Creates a {@link ReadOnlyNumberState} that holds the median of the given
	 * {@link ObservableValue}s.
	 * <p>
	 * The type of the returned state is determined by the most specific applicable type
	 * from the {@link NumberState#TYPES} array.
	 *
	 * @param values the {@link ObservableValue}s to calculate the median of
	 * @throws IllegalArgumentException if any of the values are null
	 * @return the newly created {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyNumberState<? extends Number> median(ObservableValue<? extends Number>... values) {
		checkValuesNonNull(values);
		final int length = values.length;
		if (length == 0) {
			return new IntegerConstant(0);
		}
		final Class<?> type = NumberState.findCommonNumberType(values);
		return createNumberReflection(type, () -> {
			final double[] sortedValues = Stream.of(values).mapToDouble(v -> v.get().doubleValue()).sorted().toArray();
			if (length % 2 == 1) {
				return sortedValues[length / 2];
			} else {
				return (sortedValues[length / 2 - 1] + sortedValues[length / 2]) / 2;
			}
		}, values);
	}

	/**
	 * Creates a {@link ReadOnlyStringState} that joins the values of the given
	 * {@link ObservableValue}s with the specified delimiter.
	 * <p>
	 * If the delimiter is {@code null}, it will be treated as an empty string.
	 *
	 * @param delimiter the delimiter to use for joining
	 * @param values the {@link ObservableValue}s to join
	 * @return the newly created {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyStringState join(String delimiter, ObservableValue<String>... values) {
		if (values == null) {
			throw new IllegalArgumentException("Values must be non-null");
		}
		for (ObservableValue<String> value : values) {
			if (value == null) {
				throw new IllegalArgumentException("All values must be non-null");
			}
		}
		return createStringReflection(() -> {
			final String nonNullDelimiter = delimiter == null ? "" : delimiter;
			return joinStrings(nonNullDelimiter, values);
		}, values);
	}

	/**
	 * Creates a {@link ReadOnlyStringState} that joins the values of the given
	 * {@link ObservableValue}s with the specified delimiter.
	 * <p>
	 * If the delimiter is {@code null}, it will be treated as an empty string.
	 *
	 * @param delimiter the {@link ObservableValue} that provides the delimiter to use for joining
	 * @param values the {@link ObservableValue}s to join
	 * @return the newly created {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyStringState join(ObservableValue<String> delimiter, ObservableValue<String>... values) {
		if (delimiter == null) {
			throw new IllegalArgumentException("Delimiter must be non-null");
		}
		checkValuesNonNull(values);
		return createStringReflection(() -> {
			final String nonNullDelimiter = delimiter.get() == null ? "" : delimiter.get();
			return joinStrings(nonNullDelimiter, values);
		}, combineDependencies(delimiter, values));
	}

	private static String joinStrings(String nonNullDelimiter, ObservableValue<String>[] values) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				builder.append(nonNullDelimiter);
			}
			builder.append(values[i].get());
		}
		return builder.toString();
	}

	/**
	 * Creates a {@link ReadOnlyStringState} that concatenates the values of the
	 * given {@link ObservableValue}s.
	 *
	 * @param values the {@link ObservableValue}s to concatenate
	 * @return the newly created {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if any of the values are null
	 * @since 3.0.0
	 */
	@SafeVarargs
	public static ReadOnlyStringState concat(ObservableValue<String>... values) {
		checkValuesNonNull(values);
		return createStringReflection(() -> {
			final StringBuilder builder = new StringBuilder();
			for (ObservableValue<String> value : values) {
				builder.append(value.get());
			}
			return builder.toString();
		}, values);
	}

}
