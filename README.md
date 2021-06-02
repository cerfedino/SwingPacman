# PACMAN

This is a classic Pacman game implemented in Java. 

The goal is simple: eat all food, save yourself from the ghosts that chase you and attack them when they are vulnerable!

## How to play
To play the game, just run the ```main()``` method. Once you spawn on the map, use the arrow keys to navigate. The game ends when Pacman eats all food on the map or when he loses all of his lives. Pacman loses a single life whenever he collides with an invulnerable ghost.

Eating a single food grants you 20 points and eating a ghost, when vulnerable, grants you 200 points. You can keep track of your total score on the bottom left edge of the game screen and see remaining lives of Pacman on the bottom right edge of the game screen.

To pause the game, press P. To resume, press U.

## Code Structure

The code consists of 8 packages and 37 classes, each having a huge importance of the game implementation. It is structured in a way that is easily readable, modifiable and understandable. In almost every package, there is a main class that nearly all other sub-classes inherit from. The class hiearchy is organized at a high-level and every componenet that is a part of the game can be instantly classified to its correspondant position in this hiearchy.

### 1. Package Game:
This is the package that is responsible for running the game efficiently. It holds the main Game class, including the GameState and the GameThread. These classes handle the start and end of the game, perform an intro after every new round as well as a death sequence when Pacman dies. The GameInputManager takes the keyboard Actions from the user and transforms it into the movement of Pacman along the map.

### 2. Package Map:
This is the package that holds all Map components. Our map is designed such that it is built from nodes that are connected by edges. The Pacman and Ghosts move along the edges and make a new turn whenever they are located on a Node. All nodes have a specific location on the map and every edge is built based on a relation between two nodes. 

## Customazibility
## 



# Guide to compiling :rocket:

### 1. Install Java 16

[Java SE 16 Download page](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)

After installing Java 16, set the JAVA_HOME environment variable to point to the JDKs folder, like so:

```bash
export JAVA_HOME=/usr/lib/jvm/jdk-16.0.1/
```

### 2. Install Maven 3.8.1

Since we are using Java 16, you need to be using Apache Maven 3.8.1  
[Download page](https://maven.apache.org/download.cgi)

- Extract the tar.gz archive somewhere safe :wink:

- edit your .bashrc file with  ```nano ~/.bashrc``` and add the following lines
  
  ```bash
  export M2_HOME=<path-to-extracted-maven-archive>
  export M2=$M2_HOME/bin
  export PATH=$M2:$PATH
  ```



You should be good to go now :smiling_imp:
