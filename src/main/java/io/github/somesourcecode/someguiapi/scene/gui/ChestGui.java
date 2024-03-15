package io.github.somesourcecode.someguiapi.scene.gui;

import io.github.somesourcecode.someguiapi.scene.Parent;
import io.github.somesourcecode.someguiapi.scene.Scene;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChestGui extends Gui implements InventoryHolder {

	private final List<Player> viewers = new ArrayList<>();

	private Component title;
	private int rows;

	private Scene scene;

	public ChestGui(Component title, int rows) {
		this.title = title;
		this.rows = rows;

		this.inventory = Bukkit.createInventory(this, rows * 9, title);
	}

	@Override
	public void show(Player player) {
		render();
		player.openInventory(inventory);
	}

	public void render() {
		ItemStack[][] pixels = new ItemStack[9][rows];
		Parent root = scene.getRoot();

		final int rootLayoutX = root.getLayoutX();
		final int rootLayoutY = root.getLayoutY();

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < rows; y++) {
				pixels[x][y] = root.pixelAt(x - rootLayoutX, y - rootLayoutY);
			}
		}

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < rows; y++) {
				inventory.setItem(x + y * 9, pixels[x][y]);
			}
		}
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Component getTitle() {
		return title;
	}

	public void setTitle(Component title) {
		if (this.title.equals(title)) {
			return;
		}
		this.title = title;
		inventory = Bukkit.createInventory(this, rows * 9, title);
		update();
	}



	@Override
	public @NotNull Inventory getInventory() {
		return inventory;
	}

	@Override
	public List<Player> getViewers() {
		return viewers;
	}

}
