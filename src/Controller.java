import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Controller {
    private Model model;
    private View view;
    private Timeline timeline;
    private boolean isPaused = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupKeyControls();
        setupTimeline();
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

        // Ensure quit button remains responsive
        view.requestFocus();
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
    }
    

    private void setupTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateGame() {
        model.updateGameState();
    
        // Continuous movement
        if (leftPressed) {
            movePlayer(-5); // Adjust for smoothness
        }
        if (rightPressed) {
            movePlayer(5); // Adjust for smoothness
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
