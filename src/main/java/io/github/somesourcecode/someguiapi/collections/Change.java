package io.github.somesourcecode.someguiapi.collections;

import java.util.Collections;
import java.util.List;

public class Change<E> {

	private final List<? extends E> addedSubList;
	private final List<? extends E> removedSubList;

	private final boolean wasAdded;
	private final boolean wasRemoved;

	public Change(List<? extends E> addedSubList, List<? extends E> removedSubList) {
		this.wasAdded = addedSubList != null && !addedSubList.isEmpty();
		this.wasRemoved = removedSubList != null && !removedSubList.isEmpty();

		this.addedSubList = wasAdded ? Collections.unmodifiableList(addedSubList) : Collections.emptyList();
		this.removedSubList = wasRemoved ? Collections.unmodifiableList(removedSubList) : Collections.emptyList();
	}

	public List<? extends E> getAddedSubList() {
		return addedSubList;
	}

	public List<? extends E> getRemovedSubList() {
		return removedSubList;
	}

	public boolean wasAdded() {
		return wasAdded;
	}

	public boolean wasRemoved() {
		return wasRemoved;
	}

}
