import step5.src.de.imut.oop.talkv3.communication.CommunicatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class CommunicatorFactoryTest {
    @Test
    public void singletonTest() {
        CommunicatorFactory factory1 = CommunicatorFactory.getInstance();
        CommunicatorFactory factory2 = CommunicatorFactory.getInstance();
        Assert.assertTrue(factory1 == factory2);
    }

}