package third;

public class Arthur {
    private Emotion emotion = Emotion.CALM;
    private boolean jawDropped = false;
    private int unbelievableThingsSeen = 0;

    // Артур, нервничая, вошел следом
    public void enterRoom() {
        this.emotion = Emotion.NERVOUS;
    }

    // Вошел следом и был ошеломлен, увидев развалившегося в кресле человека, положившего ноги на пульт управления и ковыряющего левой рукой в зубах правой головы
    // Правая голова, казалось, была всецело занята этим, но зато левая улыбалась широко и непринужденно.
    // Количество вещей, видя которые, Артур не верил своим глазам, все росло. Его челюсть отвисла.
    public void observe(Alien alien, ControlPanel panel) {
        if (alien.isLoungingInChair()) addUnbelievableThing();
        if (panel.isHasLegsOnIt()) addUnbelievableThing();
        if (alien.getRightHead().getState() == HeadState.BUSY_PICKING_TEETH) addUnbelievableThing();
        if (alien.getLeftHead().getState() == HeadState.SMILING_BROADLY) addUnbelievableThing();

        if (unbelievableThingsSeen >= 4) {
            this.emotion = Emotion.STUNNED;
            this.jawDropped = true;
        }
    }

    public void addUnbelievableThing() {
        this.unbelievableThingsSeen++;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public boolean isJawDropped() {
        return jawDropped;
    }

    public int getUnbelievableThingsSeen() {
        return unbelievableThingsSeen;
    }
}