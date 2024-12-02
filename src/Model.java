import javafx.beans.property.IntegerProperty;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {
    private Player player;
    private List<Platform> platforms;
    private Score score;
    private View view;

    protected double sceneWidth, sceneHeight;
    public static final double SCENE_WIDTH = 500;

    private double lastPlatformX;

    public Model(View view) {
        this.view = view;
        this.sceneWidth = view.getSceneWidth();
        this.sceneHeight = view.getSceneHeight();

        double initialPlayerX = sceneWidth / 2 - view.getPlayerWidth() / 2;
        double platformY = sceneHeight - 100; // First platform's Y position

        // Set player's Y position to be above the platform
        double initialPlayerY = platformY - view.getPlayerHeight() - 1;

        this.player = new Player(initialPlayerX, initialPlayerY, view.getPlayerWidth(), view.getPlayerHeight());

        this.lastPlatformX = player.getX();
        this.platforms = new ArrayList<>();
        this.score = new Score();

        generateInitialPlatforms(platformY);


    }

    private void generateInitialPlatforms(double startY) {
        double y = startY;
    
        // First platform directly under the player
        addFirstPlatform(lastPlatformX, y);
    
        while (y > -200) { // Continue until platforms extend beyond the top
            double yOffsetMin = 100;
            double yOffsetMax = 200;
            double yOffset = yOffsetMin + Math.random() * (yOffsetMax - yOffsetMin);
            y -= yOffset;
    
            addPlatform(y);
        }
    }
    
    
    
    
    private void addFirstPlatform(double x, double y) {
        Platform platform = PlatformFactory.createRegularPlatform(x, y); // Ensure it's a regular platform
        platforms.add(platform);
        view.addPlatform(platform);
    }

    private void addPlatform(double y) {
        double xOffset = 100; // Adjust as needed
        // Calculate bounds for X based on lastPlatformX
        double minX = Math.max(0, lastPlatformX - xOffset);
        double maxX = Math.min(sceneWidth - 80, lastPlatformX + xOffset);
        double x = minX + Math.random() * (maxX - minX);
    
        lastPlatformX = x; // Update last platform's X position
    
        Platform platform = PlatformFactory.createRandomPlatform(x, y);
        platforms.add(platform);
        view.addPlatform(platform);
    }
    
    

    public void updateGameState() {
        player.applyGravity();
        

        // Update platforms
        for (Platform platform : platforms) {
            platform.update();
        }

        // Check for collisions
        Iterator<Platform> iterator = platforms.iterator();
        while (iterator.hasNext()) {
            Platform platform = iterator.next();

            if (playerIntersectsPlatform(platform)) {
                platform.onPlayerContact(player);
                if (platform instanceof DisappearingPlatform && ((DisappearingPlatform) platform).isUsed()) {
                    iterator.remove();
                    view.removePlatform(platform);
                }
                break;
            }
        }

        scrollIfNeeded();
        removeOffScreenPlatforms();
        ensurePlatformsAbove();
    }

    private void scrollIfNeeded() {
        if (player.getY() < sceneHeight / 2) {
            double deltaY = sceneHeight / 2 - player.getY();
            player.setY(sceneHeight / 2);

            // Move all platforms down
            for (Platform platform : platforms) {
                platform.setY(platform.getY() + deltaY);
            }

            // Update the positions in the view
            view.updatePlatforms(platforms);
        }
    }

    private void removeOffScreenPlatforms() {
        Iterator<Platform> iterator = platforms.iterator();
        while (iterator.hasNext()) {
            Platform platform = iterator.next();
            if (platform.getY() > sceneHeight) {
                iterator.remove();
                view.removePlatform(platform);
                score.increaseScore(1);
    
                // **No need to add a new platform here**
            }
        }
    }
    
    

    private boolean playerIntersectsPlatform(Platform platform) {
        double playerBottomY = player.getY() + player.getHeight();
        double platformTopY = platform.getY();
    
        return player.getX() + player.getWidth() > platform.getX() &&
               player.getX() < platform.getX() + platform.getWidth() &&
               playerBottomY >= platformTopY - 5 && // Added tolerance
               playerBottomY <= platformTopY + platform.getHeight() + 5 &&
               player.getVelocityY() > 0;
    }

    private Platform getTopMostPlatform() {
        Platform topPlatform = null;
        double minY = Double.MAX_VALUE;
        for (Platform platform : platforms) {
            if (platform.getY() < minY) {
                minY = platform.getY();
                topPlatform = platform;
            }
        }
        return topPlatform;
    }
    

    private void ensurePlatformsAbove() {
        Platform topPlatform = getTopMostPlatform();
        double y = (topPlatform != null) ? topPlatform.getY() : sceneHeight;
    
        // Continue adding platforms until they extend beyond the top of the screen
        while (y > -200) { // Adjust the threshold as needed
            double yOffsetMin = 100;
            double yOffsetMax = 200;
            double yOffset = yOffsetMin + Math.random() * (yOffsetMax - yOffsetMin);
            y -= yOffset;
    
            addPlatform(y);
        }
    }
    
    
       

    public boolean isGameOver() {
        boolean gameOver = player.getY() > sceneHeight;
        return gameOver;
    }
    

    public Player getPlayer() {
        return player;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public IntegerProperty scoreProperty() {
        return score.scoreProperty();
    }
}
