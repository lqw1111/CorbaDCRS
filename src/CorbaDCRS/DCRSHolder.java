package CorbaDCRS;

/**
* CorbaDCRS/DCRSHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从CorbaDCRS.idl
* 2018年10月24日 星期三 下午02时53分47秒 EDT
*/

public final class DCRSHolder implements org.omg.CORBA.portable.Streamable
{
  public CorbaDCRS.DCRS value = null;

  public DCRSHolder ()
  {
  }

  public DCRSHolder (CorbaDCRS.DCRS initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CorbaDCRS.DCRSHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CorbaDCRS.DCRSHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CorbaDCRS.DCRSHelper.type ();
  }

}
