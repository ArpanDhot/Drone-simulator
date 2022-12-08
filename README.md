## Drone simulator Java
This drone simulator game has been made using Java and JavaFX.The application employs concepts of Object Oriented Programming to create blueprints for several objects in the game.

### Object Oriented Programming implantation overview
The simulation game comprises many objects with one thing in common their movement. Therefore, I created an abstract Position class containing coordinates and appropriate methods. The choice of making the class abstract was for a reason, to not allow the user to create instances of the Position class but only inherit it and provide the unimplemented methods. 

The majority of the classes in this project incorporate encapsulation. This concept enabled hiding an object's internal representation, or state, from the outside. For example, the attributes in the Drone class are private; therefore, they are only accessible within the class. The only way to access them is by using the public getter and setter methods. Furthermore, it enhances security because the programmer cannot manipulate attributes from every class. 

### Features
- The user can add an ordinary drone to the simulation
- User can add an attack drone in the simulation
- The user can play and pause the simulation
- User can save and load the simulation using JSON database
- The user can turn the music on and off
- The user can resize the game resolution


## Application Demo
![about](/Demo/cc3044ee9322070418fc.gif)


### Instruction to play the simulation
- To play the simulation, download the "DroneSimulator-Jar.zip" file, and download the [JavaFx library](https://gluonhq.com/products/javafx/) on your device. 
- Right-click on the "run.bat" and press edit to upate the JavaFx library path. 
- Double-click on the DroneSimulator.jar file to run the game.
