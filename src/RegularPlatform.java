public class RegularPlatform extends Platform {
    public RegularPlatform(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void onPlayerContact(Player player) {
        player.jump();
    }
}
