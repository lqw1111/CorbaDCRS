package CorbaDCRS;


/**
* CorbaDCRS/DCRSOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从CorbaDCRS.idl
* 2018年10月24日 星期三 下午02时53分47秒 EDT
*/

public interface DCRSOperations 
{
  String addCourse (String courseId, String semester);
  String removeCourse (String courseId, String semester);
  String[] listCourseAvailability (String semester);
  String enrolCourse (String studentId, String courseId, String semester);
  String dropCourse (String studentId, String courseId);
  String[] getClassSchedule (String studentId);
  String swapCourse (String studentID, String newCourseID, String oldCourseID);
  void shutdown ();
} // interface DCRSOperations
