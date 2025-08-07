# 🎨 Colour Swap – Java Game Project

## Project Overview
**Colour Swap** is a 2D Java game built using Java Swing.  
Players control a coloured circle that can move around the screen and switch between **red** and **blue**. The goal is to **collect same-coloured shapes** to earn points and **avoid opposite-coloured shapes** or the game ends!

- Move with the arrow keys  
- Press the space bar to switch colours  
- Gain points by touching same-coloured shapes  
- Game over if you touch an opposite-coloured shape!

| **Single Player Mode** | **Robot Player Mode** |
|------------------------|------------------------|
| <img src="https://github.com/user-attachments/assets/ed6c699e-2e0e-4a86-9c4b-c5603fab97dd" width="100%"/> | <img src="https://github.com/user-attachments/assets/b682355a-0fb8-4aaa-8833-99c4f2db7e85" width="100%"/> |


## Project Structure

The codebase is modular and organized into packages:

- `colourswap` – Main entry point (`Main.java`) and core drawing logic  
- `controller` – Handles game setup and connects the model to the view  
- `keylisteners` – Reads and processes key input from the player  
- `model` – Contains the `Game` class and main game logic  
- `shapes` – Shape-related classes in an inheritance hierarchy  
- `utils` – Utility classes for distance calculation and collision detection  
- `view` – Java Swing UI components (e.g. `MainWindow`)  
- `screens` – UI screens (e.g. title screen, game screen, help screen, game over)



## Development Notes

This game was originally developed as a university project and later refactored for my portfolio. It demonstrates:

- Object-oriented design
- Inheritance and polymorphism
- Real-time game logic
- Java Swing UI
- Event handling with key listeners
- Collision detection
- Modular code architecture


## How to Run

1. Clone the repository  
2. Open the project in your Java IDE  
3. Run the `Main.java` class from the `colourswap` package


## Download & Run

Download the latest version:
👉 👉 [Download Colour Swap JAR](./release/final-project-colour-swap.jar)


To run:
```bash
java -jar final-project-colour-swap.jar
