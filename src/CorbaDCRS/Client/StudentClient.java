package CorbaDCRS.Client;

import CorbaDCRS.DCRS;
import CorbaDCRS.Log.LoggerFormatter;
import CorbaDCRS.entity.Student;
import CorbaDCRS.entity.certificateIdentity;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentClient extends Client {
    public String studentId;

    public DCRS Login(){

        System.out.println("Input Your Id:");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        studentId = cmd;

        certificateIdentity certificateIdentity = parseStatus(cmd);
        if (certificateIdentity.getStatus().equals("s")){

            DCRS servent = connect(certificateIdentity.getDepartment());
            return servent;
        } else {
            System.out.println("It's Not An Student Account");
            return null;
        }
    }

    public void startStudentClient(DCRS servent , String studentId) throws RemoteException {
        Logger logger = Logger.getLogger("client.log");
        logger.setLevel(Level.ALL);

        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(studentId + ".log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new LoggerFormatter());
        logger.addHandler(fileHandler);
        logger.info(studentId + "  Login Successful");

        Scanner sc = new Scanner(System.in);
        String cmd = "";

        while(true){
            System.out.println("Input your operate: \n           " +
                    "1.Enroll Course\n           " +
                    "2.Drop Course\n           " +
                    "3.Get Class Schedule\n           " +
                    "4.Swap Course");
            cmd = sc.nextLine();

            String result = "";
            String courseId = "";

            switch(cmd) {
                case "1" :
                    System.out.println("Input The Course Id : ");
                    courseId = sc.nextLine();
                    System.out.println("Input The Semester : ");
                    String semester = sc.nextLine();
                    result = servent.enrolCourse(this.studentId ,courseId,semester);
                    logger.info("Operation: " + result);
                    break;
                //dropCourse
                case "2" :
                    System.out.println("Input The Course Id : ");
                    courseId = sc.nextLine();
                    result = servent.dropCourse(this.studentId ,courseId);
                    logger.info("Operation: " + result);
                    break;
                //getClassSchedule
                case "3" :
                    String[] classSchedule = servent.getClassSchedule(this.studentId);
                    if (classSchedule == null){
                        logger.info("The Student Does Not Exist! Please Contact With Advisor!");
                    }else {
                        System.out.println("Student " + this.studentId + ":");
                        for (String course :
                                classSchedule) {
                            System.out.println("                      " + course);
                        }
                        logger.info("Operation: get class schedule");
                    }
                    break;
                case "4" :
                    System.out.println("Input The New Course Id: ");
                    String newCourseId = sc.nextLine();
                    System.out.println("Input The Old Course Id: ");
                    String oldCourseId = sc.nextLine();
                    result = servent.swapCourse(this.studentId, newCourseId, oldCourseId);
                    logger.info("Operation: " + result);
                    break;
                default :
                    System.out.println("Invalid Command!");
            }

        }
    }

    public static void main(String[] args) throws RemoteException{
        StudentClient studentClient = new StudentClient();
        DCRS servent = studentClient.Login();
        if (servent != null){
            studentClient.startStudentClient(servent, studentClient.studentId);
        }
    }
}
