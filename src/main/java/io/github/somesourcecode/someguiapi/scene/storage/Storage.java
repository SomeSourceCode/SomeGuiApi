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

package io.github.somesourcecode.someguiapi.scene.storage;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * A storage for values that can be registered and accessed by id.
 *
 * @since 2.1.0
 */
public class Storage {

	private final HashMap<String, ValueHolder<?>> idToHolder = new HashMap<>();

	/**
	 * Registers a new holder with the specified id, type, and default value.
	 * If the id is already registered, this method does nothing.
	 *
	 * @param id the id
	 * @param type the type
	 * @param defaultValue the default value
	 * @param <T> the type
	 * @since 2.1.0
	 */
	public <T> void register(String id, Class<T> type, T defaultValue) {
		if (isRegistered(id)) {
			return;
		}
		idToHolder.put(id, new ValueHolder<>(type, defaultValue, true));
	}

	/**
	 * Registers a new holder with the specified id and type.
	 * If the id is already registered, this method does nothing.
	 *
	 * @param id the id
	 * @param type the type
	 * @param <T> the type
	 * @since 2.1.0
	 */
	public <T> void register(String id, Class<T> type) {
		if (isRegistered(id)) {
			return;
		}
		idToHolder.put(id, new ValueHolder<>(type, true));
	}

	/**
	 * Returns a list of all registered ids.
	 *
	 * @return a list of all registered ids
	 * @since 2.1.0
	 */
	public List<String> getRegisteredIds() {
		return List.copyOf(idToHolder.keySet());
	}

	/**
	 * Unregisters the holder with the specified id.
	 *
	 * @param id the id
	 * @since 2.1.0
	 */
	public void unregister(String id) {
		idToHolder.remove(id);
	}

	/**
	 * Returns whether the specified id is registered.
	 *
	 * @param id the id
	 * @return whether the id is registered
	 * @since 2.1.0
	 */
	public boolean isRegistered(String id) {
		return idToHolder.containsKey(id);
	}

	/**
	 * Returns the value holder for the specified id. If no holder
	 * is registered with the specified id, this method returns an
	 * empty dummy holder.
	 *
	 * @param id the id
	 * @return the value holder
	 * @since 2.1.0
	 */
	public ValueHolder<?> get(String id) {
		return isRegistered(id) ? idToHolder.get(id) : new ValueHolder<>(Object.class, null, false);
	}

	/**
	 * Executes the consumer if a holder with the specified
	 * id is registered. Does nothing otherwise.
	 *
	 * @param id the id
	 * @param consumer the consumer
	 * @since 2.1.0
	 */
	public void ifRegistered(String id, Consumer<ValueHolder<?>> consumer) {
		if (!isRegistered(id) || consumer == null) {
			return;
		}
		consumer.accept(get(id));
	}

}
