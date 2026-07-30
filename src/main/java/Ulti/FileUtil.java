package Ulti;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
public static final String FOLDER = "/upload/";

public static String upload(HttpServletRequest req, String name) {
	try {
		Part part = req.getPart(name);
		String fileName = part.getSubmittedFileName();
		if(fileName == null|| fileName.isEmpty()) return null;
		String exit = fileName.substring(fileName.lastIndexOf("."));
		String uniqueName = System.currentTimeMillis()+exit;
		
		String realPath = req.getServletContext().getRealPath(uniqueName);
		
		if(!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(fileName);
		return uniqueName;
	} catch (Exception e) {
		// TODO: handle exception
		return "";
	}
}

public static boolean delete (HttpServletRequest req, String name) {
	try {
		if(name==null|| name.isEmpty()) return false;
		String realPath = req.getServletContext().getRealPath(FOLDER);
		File file = new File(realPath,name);
		return file.exists() && file.isFile()&& file.delete();
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}
}
}
