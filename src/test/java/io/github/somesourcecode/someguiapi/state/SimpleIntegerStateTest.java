package io.github.somesourcecode.someguiapi.state;

import java.util.List;

public class SimpleIntegerStateTest extends AbstractStateTest<Integer> {

	@Override
	protected State<Integer> createState(Integer initialValue) {
		return new SimpleIntegerState(initialValue);
	}

	@Override
	protected List<Integer> provideDistinctValues() {
		return List.of(7, 42, -1);
	}

}
