public class MovingPlatform extends Platform {
    private double speedX = 2;

    public MovingPlatform(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void onPlayerContact(Player player) {
        player.jump();
    }

    @Override
    public void update() {
        x += speedX;
        // Reverse direction at edges
        if (x <= 0 || x + width >= Model.SCENE_WIDTH) {
            speedX = -speedX;
        }
    }
}
