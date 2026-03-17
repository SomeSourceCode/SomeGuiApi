package io.github.somesourcecode.someguiapi.state;

import java.util.List;

public class SimpleBooleanStateTest extends AbstractStateTest<Boolean> {

	@Override
	protected State<Boolean> createState(Boolean initialValue) {
		return new SimpleBooleanState(initialValue);
	}

	@Override
	protected List<Boolean> provideDistinctValues() {
		return List.of(true, false, true);
	}

}
