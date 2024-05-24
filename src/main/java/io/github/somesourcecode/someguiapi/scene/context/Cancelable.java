package io.github.somesourcecode.someguiapi.scene.context;

/**
 * A cancelable event.
 *
 * @since 2.1.0
 */
public interface Cancelable {

	/**
	 * Returns the cancellation state of the event.
	 *
	 * @return whether the event is canceled
	 * @since 2.1.0
	 */
	boolean isCanceled();

	/**
	 * Sets the cancellation state of the event.
	 *
	 * @param canceled the cancellation state
	 * @since 2.1.0
	 */
	void setCanceled(boolean canceled);

	/**
	 * Cancels the event. This is a shorthand for {@code setCanceled(true)}.
	 *
	 * @see #setCanceled(boolean)
	 * @since 2.1.0
	 */
	default void cancel() {
		setCanceled(true);
	}

}