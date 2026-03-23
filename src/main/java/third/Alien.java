package third;

public class Alien {
    private final Head leftHead;
    private final Head rightHead;
    private boolean loungingInChair;

    public Alien() {
        this.leftHead = new Head("Left");
        this.rightHead = new Head("Right");
        this.loungingInChair = false;
    }

    // развалившегося в кресле человека, положившего ноги на пульт, левая улыбалась широко
    public void relaxOn(ControlPanel panel) {
        this.loungingInChair = true;
        panel.putLegs();
        this.leftHead.setState(HeadState.SMILING_BROADLY);
    }

    // ковыряющего левой рукой в зубах правой головы
    public void pickTeeth() {
        this.rightHead.setState(HeadState.BUSY_PICKING_TEETH);
    }

    public boolean isLoungingInChair() {
        return loungingInChair;
    }

    public Head getLeftHead() {
        return leftHead;
    }

    public Head getRightHead() {
        return rightHead;
    }
}