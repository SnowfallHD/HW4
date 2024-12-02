public class PlatformFactory {
    public static Platform createRegularPlatform(double x, double y) {
        return new RegularPlatform(x, y, 80, 10);
    }

    public static Platform createRandomPlatform(double x, double y) {
        double rand = Math.random();
        if (rand < 0.5) {
            return new RegularPlatform(x, y, 80, 10);
        } else if (rand < 0.7) {
            return new DisappearingPlatform(x, y, 80, 10);
        } else if (rand < 0.85) {
            return new ExtraBouncyPlatform(x, y, 80, 10);
        } else {
            return new MovingPlatform(x, y, 80, 10);
        }
    }
}
