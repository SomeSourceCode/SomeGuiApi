package io.github.somesourcecode.someguiapi.state;

import java.util.List;

public class SimpleObjectStateTest extends AbstractStateTest<Object> {

	@Override
	protected State<Object> createState(Object initialValue) {
		return new SimpleObjectState<>(initialValue);
	}

	@Override
	protected List<Object> provideDistinctValues() {
		return List.of("Hello World", 42, new Object());
	}

}
