import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View extends BorderPane {

    Rectangle player;
    Stage stage;
    double sceneWidth, sceneHeight;
    Pane centerPane = new Pane();
    Label scoreLabel;
    double playerWidth = 50, playerHeight = 50;
    private Scene scene; // Keep this to manage focus and events

    private Map<Platform, Rectangle> platformRectangles = new HashMap<>();

    public View(Stage stage, double sceneWidth, double sceneHeight) {
        this.stage = stage;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        setupDisplay();
    }

    public void setupDisplay() {
        double playerX = sceneWidth / 2 - playerWidth / 2;
        double playerY = sceneHeight - playerHeight - 50;
        player = new Rectangle(playerX, playerY, playerWidth, playerHeight);
        player.setFill(Color.ORANGE);
        centerPane.getChildren().add(player);

        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(new Font("Arial", 24));
        BorderPane.setAlignment(scoreLabel, Pos.TOP_CENTER);
        setTop(scoreLabel);

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> System.exit(0));
        BorderPane.setAlignment(quitButton, Pos.CENTER);
        setBottom(quitButton);

        setCenter(centerPane);

        scene = new Scene(this, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    public void show() {
        stage.show();
        this.setFocusTraversable(true); // Ensure the view is focusable
        this.requestFocus(); // Request focus to receive key events
    }

    public Scene getGameScene() { // Renamed to avoid conflict
        return scene;
    }

    public void addPlatform(Platform platform) {
        Rectangle rect = new Rectangle(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());

        if (platform instanceof RegularPlatform) {
            rect.setFill(Color.BLACK);
        } else if (platform instanceof DisappearingPlatform) {
            rect.setFill(Color.RED);
        } else if (platform instanceof ExtraBouncyPlatform) {
            rect.setFill(Color.GREEN);
        } else if (platform instanceof MovingPlatform) {
            rect.setFill(Color.BLUE);
        }

        platformRectangles.put(platform, rect);
        centerPane.getChildren().add(rect);
    }

    public void removePlatform(Platform platform) {
        Rectangle rect = platformRectangles.get(platform);
        centerPane.getChildren().remove(rect);
        platformRectangles.remove(platform);
    }

    public void updatePlayerPos(double playerX, double playerY) {
        player.setX(playerX);
        player.setY(playerY);
    }

    public void updatePlatforms(List<Platform> platforms) {
        for (Platform platform : platforms) {
            Rectangle rect = platformRectangles.get(platform);
            if (rect != null) {
                rect.setX(platform.getX());
                rect.setY(platform.getY());
            }
        }
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void showGameOver() {
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setFont(new Font("Arial", 36));
        gameOverLabel.setTextFill(Color.RED);
        gameOverLabel.setLayoutX(sceneWidth / 2 - 100);
        gameOverLabel.setLayoutY(sceneHeight / 2 - 50);
        centerPane.getChildren().add(gameOverLabel);

        // Disable key inputs
        scene.setOnKeyPressed(null);
    }

    public double getPlayerWidth() {
        return playerWidth;
    }

    public double getPlayerHeight() {
        return playerHeight;
    }

    public double getSceneWidth() {
        return sceneWidth;
    }

    public double getSceneHeight() {
        return sceneHeight;
    }
}
