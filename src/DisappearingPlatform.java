public class DisappearingPlatform extends Platform {
    private boolean used = false;

    public DisappearingPlatform(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void onPlayerContact(Player player) {
        if (!used) {
            player.jump();
            used = true;
        }
    }

    public boolean isUsed() {
        return used;
    }
}
