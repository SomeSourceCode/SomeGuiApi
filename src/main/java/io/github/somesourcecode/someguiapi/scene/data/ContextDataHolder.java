/*
 * Copyright 2024, SomeSourceCode - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.somesourcecode.someguiapi.scene.data;

import java.util.HashMap;
import java.util.Optional;

/**
 * A container for context data. It is used to register, store and retrieve context data.
 * The data is represented by {@link ContextData} objects and can be accessed by their id.
 * @since 2.0.0
 */
public class ContextDataHolder {

	private final HashMap<String, ContextData<?>> idToData = new HashMap<>();

	/**
	 * Registers the given context data.
	 * <p>
	 * If the id is already registered, this method will fail silently.
	 * @param data the data to register
	 * @return the holder for method chaining
	 * @since 2.0.0
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
	 * @since 2.0.0
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
	 * @since 2.0.0
	 */
	public <T> ContextDataHolder register(String id, Class<T> type, T data) {
		return this.register(new ContextData<>(id, type, data));
	}

	/**
	 * Returns the context data with the given id.
	 * @param id the id of the data
	 * @return the data with the given id, or null if no data with the given id is registered
	 * @since 2.0.0
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
	 * @since 2.0.0
	 */
	public <T> ContextData<T> getDataUnchecked(String id) {
		return (ContextData<T>) this.idToData.get(id);
	}

	/**
	 * Returns the context data with the given id as an optional.
	 * @param id the id of the data
	 * @return the data with the given id as an optional
	 * @since 2.0.0
	 */
	public Optional<ContextData<?>> getOptionalData(String id) {
		return Optional.ofNullable(this.idToData.get(id));
	}

	/**
	 * Unregisters the context data with the given id.
	 * @param id the id of the data
	 * @return the holder for method chaining
	 * @since 2.0.0
	 */
	public ContextDataHolder unregister(String id) {
		this.idToData.remove(id);
		return this;
	}

	/**
	 * Unregisters all context data.
	 * @return the holder for method chaining
	 * @since 2.0.0
	 */
	public ContextDataHolder unregisterAll() {
		this.idToData.clear();
		return this;
	}

}
