public class Player {
    private double x, y;
    private double width, height;
    private double velocityY;
    private static final double DURATION = 0.016; // 16 ms per frame
    private static final double GRAVITY = 800; // Adjust as needed
    private static final double REBOUND_VELOCITY = -600;
    private static final double EXTRA_REBOUND_VELOCITY = -900;    


    public Player(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocityY = 0; // Start with zero vertical velocity
    }
    

    public void applyGravity() {
        velocityY += GRAVITY * DURATION;
        y += velocityY * DURATION;
    }

    public void jump() {
        velocityY = REBOUND_VELOCITY;
    }

    public void extraJump() {
        velocityY = EXTRA_REBOUND_VELOCITY;
    }

    // Getters and setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}
