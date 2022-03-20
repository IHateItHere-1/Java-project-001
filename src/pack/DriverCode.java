package pack;

public class DriverCode {
    public static void main(String[] args) 
    {
    	FileFinder _file = new FileFinder("L:\\JAVA\\L3D");  	
    	RelationBuilder RB = new RelationBuilder();
    	RB.Build(_file.fileList());
    }
}
