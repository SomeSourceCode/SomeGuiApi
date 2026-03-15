package io.github.somesourcecode.someguiapi.state;

import java.util.List;

public class SimpleStringStateTest extends AbstractStateTest<String> {

	@Override
	protected State<String> createState(String initialValue) {
		return new SimpleStringState(initialValue);
	}

	@Override
	protected List<String> provideDistinctValues() {
		return List.of("Hello", "World", "Test");
	}

}
