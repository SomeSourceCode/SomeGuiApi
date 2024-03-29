package io.github.somesourcecode.someguiapi.scene.data;

import java.util.HashMap;
import java.util.Optional;

/**
 * A container for context data. It is used to register, store and retrieve context data.
 * The data is represented by {@link ContextData} objects and can be accessed by their id.
 */
public class ContextDataHolder {

	private final HashMap<String, ContextData<?>> idToData = new HashMap<>();

	/**
	 * Registers the given context data.
	 * <p>
	 * If the id is already registered, this method will fail silently.
	 * @param data the data to register
	 * @return the holder for method chaining
	 */
	public ContextDataHolder register(ContextData<?> data) {
		if (data == null || data.getId() == null) {
			return this;
		}
		this.idToData.put(data.getId(), data);
		return this;
	}

	/**
	 * Registers a new context data with the given id, class and data.
	 * This method is a shorthand for {@link #register(ContextData)}.
	 * <p>
	 * If the id is already registered, this method will fail silently.
	 * @param id the id of the data
	 * @param type the type of the data
	 * @return the holder for method chaining
	 * @param <T> the type of the data
	 */
	public <T> ContextDataHolder register(String id, Class<T> type) {
		return this.register(new ContextData<>(id, type));
	}

	/**
	 * Registers a new context data with the given id, class and data.
	 * This method is a shorthand for {@link #register(ContextData)}.
	 * <p>
	 * If the id is already registered, this method will fail silently.
	 * @param id the id of the data
	 * @param type the type of the data
	 * @param data the initial data
	 * @return the holder for method chaining
	 * @param <T> the type of the data
	 */
	public <T> ContextDataHolder register(String id, Class<T> type, T data) {
		return this.register(new ContextData<>(id, type, data));
	}

	/**
	 * Returns the context data with the given id.
	 * @param id the id of the data
	 * @return the data with the given id, or null if no data with the given id is registered
	 */
	public ContextData<?> getData(String id) {
		return this.idToData.get(id);
	}

	/**
	 * Returns the context data with the given id and an unchecked data type.
	 * This is useful if the type of the data is known.
	 * @param id the id of the data
	 * @return the data with the given id
	 * @throws ClassCastException if the data is not of the correct type
	 */
	public <T> ContextData<T> getDataUnchecked(String id) {
		return (ContextData<T>) this.idToData.get(id);
	}

	/**
	 * Returns the context data with the given id as an optional.
	 * @param id the id of the data
	 * @return the data with the given id as an optional
	 */
	public Optional<ContextData<?>> getOptionalData(String id) {
		return Optional.ofNullable(this.idToData.get(id));
	}

	/**
	 * Unregisters the context data with the given id.
	 * @param id the id of the data
	 * @return the holder for method chaining
	 */
	public ContextDataHolder unregister(String id) {
		this.idToData.remove(id);
		return this;
	}

	/**
	 * Unregisters all context data.
	 * @return the holder for method chaining
	 */
	public ContextDataHolder unregisterAll() {
		this.idToData.clear();
		return this;
	}

}
