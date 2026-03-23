package third;

public class ControlPanel {
    private boolean hasLegsOnIt = false;

    public void putLegs() {
        this.hasLegsOnIt = true;
    }

    public boolean isHasLegsOnIt() {
        return hasLegsOnIt;
    }
}