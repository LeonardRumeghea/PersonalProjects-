Compulsory (1p)

Create the following components:

	*The main frame of the application.
	*A configuration panel for introducing parameters regarding the grid size and a button for creating a new game. The panel must be placed at the top part of the frame. The panel must contain at least one label and one input component.
	*A canvas (drawing panel) for drawing the board. Draw the grid lines according to the values specified in the config panel. This panel must be placed in the center part of the frame.
	*A control panel for managing the game. This panel will contain the buttons: Load, Save, Exit ,etc. and it will be placed at the bottom part of the frame.

Homework (2p)

	*Create the object oriented model.
	*Initialize the game by generating random sticks and place them on the board. Implement either direct or retained mode for drawing the game board.
	*Implement the logic of the game. When the player execute a mouse pressed operation, a stone must be drawn at the mouse location: red or blue depending on whose turn it is. Validate the move, according to the game rules. Determine the winner of the game.
	*(+0.5p) Export the current image of the game board into a PNG file.
	*(+0.5p) Use object serialization in order to save and restore the current status of the game