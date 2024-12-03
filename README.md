## HW4 - Doodle Jump
### Danny Philbrook & Cooper Nusbaum
 

This is our Doodle Jump project that, over a month timespan, we have collectively spent around 16 hours planning, designing, and testing our assignment. It turned out to be a lengthy assignment, and it got hard to manage sometimes with other assignments being due, but in the end it was a good challenge. 

## Additional Functionalities

Introduction

This Doodle Jump game is a JavaFX application that replicates the classic gameplay of the popular mobile game. The player's objective is to guide the doodle character upward by bouncing on various platforms without falling off the bottom of the screen. The game features different types of platforms, score tracking, and smooth player movement.
Design Choices
MVC Architecture

The game is structured using the Model-View-Controller (MVC) design pattern:

    Model: Manages the game logic, including player physics, platform generation, collision detection, and score tracking.
    View: Handles all the graphical elements and user interface components, such as rendering the player, platforms, score display, and game over message.
    Controller: Manages user input and orchestrates interactions between the Model and View, including key event handling and the game loop.

Object-Oriented Principles

    Inheritance and Polymorphism: Created an abstract Platform class with subclasses (RegularPlatform, DisappearingPlatform, ExtraBouncyPlatform, MovingPlatform) to represent different platform types, leveraging polymorphism for platform behaviors.
    Encapsulation: Encapsulated player properties and behaviors within the Player class, providing methods for movement and physics simulation.
    Delegation: Delegated specific tasks to appropriate classes, such as the PlatformFactory for platform creation and the Score class for score management.

Physics Simulation

    Implemented basic physics for the player's movement, including gravity and rebound velocities.
    Constants such as GRAVITY, REBOUND_VELOCITY, and DURATION are used to control the physics simulation for consistent and adjustable gameplay.

Platform Generation

    Platforms are generated semi-randomly with constraints to ensure they are reachable by the player.
    The game maintains an ArrayList of platforms, allowing for dynamic addition and removal as platforms go off-screen.

Smooth Player Movement

    Implemented continuous movement by tracking key press states for left and right arrow keys.
    The player's position is updated in the game loop based on the current key states, allowing for smooth and responsive controls.

Issues Encountered and Solutions
Immediate Game Over on Start

    Issue: The game would display "Game Over" immediately upon starting because the player would fall off the screen without bouncing on a platform.
    Solution: Adjusted the player's initial position to ensure it starts above the first platform. Aligned the first platform directly under the player to guarantee an initial collision and bounce.

Player Not Colliding with Platforms

    Issue: The player would fall through platforms without detecting collisions due to strict collision detection conditions.
    Solution: Relaxed the collision detection conditions by adding a small tolerance to the platform's boundaries. This accounted for minor discrepancies due to frame rates or rounding errors.

NullPointerException in Model Initialization

    Issue: Encountered a NullPointerException because the player object was null when attempting to access it during the Model's initialization.
    Solution: Rearranged the initialization order in the Model constructor to ensure the player was initialized before any method calls on it.

Key Event Handling and Focus Issues

    Issue: Key events were not being captured because the scene or node did not have focus, preventing player movement.
    Solution: Ensured that the node handling key events was focusable and requested focus upon showing the stage. Used setFocusTraversable(true) and requestFocus() on the appropriate node.

Continuous Player Movement

    Issue: The player would only move one increment per key press, requiring repeated pressing for continuous movement.
    Solution: Implemented key state tracking for the left and right arrow keys. Updated the player's position continuously in the game loop based on the keys currently pressed, resulting in smooth movement while holding down the keys.

Method Naming Conflicts

    Issue: Overriding the getScene() method caused conflicts with the final method in JavaFX's Node class.
    Solution: Renamed the method in the View class to getGameScene() to avoid overriding a final method.

Known Bugs and Missing Functionalities

    Platform Movement Synchronization: The moving platforms (blue) sometimes do not reverse direction precisely at the screen edges due to timing discrepancies.
    Player Wrapping Glitch: Occasionally, when the player wraps around the screen edges, there might be a slight visual jump.
    Platform Generation Balance: The random generation of platforms may sometimes result in unreachable platforms, requiring further adjustments to the generation constraints.
    Missing Additional Features: The game currently does not include all the extra functionalities from the original Doodle Jump game, such as enemies, power-ups, or different levels of difficulty.

Additional Functionalities Added

    Smooth Continuous Movement: Implemented continuous and smooth player movement when holding down the left or right arrow keys, enhancing the gameplay experience.
    Platform Variety: Included four types of platforms with unique behaviors:
        Regular Platforms (black): Standard bounce.
        Disappearing Platforms (red): Vanish after one bounce.
        Extra Bouncy Platforms (green): Provide a higher rebound velocity.
        Moving Platforms (blue): Move horizontally and reverse direction at screen edges.

How to Run the Game

    Ensure you have Java and JavaFX installed and configured in your development environment.
    Compile all the .java files in the project.
    Run the App class to start the game.
    Use the left and right arrow keys to move the player.
    Try to bounce on platforms to climb higher and avoid falling off the bottom.
    The score increases as platforms go off the bottom of the screen.
    Click the "Quit" button to exit the game at any time.

Controls

    Left Arrow Key: Move the player to the left.
    Right Arrow Key: Move the player to the right.
    Space Bar: Pause or resume the game.
    Quit Button: Exit the game.

Project Structure

    App.java: The main application class that initializes the MVC components.
    Model.java: Contains the game logic, including player physics and platform management.
    View.java: Handles the graphical representation and user interface elements.
    Controller.java: Manages user input and updates the model and view accordingly.
    Player.java: Represents the player character and handles physics calculations.
    Platform.java: Abstract class for platforms with subclasses for each platform type.
    PlatformFactory.java: Creates platforms of different types.
    Score.java: Manages the game's score.

Dependencies

    Java Development Kit (JDK) 8 or higher.
    JavaFX SDK.

Acknowledgments

This game was developed as part of an assignment to recreate Doodle Jump. Throughout the development process, several challenges were encountered and overcome, leading to a deeper understanding of game development and JavaFX.
Contact

For any questions or suggestions, please contact The Danny Coop Squad(ChatGPt)