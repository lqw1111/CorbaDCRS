
import CorbaDCRS.Client.Client;
import CorbaDCRS.DCRS;
import org.testng.annotations.Test;


public class ConcurrentTest {

    @Test
    public void test(){
        Client client = new Client();
        DCRS dcrs = client.connect("comp");

        Client iclient = new Client();
        DCRS idcrs = iclient.connect("inse");

        dcrs.enrolCourse("comps1111","soen1","fall");
        dcrs.enrolCourse("comps2222","soen1","fall");
        idcrs.enrolCourse("inses1111","inse1","fall");
        idcrs.enrolCourse("inses2222","inse1","fall");
//        dcrs.enrolCourse("comps4444","soen1","fall");

        Runnable t1 = () -> {
            dcrs.swapCourse("comps1111", "inse1","soen1");
        };

        Runnable t2 = () -> {
            dcrs.enrolCourse("comps2222", "soen1", "fall");
        };

        Runnable t3 = () -> {
            dcrs.enrolCourse("comps3333", "soen1", "fall");
        };

        Runnable t4 = () -> {
            dcrs.enrolCourse("comps4444", "soen1", "fall");
        };

        Runnable t5 = () -> {
            dcrs.enrolCourse("comps5555", "soen1", "fall");
        };

        Runnable t6 = () -> {
            dcrs.enrolCourse("comps6666", "soen1", "fall");
        };

        Thread th1 = new Thread(t1);
        Thread th2 = new Thread(t2);
        Thread th3 = new Thread(t3);
        Thread th4 = new Thread(t4);
        Thread th5 = new Thread(t5);
        Thread th6 = new Thread(t6);

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        th6.start();

    }

    @Test
    public void testGetClass(){
        Client client = new Client();
        DCRS dcrs = client.connect("comp");
    }
}
