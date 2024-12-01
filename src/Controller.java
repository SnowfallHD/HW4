import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Controller {
    private Model model;
    private View view;
    private Timeline timeline;
    private boolean isPaused = false;

    // Add these variables to track key states
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupGameControls();
    }

    private void setupGameControls() {
        setupKeyControls();
        setupGameLoop();
    }

    private void setupKeyControls() {
        view.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                togglePause();
            } else if (e.getCode() == KeyCode.LEFT) {
                leftPressed = true;
            } else if (e.getCode() == KeyCode.RIGHT) {
                rightPressed = true;
            }
        });
    
        view.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                leftPressed = false;
            } else if (e.getCode() == KeyCode.RIGHT) {
                rightPressed = false;
            }
        });
    }    

    private void togglePause() {
        if (!isPaused) {
            timeline.pause();
        } else {
            timeline.play();
        }
        isPaused = !isPaused;
    }

    private void movePlayer(int direction) {
        Player player = model.getPlayer();
        double newX = player.getX() + direction;
    
        // Wrap around the screen
        if (newX < -player.getWidth()) {
            newX = model.sceneWidth;
        } else if (newX > model.sceneWidth) {
            newX = -player.getWidth();
        }
    
        player.setX(newX);
        // No need to update the view here; it's handled in updateGame()
    }
    

    private void setupGameLoop() {
        timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateGame() {
        model.updateGameState();
    
        // Handle continuous movement based on key states
        if (leftPressed) {
            movePlayer(-5); // Adjust speed as needed for smoothness
        }
        if (rightPressed) {
            movePlayer(5);
        }
    
        if (model.isGameOver()) {
            timeline.stop();
            view.showGameOver();
            return;
        }
    
        Player player = model.getPlayer();
        view.updatePlayerPos(player.getX(), player.getY());
        view.updatePlatforms(model.getPlatforms());
        view.updateScore(model.scoreProperty().get());
    }
    

    public void start() {
        timeline.play();
    }
}
