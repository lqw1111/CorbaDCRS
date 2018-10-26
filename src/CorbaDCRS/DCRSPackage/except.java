package CorbaDCRS.DCRSPackage;


/**
* CorbaDCRS/DCRSPackage/except.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从CorbaDCRS.idl
* 2018年10月24日 星期三 下午02时53分47秒 EDT
*/

public final class except extends org.omg.CORBA.UserException
{
  public String reason = null;

  public except ()
  {
    super(exceptHelper.id());
  } // ctor

  public except (String _reason)
  {
    super(exceptHelper.id());
    reason = _reason;
  } // ctor


  public except (String $reason, String _reason)
  {
    super(exceptHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class except
