package third;

public class Head {
    private final String side;
    private HeadState state;

    public Head(String side) {
        this.side = side;
        this.state = HeadState.NORMAL;
    }

    public void setState(HeadState state) {
        this.state = state;
    }

    public HeadState getState() {
        return state;
    }

    public String getSide() {
        return side;
    }
}