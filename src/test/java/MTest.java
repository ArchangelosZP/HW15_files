import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MTest {
    public Main n;

    @Before
    public void setUp() {
        Main n = new Main();
    }

    @Test
    public void clearStTest() {
        String in = "История войны и мира, пронизанная множеством сюжетных линий, русс";
        String out = "История войны и мира пронизанная множеством сюжетных линий ";
        Assert.assertEquals(out, n.clearSt(in));
    }

}
