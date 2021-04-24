package cybersoft.java09.constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class UploadFile {
	public static void handleFileUpload(Part part,String path) {
		
		 
		
		String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
		
		
		// if(!Files.exists(Path.of(path))) { Files.createDirectory(Path.of(path)); }
		 
		
		 try {
			part.write(path+"/"+filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
