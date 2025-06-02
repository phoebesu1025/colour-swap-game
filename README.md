# Final Project: Colour Swap

## Introduction

In this project, you will extend the game Colour Swap. In this game, shapes will appear periodically and move around on the screen. The player controls a circle that is either red or blue. The player can move the circle using arrow keys, and change its colour using the space bar.

When the player touches a same-coloured shape, that shape is removed and the player scores points. The objective of the game is to avoid touching any opposite-coloured shapes. Once the player does, the game is over and the player will be shown their score.

## Project instructions

This project is divided into three increments. For instructions on what to do for each increment, and how they will be assessed, please see the associated project handout which will be provided to you along with this codebase.

## Package description

The code, as provided to you, is organized into _packages_. A brief description of each package is given here:

- `colourswap`: The main package. Contains the `Main` class to run the application, along with classes involved with drawing shapes.

  - `controller`: Contains classes involved with starting a new game, and connecting `Game` objects with _views_ to display them (see below).
    - `keylisteners`: Contains classes involved with reading key presses to control the game (e.g. move players, swap colours).

  - `model`: Contains the main `Game` class and other classes involved with running the game.
    - `shapes`: Contains all the shapes in the game, organized in a _hierarchy_ with `Shape` being the top-level class.

  - `utils`: Contains classes with methods used for calculating distances between objects, and for determining whether two objects are colliding with one another. Used by the game to decide when to earn points - and when the player loses!

  - `view`: Contains the Java Swing UI for the game. The `MainWindow` class is the main screen - all other components are contained within.
    - `screens`: Contains different game screens, such as the title screen, the main game screen, the help screen, and the "game over" screen.
