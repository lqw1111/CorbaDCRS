package CorbaDCRS.Server;

import CorbaDCRS.DCRS;
import CorbaDCRS.DCRSHelper;
import CorbaDCRS.Log.LoggerFormatter;
import CorbaDCRS.ServentImpl.DCRSImpl;
import CorbaDCRS.ServentImpl.UdpWokerThread;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentServer {
    private DCRSImpl servent;
    public Logger logger;

    public DepartmentServer(Logger logger, DCRSImpl servent){
        this.servent = servent;
        this.logger = logger;
    }

    public void startCorbaServer(String department){
        try{
            // create and initialize the ORB
            ORB orb = ORB.init(new String[]{"-ORBInitialHost", "localhost", "-ORBInitialPort", "1050"}, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            DCRSImpl dcrsImpl = this.servent;
            dcrsImpl.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(dcrsImpl);
            DCRS href = DCRSHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // bind the Object Reference in Naming
            String name = department;
            NameComponent path[] = ncRef.to_name( name );
            ncRef.rebind(path, href);

            logger.info(department + "server ready and waiting ...");

            // wait for invocations from clients
            orb.run();
        }

        catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Server Exiting ...");
    }

    public void startUdpServer(int udpPort) throws IOException {
        DatagramSocket socket = new DatagramSocket(udpPort);
        DatagramPacket packet = null;
        byte[] data = null;
        int count = 0;
        logger.info(" Upd Server Start");
        while(true)
        {
            data = new byte[1024];
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            logger.info("Server Recv Message :" + new String(packet.getData(), 0, packet.getLength()));
            Thread thread = new Thread(new UdpWokerThread(socket, packet, this.servent));
            thread.start();
            count++;
//            System.out.println("Server Connected：" + count);
            InetAddress address = packet.getAddress();
//            System.out.println("Server IP："+address.getHostAddress());

        }
    }

    public static void configLogger(String department , Logger logger) throws IOException {
        logger.setLevel(Level.ALL);
        FileHandler compFileHandler = new FileHandler(department + ".log");
        compFileHandler.setFormatter(new LoggerFormatter());
        logger.addHandler(compFileHandler);
    }

    public static void main(String[] args) throws IOException {
        Logger compLogger = Logger.getLogger("comp.server.log");
        configLogger("compServer",compLogger);

        Logger soenLogger = Logger.getLogger("soen.server.log");
        configLogger("soenServer",soenLogger);

        Logger inseLogger = Logger.getLogger("inse.server.log");
        configLogger("inseServer",inseLogger);

        DCRSImpl compServent = new DCRSImpl("comp",compLogger);
        DepartmentServer compServer = new DepartmentServer(compLogger, compServent);

        DCRSImpl soenServent = new DCRSImpl("soen", soenLogger);
        DepartmentServer soenServer = new DepartmentServer(soenLogger, soenServent);

        DCRSImpl inseServent = new DCRSImpl("inse", inseLogger);
        DepartmentServer inseServer = new DepartmentServer(inseLogger, inseServent);

        Runnable compTask = () -> {
            try {
                compServer.startUdpServer(1112);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable compTask2 = () -> {
            compServer.startCorbaServer( "comp");
        };

        Runnable soneTask = () -> {
            try {
                soenServer.startUdpServer(2223);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable soneTask2 = () -> {
            soenServer.startCorbaServer("soen");
        };

        Runnable inseTask = () -> {
            try {
                inseServer.startUdpServer(3334);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable inseTask2 = () -> {
            inseServer.startCorbaServer( "inse");
        };

        Thread compThread = new Thread(compTask);
        Thread compThread2 = new Thread(compTask2);

        Thread soenThread = new Thread(soneTask);
        Thread soenThread2 = new Thread(soneTask2);

        Thread inseThread = new Thread(inseTask);
        Thread inseThread2 = new Thread(inseTask2);

        compThread.start();
        compThread2.start();

        soenThread.start();
        soenThread2.start();

        inseThread.start();
        inseThread2.start();

    }

}
