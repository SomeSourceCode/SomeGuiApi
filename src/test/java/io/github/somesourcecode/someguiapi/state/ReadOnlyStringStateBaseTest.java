package io.github.somesourcecode.someguiapi.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReadOnlyStringStateBaseTest {

	/* *************************************************************** *
	 *                            EQUALITY                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly evaluate isEqualToIgnoreCase and update on change")
	void testIsEqualToIgnoreCase() {
		final SimpleStringState baseState = new SimpleStringState("Java");
		final SimpleStringState compareState = new SimpleStringState("JAVA");

		final ReadOnlyBooleanState rawResult = baseState.isEqualToIgnoreCase("JAVA");
		final ReadOnlyBooleanState stateResult = baseState.isEqualToIgnoreCase(compareState);

		assertTrue(rawResult.get(), "isEqualToIgnoreCase(String) failed to calculate correct initial value");
		assertTrue(stateResult.get(), "isEqualToIgnoreCase(ObservableValue) failed to calculate correct initial value");

		baseState.set("Python");

		assertFalse(rawResult.get(), "isEqualToIgnoreCase(String) failed to update when base state changed");
		assertFalse(stateResult.get(), "isEqualToIgnoreCase(ObservableValue) failed to update when base state changed");

		compareState.set("PYTHON");
		assertTrue(stateResult.get(), "isEqualToIgnoreCase(ObservableValue) failed to update when argument state changed");
	}

	/* *************************************************************** *
	 *                             SEARCH                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly evaluate startsWith and endsWith across variants")
	void testStartsAndEndsWith() {
		final SimpleStringState baseState = new SimpleStringState("SomeGuiApi");
		final SimpleStringState startState = new SimpleStringState("some");
		final SimpleStringState endState = new SimpleStringState("api");

		final ReadOnlyBooleanState startsWith = baseState.startsWith("Some");
		final ReadOnlyBooleanState startsWithState = baseState.startsWith(new SimpleStringState("Some"));
		final ReadOnlyBooleanState startsWithIgnoreCase = baseState.startsWithIgnoreCase("some");
		final ReadOnlyBooleanState startsWithStateIgnoresCase = baseState.startsWithIgnoreCase(startState);

		final ReadOnlyBooleanState endsWith = baseState.endsWith("Api");
		final ReadOnlyBooleanState endsWithState = baseState.endsWith(new SimpleStringState("Api"));
		final ReadOnlyBooleanState endsWithIgnoreCase = baseState.endsWithIgnoreCase("api");
		final ReadOnlyBooleanState endsWithStateIgnoreCase = baseState.endsWithIgnoreCase(endState);

		assertTrue(startsWith.get(), "startsWith(String) failed to calculate correct initial value");
		assertTrue(startsWithState.get(), "startsWith(ObservableValue) failed to calculate correct initial value");
		assertTrue(startsWithIgnoreCase.get(), "startsWithIgnoreCase(String) failed to calculate correct initial value");
		assertTrue(startsWithStateIgnoresCase.get(), "startsWithIgnoreCase(ObservableValue) failed to calculate correct initial value");

		assertTrue(endsWith.get(), "endsWith(String) failed to calculate correct initial value");
		assertTrue(endsWithState.get(), "endsWith(ObservableValue) failed to calculate correct initial value");
		assertTrue(endsWithIgnoreCase.get(), "endsWithIgnoreCase(String) failed to calculate correct initial value");
		assertTrue(endsWithStateIgnoreCase.get(), "endsWithIgnoreCase(ObservableValue) failed to calculate correct initial value");

		baseState.set("OtherFramework");

		assertFalse(startsWith.get(), "startsWith() failed to update");
		assertFalse(endsWith.get(), "endsWith() failed to update");
		assertFalse(startsWithStateIgnoresCase.get(), "startsWithIgnoreCase(ObservableValue) failed to update");

		baseState.set("SomeOtherApi");

		assertTrue(startsWithStateIgnoresCase.get(), "startsWithIgnoreCase(ObservableValue) failed to update on match");
	}

	@Test
	@DisplayName("Should correctly evaluate contains and containsIgnoreCase across variants")
	void testContains() {
		final SimpleStringState baseState = new SimpleStringState("Hello World");
		final SimpleStringState searchState = new SimpleStringState("world");

		final ReadOnlyBooleanState contains = baseState.contains("World");
		final ReadOnlyBooleanState containsState = baseState.contains(new SimpleStringState("World"));
		final ReadOnlyBooleanState containsIgnoreCase = baseState.containsIgnoreCase("world");
		final ReadOnlyBooleanState containsStateIgnoreCase = baseState.containsIgnoreCase(searchState);

		assertTrue(contains.get(), "contains(String) failed to calculate correct initial value");
		assertTrue(containsState.get(), "contains(ObservableValue) failed to calculate correct initial value");
		assertTrue(containsIgnoreCase.get(), "containsIgnoreCase(String) failed to calculate correct initial value");
		assertTrue(containsStateIgnoreCase.get(), "containsIgnoreCase(ObservableValue) failed to calculate correct initial value");

		baseState.set("Hello Java");

		assertFalse(contains.get(), "contains() failed to update when base state changed");
		assertFalse(containsStateIgnoreCase.get(), "containsIgnoreCase(ObservableValue) failed to update when base state changed");

		searchState.set("java");

		assertTrue(containsStateIgnoreCase.get(), "containsIgnoreCase(ObservableValue) failed to update when search argument changed");
	}

	/* *************************************************************** *
	 *                              MATCH                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly evaluate regex matches and update on change")
	void testMatches() {
		final SimpleStringState baseState = new SimpleStringState("12345");
		final SimpleStringState regexState = new SimpleStringState("\\d+");

		final ReadOnlyBooleanState matchRaw = baseState.matches("\\d+");
		final ReadOnlyBooleanState matchState = baseState.matches(regexState);

		assertTrue(matchRaw.get(), "matches(String) failed to calculate correct initial value");
		assertTrue(matchState.get(), "matches(ObservableValue) failed to calculate correct initial value");

		baseState.set("123abc");

		assertFalse(matchRaw.get(), "matches(String) failed to update when base state changed");
		assertFalse(matchState.get(), "matches(ObservableValue) failed to update when base state changed");

		regexState.set("\\w+");

		assertTrue(matchState.get(), "matches(ObservableValue) failed to update when regex argument changed");
	}

	/* *************************************************************** *
	 *                          EMPTY & BLANK                          *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly identify empty and blank states")
	void testEmptyAndBlank() {
		final SimpleStringState baseState = new SimpleStringState("");

		final ReadOnlyBooleanState isEmpty = baseState.isEmpty();
		final ReadOnlyBooleanState isNotEmpty = baseState.isNotEmpty();
		final ReadOnlyBooleanState isBlank = baseState.isBlank();
		final ReadOnlyBooleanState isNotBlank = baseState.isNotBlank();

		assertTrue(isEmpty.get(), "isEmpty() failed to calculate correct initial value");
		assertFalse(isNotEmpty.get(), "isNotEmpty() failed to calculate correct initial value");
		assertTrue(isBlank.get(), "isBlank() failed to calculate correct initial value");
		assertFalse(isNotBlank.get(), "isNotBlank() failed to calculate correct initial value");

		baseState.set("   ");

		assertFalse(isEmpty.get(), "isEmpty() failed to update when base state changed");
		assertTrue(isNotEmpty.get(), "isNotEmpty() failed to update when base state changed");
		assertTrue(isBlank.get(), "isBlank() failed to update when base state changed");
		assertFalse(isNotBlank.get(), "isNotBlank() failed to update when base state changed");

		baseState.set("Text");

		assertFalse(isEmpty.get(), "isEmpty() failed to update when base state changed");
		assertFalse(isBlank.get(), "isBlank() failed to update when base state changed");
		assertTrue(isNotBlank.get(), "isNotBlank() failed to update when base state changed");
	}

	/* *************************************************************** *
	 *                          CONCATENATION                          *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly append and prepend strings")
	void testAppendAndPrepend() {
		final SimpleStringState baseState = new SimpleStringState("Middle");
		final SimpleStringState appendState = new SimpleStringState("End");
		final SimpleStringState prependState = new SimpleStringState("Start");

		final ReadOnlyStringState appended = baseState.append("End");
		final ReadOnlyStringState appendedState = baseState.append(appendState);
		final ReadOnlyStringState prepended = baseState.prepend("Start");
		final ReadOnlyStringState prependedState = baseState.prepend(prependState);

		assertEquals("MiddleEnd", appended.get());
		assertEquals("MiddleEnd", appendedState.get());
		assertEquals("StartMiddle", prepended.get());
		assertEquals("StartMiddle", prependedState.get());

		baseState.set("Core");

		assertEquals("CoreEnd", appendedState.get());
		assertEquals("StartCore", prependedState.get());

		appendState.set("Tail");
		prependState.set("Head");

		assertEquals("CoreTail", appendedState.get());
		assertEquals("HeadCore", prependedState.get());
	}

	/* *************************************************************** *
	 *                             REPLACE                             *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly replace literal strings across all overloads and update on change")
	void testReplace() {
		final SimpleStringState baseState = new SimpleStringState("foo bar foo");
		final SimpleStringState targetState = new SimpleStringState("foo");
		final SimpleStringState replacementState = new SimpleStringState("baz");

		final ReadOnlyStringState rawRaw = baseState.replace("foo", "baz");
		final ReadOnlyStringState stateRaw = baseState.replace(targetState, "baz");
		final ReadOnlyStringState rawState = baseState.replace("foo", replacementState);
		final ReadOnlyStringState stateState = baseState.replace(targetState, replacementState);

		assertEquals("baz bar baz", rawRaw.get(), "replace(String, String) failed to calculate correct initial value");
		assertEquals("baz bar baz", stateRaw.get(), "replace(ObservableValue, String) failed to calculate correct initial value");
		assertEquals("baz bar baz", rawState.get(), "replace(String, ObservableValue) failed to calculate correct initial value");
		assertEquals("baz bar baz", stateState.get(), "replace(ObservableValue, ObservableValue) failed to calculate correct initial value");

		baseState.set("foo foo");

		assertEquals("baz baz", rawRaw.get(), "replace(String, String) failed to update when base state changed");
		assertEquals("baz baz", stateRaw.get(), "replace(ObservableValue, String) failed to update when base state changed");
		assertEquals("baz baz", rawState.get(), "replace(String, ObservableValue) failed to update when base state changed");
		assertEquals("baz baz", stateState.get(), "replace(ObservableValue, ObservableValue) failed to update when base state changed");

		targetState.set("bar");
		replacementState.set("qux");

		assertEquals("baz baz", rawRaw.get(), "replace(String, String) incorrectly updated on argument state change");
		assertEquals("foo foo", stateRaw.get(), "replace(ObservableValue, String) failed to update when target argument changed");
		assertEquals("qux qux", rawState.get(), "replace(String, ObservableValue) failed to update when replacement argument changed");
		assertEquals("foo foo", stateState.get(), "replace(ObservableValue, ObservableValue) failed to update when arguments changed");
	}

	@Test
	@DisplayName("Should correctly replace all regex matches across all overloads and update on change")
	void testReplaceAll() {
		final SimpleStringState baseState = new SimpleStringState("foo1 bar2 foo3");
		final SimpleStringState regexState = new SimpleStringState("foo\\d");
		final SimpleStringState replacementState = new SimpleStringState("baz");

		final ReadOnlyStringState rawRaw = baseState.replaceAll("foo\\d", "baz");
		final ReadOnlyStringState stateRaw = baseState.replaceAll(regexState, "baz");
		final ReadOnlyStringState rawState = baseState.replaceAll("foo\\d", replacementState);
		final ReadOnlyStringState stateState = baseState.replaceAll(regexState, replacementState);

		assertEquals("baz bar2 baz", rawRaw.get(), "replaceAll(String, String) failed to calculate correct initial value");
		assertEquals("baz bar2 baz", stateRaw.get(), "replaceAll(ObservableValue, String) failed to calculate correct initial value");
		assertEquals("baz bar2 baz", rawState.get(), "replaceAll(String, ObservableValue) failed to calculate correct initial value");
		assertEquals("baz bar2 baz", stateState.get(), "replaceAll(ObservableValue, ObservableValue) failed to calculate correct initial value");

		baseState.set("foo8 foo9");

		assertEquals("baz baz", rawRaw.get(), "replaceAll(String, String) failed to update when base state changed");
		assertEquals("baz baz", stateRaw.get(), "replaceAll(ObservableValue, String) failed to update when base state changed");
		assertEquals("baz baz", rawState.get(), "replaceAll(String, ObservableValue) failed to update when base state changed");
		assertEquals("baz baz", stateState.get(), "replaceAll(ObservableValue, ObservableValue) failed to update when base state changed");

		regexState.set("bar\\d");
		replacementState.set("qux");

		assertEquals("baz baz", rawRaw.get(), "replaceAll(String, String) incorrectly updated on argument state change");
		assertEquals("foo8 foo9", stateRaw.get(), "replaceAll(ObservableValue, String) failed to update when regex argument changed");
		assertEquals("qux qux", rawState.get(), "replaceAll(String, ObservableValue) failed to update when replacement argument changed");
		assertEquals("foo8 foo9", stateState.get(), "replaceAll(ObservableValue, ObservableValue) failed to update when arguments changed");
	}

	@Test
	@DisplayName("Should correctly replace the first regex match across all overloads and update on change")
	void testReplaceFirst() {
		final SimpleStringState baseState = new SimpleStringState("foo1 bar2 foo3");
		final SimpleStringState regexState = new SimpleStringState("foo\\d");
		final SimpleStringState replacementState = new SimpleStringState("baz");

		final ReadOnlyStringState rawRaw = baseState.replaceFirst("foo\\d", "baz");
		final ReadOnlyStringState stateRaw = baseState.replaceFirst(regexState, "baz");
		final ReadOnlyStringState rawState = baseState.replaceFirst("foo\\d", replacementState);
		final ReadOnlyStringState stateState = baseState.replaceFirst(regexState, replacementState);

		assertEquals("baz bar2 foo3", rawRaw.get(), "replaceFirst(String, String) failed to calculate correct initial value");
		assertEquals("baz bar2 foo3", stateRaw.get(), "replaceFirst(ObservableValue, String) failed to calculate correct initial value");
		assertEquals("baz bar2 foo3", rawState.get(), "replaceFirst(String, ObservableValue) failed to calculate correct initial value");
		assertEquals("baz bar2 foo3", stateState.get(), "replaceFirst(ObservableValue, ObservableValue) failed to calculate correct initial value");

		baseState.set("foo8 foo9");

		assertEquals("baz foo9", rawRaw.get(), "replaceFirst(String, String) failed to update when base state changed");
		assertEquals("baz foo9", stateRaw.get(), "replaceFirst(ObservableValue, String) failed to update when base state changed");
		assertEquals("baz foo9", rawState.get(), "replaceFirst(String, ObservableValue) failed to update when base state changed");
		assertEquals("baz foo9", stateState.get(), "replaceFirst(ObservableValue, ObservableValue) failed to update when base state changed");

		regexState.set("bar\\d");
		replacementState.set("qux");

		assertEquals("baz foo9", rawRaw.get(), "replaceFirst(String, String) incorrectly updated on argument state change");
		assertEquals("foo8 foo9", stateRaw.get(), "replaceFirst(ObservableValue, String) failed to update when regex argument changed");
		assertEquals("qux foo9", rawState.get(), "replaceFirst(String, ObservableValue) failed to update when replacement argument changed");
		assertEquals("foo8 foo9", stateState.get(), "replaceFirst(ObservableValue, ObservableValue) failed to update when arguments changed");
	}

	/* *************************************************************** *
	 *                             REPEAT                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly repeat strings")
	void testRepeat() {
		final SimpleStringState baseState = new SimpleStringState("A");
		final SimpleIntegerState countState = new SimpleIntegerState(3);

		final ReadOnlyStringState repeat = baseState.repeat(3);
		final ReadOnlyStringState repeatState = baseState.repeat(countState);

		assertEquals("AAA", repeat.get(), "repeat(int) failed to calculate correct initial value");
		assertEquals("AAA", repeatState.get(), "repeat(ObservableValue) failed to calculate correct initial value");

		baseState.set("B");

		assertEquals("BBB", repeat.get(), "repeat(int) failed to update when base state changed");
		assertEquals("BBB", repeatState.get(), "repeat(ObservableValue) failed to update when base state changed");

		countState.set(5);

		assertEquals("BBBBB", repeatState.get(), "repeat(ObservableValue) failed to update when count argument changed");
	}

	/* *************************************************************** *
	 *                            SUBSTRING                            *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly substring across varying arguments and handle bounds")
	void testSubstring() {
		final SimpleStringState baseState = new SimpleStringState("HelloWorld");
		final SimpleIntegerState beginState = new SimpleIntegerState(0);
		final SimpleIntegerState endState = new SimpleIntegerState(5);

		final ReadOnlyStringState sub1 = baseState.substring(5);
		final ReadOnlyStringState sub2 = baseState.substring(beginState, 5);
		final ReadOnlyStringState sub3 = baseState.substring(beginState, endState);

		assertEquals("World", sub1.get());
		assertEquals("Hello", sub2.get());
		assertEquals("Hello", sub3.get());

		baseState.set("Developer");

		assertEquals("oper", sub1.get());
		assertEquals("Devel", sub3.get());

		beginState.set(2);
		endState.set(6);

		assertEquals("velo", sub3.get());
	}

	/* *************************************************************** *
	 *                         TRANSFORMATIONS                         *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly convert string case and update on change")
	void testCaseConversion() {
		final SimpleStringState baseState = new SimpleStringState("Java");

		final ReadOnlyStringState lowerCase = baseState.toLowerCase();
		final ReadOnlyStringState upperCase = baseState.toUpperCase();

		assertEquals("java", lowerCase.get());
		assertEquals("JAVA", upperCase.get());

		baseState.set("API");

		assertEquals("api", lowerCase.get());
		assertEquals("API", upperCase.get());
	}

	@Test
	@DisplayName("Should correctly strip whitespace and update on change")
	void testStripping() {
		final SimpleStringState baseState = new SimpleStringState("  Java  ");

		final ReadOnlyStringState strip = baseState.strip();
		final ReadOnlyStringState stripLeading = baseState.stripLeading();
		final ReadOnlyStringState stripTrailing = baseState.stripTrailing();

		assertEquals("Java", strip.get());
		assertEquals("Java  ", stripLeading.get());
		assertEquals("  Java", stripTrailing.get());

		baseState.set(" API ");

		assertEquals("API", strip.get());
		assertEquals("API ", stripLeading.get());
		assertEquals(" API", stripTrailing.get());
	}

	@Test
	@DisplayName("Should correctly reverse strings and update on change")
	void testReverse() {
		final SimpleStringState baseState = new SimpleStringState("Java");
		final ReadOnlyStringState reverse = baseState.reverse();

		assertEquals("avaJ", reverse.get());

		baseState.set("API");

		assertEquals("IPA", reverse.get());
	}

	/* *************************************************************** *
	 *                             LENGTH                              *
	 * *************************************************************** */

	@Test
	@DisplayName("Should correctly report length and handle nulls")
	void testLength() {
		final SimpleStringState baseState = new SimpleStringState("12345");
		final ReadOnlyIntegerState length = baseState.length();

		assertEquals(5, length.get());

		baseState.set("1234567890");
		assertEquals(10, length.get());

		baseState.set(null);
		assertEquals(0, length.get());
	}

}
