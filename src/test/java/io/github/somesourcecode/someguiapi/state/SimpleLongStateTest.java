package io.github.somesourcecode.someguiapi.state;

import java.util.List;

public class SimpleLongStateTest extends AbstractStateTest<Long> {

	@Override
	protected State<Long> createState(Long initialValue) {
		return new SimpleLongState(initialValue);
	}

	@Override
	protected List<Long> provideDistinctValues() {
		return List.of(7L, 42L, -1L);
	}

}
