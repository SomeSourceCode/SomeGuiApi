package io.github.somesourcecode.someguiapi.state;

import java.util.List;

public class SimpleFloatStateTest extends AbstractStateTest<Float> {

	@Override
	protected State<Float> createState(Float initialValue) {
		return new SimpleFloatState(initialValue);
	}

	@Override
	protected List<Float> provideDistinctValues() {
		return List.of(3.141f, 2.718f, -1.618f);
	}

}
