import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BattleshipGameTest {
    private BattleshipGame game;

    @BeforeEach
    public void setUp() {
        game = new BattleshipGame();
    }

    @Test
    public void testInitialCounters() {
        assertEquals(0, game.getMissCounter());
        assertEquals(0, game.getStrikeCounter());
        assertEquals(0, game.getTotalMissCounter());
        assertEquals(0, game.getTotalHitCounter());
        assertFalse(game.isGameOver());
    }
}