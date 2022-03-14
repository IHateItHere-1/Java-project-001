package pack;

public class DriverCode {
    public static void main(String[] args) 
    {
    	DB DBobj = new DB();
    	
        XML XMLobj = new XML();
        XMLobj.GetXMLFilePTID("FAKESTUDY001.xml");
        L3DDataType L3Dobj = new L3DDataType("","");
        //String str = L3Dobj.GetPTID();

    }
}
