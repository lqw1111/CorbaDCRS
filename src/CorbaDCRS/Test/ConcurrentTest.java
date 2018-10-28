
import CorbaDCRS.Client.Client;
import CorbaDCRS.DCRS;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentTest {

    @Test
    public void test(){
        Client client = new Client();
        DCRS dcrs = client.connect("comp");

        dcrs.enrolCourse("comps1111","soen1","fall");
        dcrs.enrolCourse("comps2222","soen1","fall");

        Runnable t1 = () -> {
            dcrs.swapCourse("comps1111", "inse1","soen1");
        };
    }
}
