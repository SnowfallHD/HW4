import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score {
    private IntegerProperty score;

    public Score() {
        this.score = new SimpleIntegerProperty(0);
    }

    public void increaseScore(int amount) {
        score.set(score.get() + amount);
    }

    public void reset() {
        score.set(0);
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }
}
