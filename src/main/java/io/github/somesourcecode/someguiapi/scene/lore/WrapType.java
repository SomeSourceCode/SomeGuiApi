package io.github.somesourcecode.someguiapi.scene.lore;

/**
 * Represents the type of wrapping that should be applied to a paragraph.
 * It determines where the text should be wrapped when it reaches the end of the line.
 *
 * @since 2.0.0
 */
public enum WrapType {

	/**
	 * No wrapping should be applied.
	 *
	 * @since 2.0.0
	 */
	NONE,

	/**
	 * Wrapping should be applied at the end of the line,
	 * regardless of the position of the last word.
	 *
	 * @since 2.0.0
	 */
	CHARACTER,

	/**
	 * Wrapping should be applied at the end of the line,
	 * but only after the last word.
	 *
	 * @since 2.0.0
	 */
	WORD

}
