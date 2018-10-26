package CorbaDCRS;


/**
* CorbaDCRS/DCRSPOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从CorbaDCRS.idl
* 2018年10月24日 星期三 下午02时53分47秒 EDT
*/

public abstract class DCRSPOA extends org.omg.PortableServer.Servant
 implements CorbaDCRS.DCRSOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addCourse", new java.lang.Integer (0));
    _methods.put ("removeCourse", new java.lang.Integer (1));
    _methods.put ("listCourseAvailability", new java.lang.Integer (2));
    _methods.put ("enrolCourse", new java.lang.Integer (3));
    _methods.put ("dropCourse", new java.lang.Integer (4));
    _methods.put ("getClassSchedule", new java.lang.Integer (5));
    _methods.put ("swapCourse", new java.lang.Integer (6));
    _methods.put ("shutdown", new java.lang.Integer (7));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // CorbaDCRS/DCRS/addCourse
       {
         String courseId = in.read_string ();
         String semester = in.read_string ();
         String $result = null;
         $result = this.addCourse (courseId, semester);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // CorbaDCRS/DCRS/removeCourse
       {
         String courseId = in.read_string ();
         String semester = in.read_string ();
         String $result = null;
         $result = this.removeCourse (courseId, semester);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // CorbaDCRS/DCRS/listCourseAvailability
       {
         String semester = in.read_string ();
         String $result[] = null;
         $result = this.listCourseAvailability (semester);
         out = $rh.createReply();
         CorbaDCRS.listHelper.write (out, $result);
         break;
       }

       case 3:  // CorbaDCRS/DCRS/enrolCourse
       {
         String studentId = in.read_string ();
         String courseId = in.read_string ();
         String semester = in.read_string ();
         String $result = null;
         $result = this.enrolCourse (studentId, courseId, semester);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // CorbaDCRS/DCRS/dropCourse
       {
         String studentId = in.read_string ();
         String courseId = in.read_string ();
         String $result = null;
         $result = this.dropCourse (studentId, courseId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // CorbaDCRS/DCRS/getClassSchedule
       {
         String studentId = in.read_string ();
         String $result[] = null;
         $result = this.getClassSchedule (studentId);
         out = $rh.createReply();
         CorbaDCRS.listHelper.write (out, $result);
         break;
       }

       case 6:  // CorbaDCRS/DCRS/swapCourse
       {
         String studentID = in.read_string ();
         String newCourseID = in.read_string ();
         String oldCourseID = in.read_string ();
         String $result = null;
         $result = this.swapCourse (studentID, newCourseID, oldCourseID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 7:  // CorbaDCRS/DCRS/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CorbaDCRS/DCRS:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public DCRS _this() 
  {
    return DCRSHelper.narrow(
    super._this_object());
  }

  public DCRS _this(org.omg.CORBA.ORB orb) 
  {
    return DCRSHelper.narrow(
    super._this_object(orb));
  }


} // class DCRSPOA
