# SomeGuiApi

SomeGuiApi is a versatile Java library designed for effortless creation and management 
of Graphical User Interfaces (GUIs) on Paper Minecraft servers. With intuitive methods 
and flexible components, developers can quickly build interactive GUIs to enhance the 
player experience within their Minecraft worlds.

## Features

- **Simple API**: Create GUIs with ease using a simple and intuitive API.
- **Flexible Components**: Support for various layout panes for flexible design.
- **Interaction**: Click handling for interactive elements.

## Usage

### Coordinate System

SomeGuiApi uses the typical computer coordinate system, where the origin (0,0) is located 
at the top-left corner of the GUI. The x-coordinate increases from left to right, and the 
y-coordinate increases from top to bottom.

### Creating a Gui

Creating a new GUI with SomeGuiApi is straightforward. Below is an example of creating a `ChestGui`:

```java
ChestGui gui = new ChestGui(Component.text("My GUI"), 3);
gui.show(player);
```
This code snippet creates a new ChestGui with the `title My GUI` and `3 rows`, and then displays
it to the specified player.

### GuiItem

A `GuiItem` represents a clickable item within the GUI. You can customize its appearance and behavior 
using various properties such as `material`, `title`, `lore`, and `click events`.

```java
GuiItem item = new GuiItem();
item.setMaterial(Material.DIAMOND_SWORD);
item.setTitle("Sword");
item.setLore("A powerful weapon");
item.setOnClick(context -> {
    Player player = context.getPlayer();
    player.sendMessage("You clicked the sword!");
});
```

### Adding Content

To add content to the GUI, you need to define a `Scene` containing a `root` Parent node to hold the content.
Here's how you can do it:

```java
ChestGui gui = new ChestGui(Component.text("My GUI"), 3);

// Create a layout pane to hold the content
Pane root = new Pane();
root.resize(9, 3);

// Create GuiItems or other nodes to add to the layout
GuiItem item1 = createGuiItem(/* Item details */);
GuiItem item2 = createGuiItem(/* Item details */);

// Add the items to the layout
root.getChildren().addAll(item1, item2);

// create and set a Scene with the root node for the GUI
Scene scene = new Scene(root);
gui.setScene(scene);

// Show the GUI to the player
gui.show(player);
```

In this example, we create a Pane layout pane to hold the content of the GUI.
We add GuiItems or other nodes to the layout, and then we set the `root` node for the GUI using a `Scene`.
Finally, we show the GUI to the player.

### Layout Panes

SomeGuiApi provides several layout panes to assist with organizing and positioning GUI elements:
- `Pane`: A basic layout pane that allows you to position elements manually.
- `HBox`: A horizontal box layout pane that arranges elements in a single row.
- `VBox`: A vertical box layout pane that arranges elements in a single column.
- `FlowPane`: A layout pane that arranges elements in a flow layout, wrapping at the pane's boundary.

### Nesting

You can nest layout panes within each other to achieve more complex GUI designs. For example:
```java
ChestGui gui = new ChestGui(Component.text("Nested GUI"), 3);

VBox root = new VBox();
root.resize(9, 3);
root.setSpacing(10);

HBox hBox = new HBox();
hBox.setSpacing(10);
GuiItem item1 = createGuiItem(/* Item details */);
GuiItem item2 = createGuiItem(/* Item details */);
hBox.getChildren().addAll(item1, item2);

FlowPane flowPane = new FlowPane();
flowPane.setHgap(5);
flowPane.setVgap(5);
GuiItem item3 = createGuiItem(/* Item details */);
GuiItem item4 = createGuiItem(/* Item details */);
flowPane.getChildren().addAll(item3, item4);

root.getChildren().addAll(hBox, flowPane);

gui.setScene(new Scene(root));
gui.show(player);
```

### Layout Properties

You can set layout properties for each node to control its position and size within the GUI.
These properties include:

- `layoutX`: The x-coordinate of the node within its parent.
- `layoutY`: The y-coordinate of the node within its parent.
- `translateX`: The x-coordinate translation of the node.
- `translateY`: The y-coordinate translation of the node.

Note that `layoutX` and `layoutY` will be managed by the layout pane, if the node is a child of one.
Translation properties are added to the layout coordinates to get the final position,
allowing you to move the node relative to its layout position.

Nodes that extend `Region` (e.g. the [layout panes](#layout-panes)) also have the following properties:

- `width`: The width of the node.
- `height`: The height of the node.

### Click handling

SomeGuiApi provides support for handling various types of click events. When a player clicks an item 
a click event will be fired. The top-most clicked node will receive the click first, followed by all of its
parent nodes, if the click occurs within their bounds.
```java
item.setOnClick(context -> {
    // Handle click
})

item.setOnLeftClick(context -> {
    // Handle left-click
});

item.setOnRightClick(context -> {
    // Handle right-click
});

item.setOnShiftClick(context -> {
    // Handle shift-click
});

item.setOnHotBarClick(context -> {
    // Handle clicks from the hotbar
});
```

`ClickContext` provides information about the click event, such as the `player` who clicked, the `coordinates` of the click,
and the `click type` (e.g., left-click, right-click).
<br>
It also can be consumed using `context.consume()` to prevent further processing of the click event. Other listeners can
check if the context is consumed before handling the event using `context.isConsumed()`.
