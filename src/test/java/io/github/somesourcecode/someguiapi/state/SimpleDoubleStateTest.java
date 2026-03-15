package io.github.somesourcecode.someguiapi.state;

import java.util.List;

public class SimpleDoubleStateTest extends AbstractStateTest<Double> {

	@Override
	protected State<Double> createState(Double initialValue) {
		return new SimpleDoubleState(initialValue);
	}

	@Override
	protected List<Double> provideDistinctValues() {
		return List.of(3.141, 2.718, -1.618);
	}

}
