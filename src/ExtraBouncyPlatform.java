public class ExtraBouncyPlatform extends Platform {
    public ExtraBouncyPlatform(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void onPlayerContact(Player player) {
        player.extraJump();
    }
}
