package third;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DomainModelTest {

    private Arthur arthur;
    private Alien alien;
    private ControlPanel panel;

    @BeforeEach
    void setUp() {
        arthur = new Arthur();
        alien = new Alien();
        panel = new ControlPanel();
    }

    @Test
    @DisplayName("Проверка начального состояния Артура и входа в комнату")
    void testArthurEntering() {
        assertEquals(Emotion.CALM, arthur.getEmotion());
        assertFalse(arthur.isJawDropped());

        arthur.enterRoom();
        assertEquals(Emotion.NERVOUS, arthur.getEmotion(), "Войдя, Артур должен нервничать");
    }

    @Test
    @DisplayName("Проверка действий инопланетянина")
    void testAlienActions() {
        alien.relaxOn(panel);
        alien.pickTeeth();

        assertTrue(alien.isLoungingInChair());
        assertTrue(panel.isHasLegsOnIt());
        assertEquals(HeadState.SMILING_BROADLY, alien.getLeftHead().getState());
        assertEquals(HeadState.BUSY_PICKING_TEETH, alien.getRightHead().getState());
        assertEquals("Left", alien.getLeftHead().getSide());
        assertEquals("Right", alien.getRightHead().getSide());
    }

    @Test
    @DisplayName("Артур наблюдает всю сцену")
    void testFullObservationScene() {
        arthur.enterRoom();
        alien.relaxOn(panel);
        alien.pickTeeth();

        arthur.observe(alien, panel);

        assertEquals(4, arthur.getUnbelievableThingsSeen());
        assertEquals(Emotion.STUNNED, arthur.getEmotion(), "Артур должен быть ошеломлен");
        assertTrue(arthur.isJawDropped(), "Челюсть Артура должна отвиснуть");
    }

    @Test
    @DisplayName("Артур наблюдает сцену частично")
    void testPartialObservationScene() {
        alien.relaxOn(panel);

        arthur.observe(alien, panel);

        assertEquals(3, arthur.getUnbelievableThingsSeen());
        assertNotEquals(Emotion.STUNNED, arthur.getEmotion());
        assertFalse(arthur.isJawDropped());
    }

    @ParameterizedTest(name = "Увидел столько-то невероятных вещей, значит челюсть или отвисла или нет, а эмоция появилась соответсвующая")
    @CsvSource({
            "0, false, CALM",
            "3, false, CALM",
            "4, true, STUNNED",
            "5, true, STUNNED"
    })
    @DisplayName("Тест логики реакции Артура на количество вещей")
    void testArthurReactionLogic(int thingsSeen, boolean expectedJawDrop, Emotion expectedEmotion) {
        for (int i = 0; i < thingsSeen; i++) {
            arthur.addUnbelievableThing();
        }

        arthur.observe(new Alien(), new ControlPanel());

        assertEquals(expectedJawDrop, arthur.isJawDropped());
        assertEquals(expectedEmotion, arthur.getEmotion());
    }
}