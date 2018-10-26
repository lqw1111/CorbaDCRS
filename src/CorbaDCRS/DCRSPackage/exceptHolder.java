package CorbaDCRS.DCRSPackage;

/**
* CorbaDCRS/DCRSPackage/exceptHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从CorbaDCRS.idl
* 2018年10月24日 星期三 下午02时53分47秒 EDT
*/

public final class exceptHolder implements org.omg.CORBA.portable.Streamable
{
  public CorbaDCRS.DCRSPackage.except value = null;

  public exceptHolder ()
  {
  }

  public exceptHolder (CorbaDCRS.DCRSPackage.except initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CorbaDCRS.DCRSPackage.exceptHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CorbaDCRS.DCRSPackage.exceptHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CorbaDCRS.DCRSPackage.exceptHelper.type ();
  }

}
