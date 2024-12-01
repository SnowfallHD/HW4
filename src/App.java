import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        double sceneWidth = 500;
        double sceneHeight = 600;

        View myGameView = new View(primaryStage, sceneWidth, sceneHeight);
        Model myGameModel = new Model(myGameView);
        Controller myGameController = new Controller(myGameModel, myGameView);

        myGameController.start();
        myGameView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
