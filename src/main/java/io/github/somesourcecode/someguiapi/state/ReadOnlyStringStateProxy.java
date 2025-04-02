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

/**
 * A proxy for {@link ReadOnlyStringState} that forwards all calls to the
 * delegate. This is useful to provide a read-only view of a mutable state.
 *
 * @see ReadOnlyStringStateWrapper
 * @since 3.0.0
 */
public class ReadOnlyStringStateProxy extends ReadOnlyObjectStateProxy<String> implements ReadOnlyStringState {

	private final ReadOnlyStringState delegate;

	/**
	 * Constructs a new proxy for the given {@link ReadOnlyStringState}.
	 *
	 * @param delegate the delegate to forward calls to
	 * @since 3.0.0
	 */
	public ReadOnlyStringStateProxy(ReadOnlyStringState delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public StringState asMutableState() {
		return delegate.asMutableState();
	}

	@Override
	public ReadOnlyBooleanState isEqualToIgnoreCase(String value) {
		return delegate.isEqualToIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState isEqualToIgnoreCase(ObservableValue<String> value) {
		return delegate.isEqualToIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState startsWith(String value) {
		return delegate.startsWith(value);
	}

	@Override
	public ReadOnlyBooleanState startsWith(ObservableValue<String> value) {
		return delegate.startsWith(value);
	}

	@Override
	public ReadOnlyBooleanState startsWithIgnoreCase(String value) {
		return delegate.startsWithIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState startsWithIgnoreCase(ObservableValue<String> value) {
		return delegate.startsWithIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState endsWith(String value) {
		return delegate.endsWith(value);
	}

	@Override
	public ReadOnlyBooleanState endsWith(ObservableValue<String> value) {
		return delegate.endsWith(value);
	}

	@Override
	public ReadOnlyBooleanState endsWithIgnoreCase(String value) {
		return delegate.endsWithIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState endsWithIgnoreCase(ObservableValue<String> value) {
		return delegate.endsWithIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState contains(String value) {
		return delegate.contains(value);
	}

	@Override
	public ReadOnlyBooleanState contains(ObservableValue<String> value) {
		return delegate.contains(value);
	}

	@Override
	public ReadOnlyBooleanState containsIgnoreCase(String value) {
		return delegate.containsIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState containsIgnoreCase(ObservableValue<String> value) {
		return delegate.containsIgnoreCase(value);
	}

	@Override
	public ReadOnlyBooleanState matches(String regex) {
		return delegate.matches(regex);
	}

	@Override
	public ReadOnlyBooleanState matches(ObservableValue<String> regex) {
		return delegate.matches(regex);
	}

	@Override
	public ReadOnlyBooleanState isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public ReadOnlyBooleanState isNotEmpty() {
		return delegate.isNotEmpty();
	}

	@Override
	public ReadOnlyBooleanState isBlank() {
		return delegate.isBlank();
	}

	@Override
	public ReadOnlyBooleanState isNotBlank() {
		return delegate.isNotBlank();
	}

	@Override
	public ReadOnlyStringState append(String value) {
		return delegate.append(value);
	}

	@Override
	public ReadOnlyStringState append(ObservableValue<String> value) {
		return delegate.append(value);
	}

	@Override
	public ReadOnlyStringState prepend(String value) {
		return delegate.prepend(value);
	}

	@Override
	public ReadOnlyStringState prepend(ObservableValue<String> value) {
		return delegate.prepend(value);
	}

	@Override
	public ReadOnlyStringState replace(String target, String replacement) {
		return delegate.replace(target, replacement);
	}

	@Override
	public ReadOnlyStringState replace(ObservableValue<String> target, String replacement) {
		return delegate.replace(target, replacement);
	}

	@Override
	public ReadOnlyStringState replace(String target, ObservableValue<String> replacement) {
		return delegate.replace(target, replacement);
	}

	@Override
	public ReadOnlyStringState replace(ObservableValue<String> target, ObservableValue<String> replacement) {
		return delegate.replace(target, replacement);
	}

	@Override
	public ReadOnlyStringState replaceAll(String regex, String replacement) {
		return delegate.replaceAll(regex, replacement);
	}

	@Override
	public ReadOnlyStringState replaceAll(ObservableValue<String> regex, String replacement) {
		return delegate.replaceAll(regex, replacement);
	}

	@Override
	public ReadOnlyStringState replaceAll(String regex, ObservableValue<String> replacement) {
		return delegate.replaceAll(regex, replacement);
	}

	@Override
	public ReadOnlyStringState replaceAll(ObservableValue<String> regex, ObservableValue<String> replacement) {
		return delegate.replaceAll(regex, replacement);
	}

	@Override
	public ReadOnlyStringState replaceFirst(String regex, String replacement) {
		return delegate.replaceFirst(regex, replacement);
	}

	@Override
	public ReadOnlyStringState replaceFirst(ObservableValue<String> regex, String replacement) {
		return delegate.replaceFirst(regex, replacement);
	}

	@Override
	public ReadOnlyStringState replaceFirst(String regex, ObservableValue<String> replacement) {
		return delegate.replaceFirst(regex, replacement);
	}

	@Override
	public ReadOnlyStringState replaceFirst(ObservableValue<String> regex, ObservableValue<String> replacement) {
		return delegate.replaceFirst(regex, replacement);
	}

	@Override
	public ReadOnlyStringState repeat(int count) {
		return delegate.repeat(count);
	}

	@Override
	public ReadOnlyStringState repeat(ObservableValue<Integer> count) {
		return delegate.repeat(count);
	}

	@Override
	public ReadOnlyStringState substring(int beginIndex) {
		return delegate.substring(beginIndex);
	}

	@Override
	public ReadOnlyStringState substring(ObservableValue<Integer> beginIndex) {
		return delegate.substring(beginIndex);
	}

	@Override
	public ReadOnlyStringState substring(int beginIndex, int endIndex) {
		return delegate.substring(beginIndex, endIndex);
	}

	@Override
	public ReadOnlyStringState substring(ObservableValue<Integer> beginIndex, int endIndex) {
		return delegate.substring(beginIndex, endIndex);
	}

	@Override
	public ReadOnlyStringState substring(int beginIndex, ObservableValue<Integer> endIndex) {
		return delegate.substring(beginIndex, endIndex);
	}

	@Override
	public ReadOnlyStringState substring(ObservableValue<Integer> beginIndex, ObservableValue<Integer> endIndex) {
		return delegate.substring(beginIndex, endIndex);
	}

	@Override
	public ReadOnlyStringState toLowerCase() {
		return delegate.toLowerCase();
	}

	@Override
	public ReadOnlyStringState toUpperCase() {
		return delegate.toUpperCase();
	}

	@Override
	public ReadOnlyStringState strip() {
		return delegate.strip();
	}

	@Override
	public ReadOnlyStringState stripLeading() {
		return delegate.stripLeading();
	}

	@Override
	public ReadOnlyStringState stringTailing() {
		return delegate.stringTailing();
	}

	@Override
	public ReadOnlyStringState reverse() {
		return delegate.reverse();
	}

	@Override
	public ReadOnlyIntegerState length() {
		return delegate.length();
	}

}
