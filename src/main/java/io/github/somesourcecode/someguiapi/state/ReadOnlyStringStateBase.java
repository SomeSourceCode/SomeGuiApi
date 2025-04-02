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

import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The base class for all string states.
 *
 * @since 3.0.0
 */
public abstract class ReadOnlyStringStateBase extends ReadOnlyObjectStateBase<String> implements ReadOnlyStringState {

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	public StringState asMutableState() {
		final StringState state = new SimpleStringState(get());
		state.reflect(this);
		return state;
	}

	@Override
	public ReadOnlyBooleanState isEqualToIgnoreCase(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return Objects.equals(thisString, value) || (thisString != null && thisString.equalsIgnoreCase(value));
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isEqualToIgnoreCase(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String otherString = value.get();
			return Objects.equals(thisString, otherString) || (thisString != null && thisString.equalsIgnoreCase(otherString));
		}, this, value);
	}

	@Override
	public ReadOnlyBooleanState startsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && thisString.startsWith(value);
		}, this);
	}

	@Override
	public ReadOnlyBooleanState startsWith(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String otherString = value.get();
			return thisString != null && otherString != null && thisString.startsWith(otherString);
		}, this, value);
	}

	@Override
	public ReadOnlyBooleanState startsWithIgnoreCase(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && thisString.toLowerCase().startsWith(value.toLowerCase());
		}, this);
	}

	@Override
	public ReadOnlyBooleanState startsWithIgnoreCase(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String otherString = value.get();
			return thisString != null && otherString != null && thisString.toLowerCase().startsWith(otherString.toLowerCase());
		}, this, value);
	}

	@Override
	public ReadOnlyBooleanState endsWith(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && thisString.endsWith(value);
		}, this);
	}

	@Override
	public ReadOnlyBooleanState endsWith(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String otherString = value.get();
			return thisString != null && otherString != null && thisString.endsWith(otherString);
		}, this, value);
	}

	@Override
	public ReadOnlyBooleanState endsWithIgnoreCase(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && thisString.toLowerCase().endsWith(value.toLowerCase());
		}, this);
	}

	@Override
	public ReadOnlyBooleanState endsWithIgnoreCase(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String otherString = value.get();
			return thisString != null && otherString != null && thisString.toLowerCase().endsWith(otherString.toLowerCase());
		}, this, value);
	}

	@Override
	public ReadOnlyBooleanState contains(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && thisString.contains(value);
		}, this);
	}

	@Override
	public ReadOnlyBooleanState contains(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String otherString = value.get();
			return thisString != null && otherString != null && thisString.contains(otherString);
		}, this, value);
	}

	@Override
	public ReadOnlyBooleanState containsIgnoreCase(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && thisString.toLowerCase().contains(value.toLowerCase());
		}, this);
	}

	@Override
	public ReadOnlyBooleanState containsIgnoreCase(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String otherString = value.get();
			return thisString != null && otherString != null && thisString.toLowerCase().contains(otherString.toLowerCase());
		}, this, value);
	}

	@Override
	public ReadOnlyBooleanState matches(String regex) {
		if (regex == null) {
			throw new IllegalArgumentException("regex must be non-null");
		}
		try {
			final Pattern pattern = Pattern.compile(regex);
			return Reflect.createBooleanReflection(() -> {
				final String thisString = get();
				return thisString != null && pattern.matcher(thisString).matches();
			}, this);
		} catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("Invalid regex", e);
		}
	}

	@Override
	public ReadOnlyBooleanState matches(ObservableValue<String> regex) {
		if (regex == null) {
			throw new IllegalArgumentException("regex must be non-null");
		}
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			final String regexString = regex.get();
			if (thisString == null || regexString == null) {
				return false;
			}
			try {
				final Pattern pattern = Pattern.compile(regexString);
				return pattern.matcher(thisString).matches();
			} catch (PatternSyntaxException e) {
				return false;
			}
		}, this, regex);
	}

	@Override
	public ReadOnlyBooleanState isEmpty() {
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString == null || thisString.isEmpty();
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isNotEmpty() {
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && !thisString.isEmpty();
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isBlank() {
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString == null || thisString.isBlank();
		}, this);
	}

	@Override
	public ReadOnlyBooleanState isNotBlank() {
		return Reflect.createBooleanReflection(() -> {
			final String thisString = get();
			return thisString != null && !thisString.isBlank();
		}, this);
	}

	@Override
	public ReadOnlyStringState append(String value) {
		return Reflect.createStringReflection(() -> get() + value, this);
	}

	@Override
	public ReadOnlyStringState append(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createStringReflection(() -> get() + value.get(), this, value);
	}

	@Override
	public ReadOnlyStringState prepend(String value) {
		return Reflect.createStringReflection(() -> value + get(), this);
	}

	@Override
	public ReadOnlyStringState prepend(ObservableValue<String> value) {
		if (value == null) {
			throw new IllegalArgumentException("value must be non-null");
		}
		return Reflect.createStringReflection(() -> value.get() + get(), this, value);
	}

	private interface Replacer {
		String replace(String string, String target, String replacement);
	}

	private ReadOnlyStringState createReplaceReflection(Supplier<String> targetSupplier, Supplier<String> replacementSupplier, Replacer replacer, ObservableValue<?>... dependencies) {
		return Reflect.createStringReflection(() -> {
			final String thisString = get();
			if (thisString == null) {
				return null;
			}
			final String target = targetSupplier.get();
			if (target == null) {
				return thisString;
			}
			final String replacement = replacementSupplier.get();
			return replacer.replace(thisString, target, replacement == null ? "" : replacement);
		}, dependencies);
	}

	@Override
	public ReadOnlyStringState replace(String target, String replacement) {
		if (target == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		return createReplaceReflection(
				() -> target,
				() -> replacement,
				String::replace,
				this
		);
	}

	@Override
	public ReadOnlyStringState replace(ObservableValue<String> target, String replacement) {
		if (target == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		return createReplaceReflection(
				target::get,
				() -> replacement,
				String::replace,
				this, target
		);
	}

	@Override
	public ReadOnlyStringState replace(String target, ObservableValue<String> replacement) {
		if (target == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		if (replacement == null) {
			throw new IllegalArgumentException("Replacement cannot be null");
		}
		return createReplaceReflection(
				() -> target,
				replacement::get,
				String::replace,
				this, replacement
		);
	}

	@Override
	public ReadOnlyStringState replace(ObservableValue<String> target, ObservableValue<String> replacement) {
		if (target == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		if (replacement == null) {
			throw new IllegalArgumentException("Replacement cannot be null");
		}
		return createReplaceReflection(
				target::get,
				replacement::get,
				String::replace,
				this, target, replacement
		);
	}

	@Override
	public ReadOnlyStringState replaceAll(String regex, String replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		try {
			final Pattern pattern = Pattern.compile(regex);
			return createReplaceReflection(
					() -> regex,
					() -> replacement,
					(s, t, r) -> pattern.matcher(s).replaceAll(r),
					this
			);
		} catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("Invalid regex", e);
		}
	}

	@Override
	public ReadOnlyStringState replaceAll(ObservableValue<String> regex, String replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		return createReplaceReflection(
				regex::get,
				() -> replacement,
				(s, t, r) -> {
					try {
						return s.replaceAll(t, r);
					} catch (PatternSyntaxException e) {
						return s;
					}
				},
				this, regex
		);
	}

	@Override
	public ReadOnlyStringState replaceAll(String regex, ObservableValue<String> replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		if (replacement == null) {
			throw new IllegalArgumentException("Replacement cannot be null");
		}
		try {
			final Pattern pattern = Pattern.compile(regex);
			return createReplaceReflection(
					() -> regex,
					replacement::get,
					(s, t, r) -> pattern.matcher(s).replaceAll(r),
					this, replacement
			);
		} catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("Invalid regex", e);
		}
	}

	@Override
	public ReadOnlyStringState replaceAll(ObservableValue<String> regex, ObservableValue<String> replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		if (replacement == null) {
			throw new IllegalArgumentException("Replacement cannot be null");
		}
		return createReplaceReflection(
				regex::get,
				replacement::get,
				(s, t, r) -> {
					try {
						return s.replaceAll(t, r);
					} catch (PatternSyntaxException e) {
						return s;
					}
				},
				this, regex, replacement
		);
	}

	@Override
	public ReadOnlyStringState replaceFirst(String regex, String replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		try {
			final Pattern pattern = Pattern.compile(regex);
			return createReplaceReflection(
					() -> regex,
					() -> replacement,
					(s, t, r) -> pattern.matcher(s).replaceFirst(r),
					this
			);
		} catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("Invalid regex", e);
		}
	}

	@Override
	public ReadOnlyStringState replaceFirst(ObservableValue<String> regex, String replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		return createReplaceReflection(
				regex::get,
				() -> replacement,
				(s, t, r) -> {
					try {
						return s.replaceFirst(t, r);
					} catch (PatternSyntaxException e) {
						return s;
					}
				},
				this, regex
		);
	}

	@Override
	public ReadOnlyStringState replaceFirst(String regex, ObservableValue<String> replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		if (replacement == null) {
			throw new IllegalArgumentException("Replacement cannot be null");
		}
		try {
			final Pattern pattern = Pattern.compile(regex);
			return createReplaceReflection(
					() -> regex,
					replacement::get,
					(s, t, r) -> pattern.matcher(s).replaceFirst(r),
					this, replacement
			);
		} catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("Invalid regex", e);
		}
	}

	@Override
	public ReadOnlyStringState replaceFirst(ObservableValue<String> regex, ObservableValue<String> replacement) {
		if (regex == null) {
			throw new IllegalArgumentException("Target cannot be null");
		}
		if (replacement == null) {
			throw new IllegalArgumentException("Replacement cannot be null");
		}
		return createReplaceReflection(
				regex::get,
				replacement::get,
				(s, t, r) -> {
					try {
						return s.replaceFirst(t, r);
					} catch (PatternSyntaxException e) {
						return s;
					}
				},
				this, regex, replacement
		);
	}
	
	@Override
	public ReadOnlyStringState repeat(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Count must be non-negative");
		}
		return Reflect.createStringReflection(() -> {
			final String thisString = get();
			if (thisString == null) {
				return null;
			}
			return thisString.repeat(count);
		}, this);
	}

	@Override
	public ReadOnlyStringState repeat(ObservableValue<Integer> count) {
		if (count == null) {
			throw new IllegalArgumentException("Count cannot be null");
		}
		Reflect.createStringReflection(() -> get() == null ? null : get().repeat(count.get()), this, count);
		return Reflect.createStringReflection(() -> {
			final String thisString = get();
			if (thisString == null) {
				return null;
			}
			final int countInt = count.get();
			if (countInt < 0) {
				return "";
			}
			return thisString.repeat(count.get());
		}, this, count);
	}
	
	private ReadOnlyStringState createSubstringReflection(Supplier<Integer> beginIndexSupplier, Supplier<Integer> endIndexSupplier, ObservableValue<?>... dependencies) {
		return Reflect.createStringReflection(() -> {
			final String thisString = get();
			if (thisString == null) {
				return null;
			}
			final int beginIndex = beginIndexSupplier == null ? 0 : beginIndexSupplier.get();
			final int endIndex = endIndexSupplier == null ? thisString.length() : endIndexSupplier.get();
			if (endIndex < 0 || beginIndex > thisString.length() || beginIndex > endIndex) {
				return "";
			}
			if (endIndex > thisString.length()) {
				return thisString.substring(beginIndex);
			}
			return thisString.substring(Math.max(beginIndex, 0), endIndex);
		}, dependencies);
	}

	@Override
	public ReadOnlyStringState substring(int beginIndex) {
		if (beginIndex < 0) {
			throw new IllegalArgumentException("Begin index must be non-negative");
		}
		return createSubstringReflection(() -> beginIndex, null, this);
	}

	@Override
	public ReadOnlyStringState substring(ObservableValue<Integer> beginIndex) {
		if (beginIndex == null) {
			throw new IllegalArgumentException("Begin index cannot be null");
		}
		return createSubstringReflection(beginIndex::get, null, this, beginIndex);
	}

	@Override
	public ReadOnlyStringState substring(int beginIndex, int endIndex) {
		if (beginIndex < 0) {
			throw new IllegalArgumentException("Begin index must be non-negative");
		}
		if (endIndex < beginIndex) {
			throw new IllegalArgumentException("End index must be greater than or equal to begin index");
		}
		return createSubstringReflection(() -> beginIndex, () -> endIndex, this);
	}

	@Override
	public ReadOnlyStringState substring(ObservableValue<Integer> beginIndex, int endIndex) {
		if (beginIndex == null) {
			throw new IllegalArgumentException("Begin index cannot be null");
		}
		return createSubstringReflection(beginIndex::get, () -> endIndex, this, beginIndex);
	}

	@Override
	public ReadOnlyStringState substring(int beginIndex, ObservableValue<Integer> endIndex) {
		if (beginIndex < 0) {
			throw new IllegalArgumentException("Begin index must be non-negative");
		}
		if (endIndex == null) {
			throw new IllegalArgumentException("End index cannot be null");
		}
		return createSubstringReflection(() -> beginIndex, endIndex::get, this, endIndex);
	}

	@Override
	public ReadOnlyStringState substring(ObservableValue<Integer> beginIndex, ObservableValue<Integer> endIndex) {
		if (beginIndex == null) {
			throw new IllegalArgumentException("Begin index cannot be null");
		}
		if (endIndex == null) {
			throw new IllegalArgumentException("End index cannot be null");
		}
		return createSubstringReflection(beginIndex::get, endIndex::get, this, beginIndex, endIndex);
	}

	@Override
	public ReadOnlyStringState toLowerCase() {
		return createMethodForwardStringReflection(String::toLowerCase, this);
	}

	@Override
	public ReadOnlyStringState toUpperCase() {
		return createMethodForwardStringReflection(String::toUpperCase, this);
	}

	@Override
	public ReadOnlyStringState strip() {
		return createMethodForwardStringReflection(String::strip, this);
	}

	@Override
	public ReadOnlyStringState stripLeading() {
		return createMethodForwardStringReflection(String::stripLeading, this);
	}

	@Override
	public ReadOnlyStringState stringTailing() {
		return createMethodForwardStringReflection(String::stripTrailing, this);
	}

	@Override
	public ReadOnlyStringState reverse() {
		return createMethodForwardStringReflection(thisString -> new StringBuilder(thisString).reverse().toString(), this);
	}

	@Override
	public ReadOnlyIntegerState length() {
		return Reflect.createIntegerReflection(() -> {
			final String thisString = get();
			return thisString == null ? 0 : thisString.length();
		}, this);
	}

}
