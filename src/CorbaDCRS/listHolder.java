package CorbaDCRS;


/**
* CorbaDCRS/listHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从CorbaDCRS.idl
* 2018年10月24日 星期三 下午02时53分47秒 EDT
*/

public final class listHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public listHolder ()
  {
  }

  public listHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CorbaDCRS.listHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CorbaDCRS.listHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CorbaDCRS.listHelper.type ();
  }

}
