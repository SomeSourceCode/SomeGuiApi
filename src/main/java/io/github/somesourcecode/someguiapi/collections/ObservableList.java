package io.github.somesourcecode.someguiapi.collections;

import java.util.List;

public interface ObservableList<E> extends List<E> {

	void addListener(ListChangeListener<? super E> listener);

	void removeListener(ListChangeListener<? super E> listener);

}
