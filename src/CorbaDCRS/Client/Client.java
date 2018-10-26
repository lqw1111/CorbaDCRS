package CorbaDCRS.Client;

import CorbaDCRS.DCRS;
import CorbaDCRS.DCRSHelper;
import CorbaDCRS.ServentImpl.DCRSImpl;
import CorbaDCRS.entity.certificateIdentity;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
    public static DCRS connect(String department){
        DCRS dcrsImpl = null;
        try{
            // create and initialize the ORB
            ORB orb = ORB.init(new String[]{"-ORBInitialPort","1050","-ORBInitialHost","localhost"}, null);

            // get the root naming context
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = department;
            dcrsImpl = DCRSHelper.narrow(ncRef.resolve_str(name));

        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
        return dcrsImpl;
    }

    public static certificateIdentity parseStatus(String cmd) {
        String department = cmd.substring(0,4);
        String status = cmd.substring(4,5);
        String id = cmd.substring(5,cmd.length());

        certificateIdentity certificateIdentity = new certificateIdentity(department,status,id);

        return certificateIdentity;
    }
}


