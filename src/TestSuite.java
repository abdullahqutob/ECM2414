import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDeck.class,
        TestCardGame.class,
        TestCard.class,
        TestPlayer.class,
        TestPack.class
})
public class TestSuite {}
