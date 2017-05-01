# VOOGAUtil Multi-Input Handler
## Team ILoveSingletons

### Overview
This utility allows any implementing program to run the utility and use either a gamepad or remapped keys from the keyboard to control various keystrokes and mouse movements on the screen. This can allow for much more intuitive game play for a game. 

### How To Use
This utility provides both the utility of multiple input and a basic selection menu for the available inputs. The main file to run is IOUtil, whose constructor takes a String name of a file that contains the mapping of keys to controls. If the user has no preference for controls they wish to map beforehand, the constructors in the utility permit the use of empty constructors that default the utility to files held within its resources package. 

The menu is returned in the form of a Node, which can be added to any JavaFX layout. This Node is returned from the method call showMenu() in IOUtil. The utility automatically starts running the moment this method is called, and the user can select which type of controller to use from the menu. The user can always change the controller selection by returning to the menu. 

### Notes
Make sure that the proper drivers are installed on the computer using this utility for proper function. If the drivers are not installed, the program cannot read the input from the controller.