package io.github.somesourcecode.someguiapi.scene.context;

/**
 * A consumable event.
 *
 * @since 2.1.0
 */
public interface Consumable {

	/**
	 * Check if the event was consumed.
	 *
	 * @return if the event was consumed
	 * @since 2.1.0
	 */
	boolean isConsumed();

	/**
	 * Consumes the click.
	 * Once a click has been consumed, {@link #isConsumed()} will return {@code true}.
	 *
	 * @see #isConsumed()
	 * @since 2.1.0
	 */
	void consume();

}
