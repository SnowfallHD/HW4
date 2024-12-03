## HW4 - Doodle Jump
### Danny Philbrook & Cooper Nusbaum
 

This is our Doodle Jump project that, over a month timespan, we have collectively spent around 16 hours planning, designing, and testing our assignment. It turned out to be a lengthy assignment, and it got hard to manage sometimes with other assignments being due, but in the end it was a good challenge. 

Doodle Jump is a game where you control a doodle as it bounces upwards to higher platforms without falling off.

## Additional Functionalities
Pause button: Pressing the space bar pauses the game, and you can reclick to unpause the game. 

Smooth left/right movement with arrow keys: We tracked the key presses with Boolean variables (leftPressed and rightPressed), and then called our movePlayer method with direction changes every time those buttons were pressed. The result was a smooth look. 

Constant flow of platforms: We noticed in the video demo, the platforms spawning above the doodle were choppy, and they only seemed to spawn above when the platforms below would delete. We incorporated a method, ensurePlatformsAbove(), using another method, getTopMostPlatform(), to ensure there was a constantly flow of platforms above. This made the game look a lot more smooth, and reinforced the illusion that the screen was moving up. 

## Design Choices:

We used the MVC structure and implemented different platform types using separate classes w/ inheritance.


## Issues During Development 

Fixed the game from immediate ending saying game over by adjusting player and platform positions.
Improved logic for detecting the player touching a platform. The player was going through the platforms initially, it took trial and error to get it just right.
Was throwing a NullPointerException because the player object was null when trying to access it when initiating the Model. Fixed by reorganizing the initialization order in Model.
Key presses were not being captured because there was no focus.
Overriding the getScene() method caused issue, we renamed the method in the View class to getGameScene() to avoid overriding.
