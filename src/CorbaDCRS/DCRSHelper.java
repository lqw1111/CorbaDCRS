package CorbaDCRS;


/**
* CorbaDCRS/DCRSHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从CorbaDCRS.idl
* 2018年10月24日 星期三 下午02时53分47秒 EDT
*/

abstract public class DCRSHelper
{
  private static String  _id = "IDL:CorbaDCRS/DCRS:1.0";

  public static void insert (org.omg.CORBA.Any a, CorbaDCRS.DCRS that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CorbaDCRS.DCRS extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (CorbaDCRS.DCRSHelper.id (), "DCRS");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CorbaDCRS.DCRS read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_DCRSStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CorbaDCRS.DCRS value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static CorbaDCRS.DCRS narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CorbaDCRS.DCRS)
      return (CorbaDCRS.DCRS)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CorbaDCRS._DCRSStub stub = new CorbaDCRS._DCRSStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static CorbaDCRS.DCRS unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CorbaDCRS.DCRS)
      return (CorbaDCRS.DCRS)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CorbaDCRS._DCRSStub stub = new CorbaDCRS._DCRSStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
