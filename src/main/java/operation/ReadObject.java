package operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {

	Properties p = new Properties();
 
	public Properties getObjectRepository() throws IOException{
		//Read object repository file
		System.out.println("before file read");
		//InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\objects\\object.properties"));
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\objects\\object.properties"));
		//load all objects
		p.load(stream);
		System.out.println("loading objects");
		 return p;
	}
	
}
