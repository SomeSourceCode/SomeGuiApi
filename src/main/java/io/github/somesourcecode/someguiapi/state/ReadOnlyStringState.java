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

import java.util.regex.PatternSyntaxException;

/**
 * A read-only state of type {@link String}.
 *
 * @since 3.0.0
 */
public interface ReadOnlyStringState extends ReadOnlyObjectState<String> {

	StringState asMutableState();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is equal to the given value ignoring case, {@code false} otherwise, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isEqualToIgnoreCase(String value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is equal to the value of the given {@link ObservableValue} ignoring case, {@code false} otherwise, {@code false} otherwise.
	 *
	 * @param value the observable to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isEqualToIgnoreCase(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * starts with the given value, {@code false} otherwise, {@code false} otherwise.
	 *
	 * @param value the start value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState startsWith(String value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * starts with the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the start value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState startsWith(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * starts with the given value ignoring case, {@code false} otherwise.
	 *
	 * @param value the start value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState startsWithIgnoreCase(String value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * starts with the value of the given {@link ObservableValue} ignoring case, {@code false} otherwise.
	 *
	 * @param value the start value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState startsWithIgnoreCase(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * ends with the given value, {@code false} otherwise.
	 *
	 * @param value the end value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState endsWith(String value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * ends with the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the end value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState endsWith(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * ends with the given value ignoring case, {@code false} otherwise.
	 *
	 * @param value the end value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState endsWithIgnoreCase(String value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * ends with the value of the given {@link ObservableValue} ignoring case, {@code false} otherwise.
	 *
	 * @param value the end value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState endsWithIgnoreCase(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * contains the given value, {@code false} otherwise.
	 *
	 * @param value the value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState contains(String value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * contains the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState contains(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * contains the given value ignoring case, {@code false} otherwise.
	 *
	 * @param value the value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState containsIgnoreCase(String value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * contains the value of the given {@link ObservableValue} ignoring case, {@code false} otherwise.
	 *
	 * @param value the value to check
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState containsIgnoreCase(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * matches the given regular expression, {@code false} otherwise.
	 *
	 * @param regex the regular expression to match
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if regex is null
	 * @throws PatternSyntaxException if the regex is not a valid regular expression
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState matches(String regex);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * matches the value of the given {@link ObservableValue}, {@code false} otherwise.
	 * <p>
	 * If the value of the given observable is not a valid regular expression,
	 * no match will be assumed.
	 *
	 * @param regex the regular expression to match
	 * @return the new {@code ReadOnlyBooleanState}
	 * @throws IllegalArgumentException if regex is null
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState matches(ObservableValue<String> regex);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is empty, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isEmpty();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is not empty, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNotEmpty();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is blank, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBlank();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is not blank, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNotBlank();

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the given value appended to it.
	 * <p>
	 * If the given value is null, "null" will be appended.
	 *
	 * @param value the value to append
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState append(String value);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the value of the given {@link ObservableValue} appended to it.
	 * <p>
	 * If the observable's value is null, "null" will be appended.
	 *
	 * @param value the value to append
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState append(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the given value prepended to it.
	 * <p>
	 * If the given value is null, "null" will be prepended.
	 *
	 * @param value the value to prepend
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState prepend(String value);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the value of the given {@link ObservableValue} prepended to it.
	 * <p>
	 * If the observable's value is null, "null" will be prepended.
	 *
	 * @param value the value to prepend
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if value is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState prepend(ObservableValue<String> value);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the given value replaced by the given literal.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param target the value to replace
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if target is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replace(String target, String replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the value of the given {@link ObservableValue} replaced by the given literal.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param target the value to replace
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if target is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replace(ObservableValue<String> target, String replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the given value replaced by the literal of the given {@link ObservableValue}.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param target the value to replace
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if target is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replace(String target, ObservableValue<String> replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the value of the given {@link ObservableValue} replaced by the literal of the given {@link ObservableValue}.
	 * <p>
	 * If the target value is null, this is a no-op.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param target the value to replace
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if target is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replace(ObservableValue<String> target, ObservableValue<String> replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the given regular expression replaced by the given literal.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @throws PatternSyntaxException if the regex is not a valid regular expression
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceAll(String regex, String replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the regular expression of the given {@link ObservableValue} replaced by the given literal.
	 * <p>
	 * If the value of the given observable is not a valid regular expression,
	 * no match will be assumed.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceAll(ObservableValue<String> regex, String replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the given regular expression replaced by the literal of the given {@link ObservableValue}.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @throws PatternSyntaxException if the regex is not a valid regular expression
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceAll(String regex, ObservableValue<String> replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with every occurrence of the regular expression of the given {@link ObservableValue} replaced by the literal of the given {@link ObservableValue}.
	 * <p>
	 * If the value of the given observable is not a valid regular expression,
	 * no match will be assumed.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceAll(ObservableValue<String> regex, ObservableValue<String> replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the first occurrence of the given regular expression replaced by the given literal.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @throws PatternSyntaxException if the regex is not a valid regular expression
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceFirst(String regex, String replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the first occurrence of the regular expression of the given {@link ObservableValue} replaced by the given literal.
	 * <p>
	 * If the value of the given observable is not a valid regular expression,
	 * no match will be assumed.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceFirst(ObservableValue<String> regex, String replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the first occurrence of the given regular expression replaced by the literal of the given {@link ObservableValue}.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @throws PatternSyntaxException if the regex is not a valid regular expression
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceFirst(String regex, ObservableValue<String> replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with the first occurrence of the regular expression of the given {@link ObservableValue} replaced by the literal of the given {@link ObservableValue}.
	 * <p>
	 * If the value of the given observable is not a valid regular expression,
	 * no match will be assumed.
	 * <p>
	 * If the replacement value is null, the target will just be removed.
	 *
	 * @param regex the regular expression to match
	 * @param replacement the replacement value
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if regex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState replaceFirst(ObservableValue<String> regex, ObservableValue<String> replacement);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the concatenation of the value
	 * of this state repeated {@code count} times.
	 *
	 * @param count the number of times to repeat the string
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if count is less than 0
	 * @since 3.0.0
	 */
	ReadOnlyStringState repeat(int count);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the concatenation of the value
	 * of this state repeated the number of times given by the value of the given {@link ObservableValue}.
	 * <p>
	 * If count is less than or equal to 0, the string will be empty.
	 *
	 * @param count the number of times to repeat the string
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if count is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState repeat(ObservableValue<Integer> count);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the substring of the value
	 * of this state beginning at the given index.
	 * <p>
	 * If beginIndex is larger than the length of the value of this state,
	 * the substring will be empty.
	 *
	 * @param beginIndex the beginning index (inclusive)
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if beginIndex is less than 0
	 * @since 3.0.0
	 */
	ReadOnlyStringState substring(int beginIndex);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the substring of the value
	 * of this state beginning at the index given by the value of the given {@link ObservableValue}.
	 * <p>
	 * If beginIndex is less than 0, the substring will be equal to the value of this state.
	 * <p>
	 * If beginIndex is larger than the length of the value of this state,
	 * the substring will be empty.
	 *
	 * @param beginIndex the beginning index (inclusive)
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if beginIndex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState substring(ObservableValue<Integer> beginIndex);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the substring of the value
	 * of this state beginning at the specified beginIndex and extending to the character
	 * at index endIndex - 1.
	 * <p>
	 * If beginIndex is larger than the length of the value of this state, the substring will be empty.
	 * <p>
	 * If endIndex is larger than the length of the value of this state, the substring will
	 * extend to the end of the string.
	 *
	 * @param beginIndex the starting index (inclusive)
	 * @param endIndex the ending index (exclusive)
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if beginIndex is less than 0 or endIndex is less than beginIndex
	 * @since 3.0.0
	 */
	ReadOnlyStringState substring(int beginIndex, int endIndex);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the substring of the value
	 * of this state beginning at the index given by the value of the given {@link ObservableValue}
	 * and extending to the character at index endIndex - 1.
	 * <p>
	 * If beginIndex is less than 0, the substring will begin at the beginning of the string.
	 * <p>
	 * If beginIndex is larger than the length of the value of this state or larger than endIndex,
	 * the substring will be empty.
	 * <p>
	 * If endIndex is larger than the length of the value of this state, the substring will
	 * extend to the end of the string.
	 *
	 * @param beginIndex the starting index (inclusive)
	 * @param endIndex the ending index (exclusive)
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if beginIndex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState substring(ObservableValue<Integer> beginIndex, int endIndex);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the substring of the value
	 * of this state beginning at the specified beginIndex and extending to the character at index endIndex - 1
	 * of the given {@link ObservableValue}.
	 * <p>
	 * If beginIndex is larger than the length of the value or larger than endIndex, the substring will be empty.
	 * <p>
	 * If endIndex is less than 0, the substring will be empty.
	 *
	 * @param beginIndex the starting index (inclusive)
	 * @param endIndex the ending index (exclusive)
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if beginIndex is less than 0 or endIndex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState substring(int beginIndex, ObservableValue<Integer> endIndex);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the substring of the value
	 * of this state beginning at the index given by the value of the given {@link ObservableValue}
	 * and extending to the character at index endIndex - 1 of the given {@link ObservableValue}.
	 * <p>
	 * If beginIndex is less than 0, the substring will begin at the beginning of the string.
	 * <p>
	 * If beginIndex is larger than the length of the value of this state or larger than endIndex,
	 * the substring will be empty.
	 * <p>
	 * If endIndex is less than 0, the substring will be empty.
	 *
	 * @param beginIndex the starting index (inclusive)
	 * @param endIndex the ending index (exclusive)
	 * @return the new {@code ReadOnlyStringState}
	 * @throws IllegalArgumentException if beginIndex is null or endIndex is null
	 * @since 3.0.0
	 */
	ReadOnlyStringState substring(ObservableValue<Integer> beginIndex, ObservableValue<Integer> endIndex);

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the lowercase value
	 * of this state.
	 *
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState toLowerCase();

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the uppercase value
	 * of this state.
	 *
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState toUpperCase();

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with leading and trailing whitespace removed.
	 *
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState strip();

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with leading whitespace removed.
	 *
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState stripLeading();

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the value of this state
	 * with trailing whitespace removed.
	 *
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState stringTailing();

	/**
	 * Creates a new {@link ReadOnlyStringState} that holds the reversed value
	 * of this state.
	 *
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState reverse();

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the length of the value
	 * of this state.
	 *
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState length();

}
