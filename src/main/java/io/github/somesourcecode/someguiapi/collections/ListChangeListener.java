package io.github.somesourcecode.someguiapi.collections;

public interface ListChangeListener<E> {

	void onChanged(Change<? extends E> change);

}
