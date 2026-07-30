package Ulti;

import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.http.HttpServletRequest;

public class ParamUtil {

	
	public static String getString(HttpServletRequest req, String name) {
		try {
			return req.getParameter(name);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	public static int getInt(HttpServletRequest req, String name) {
		try {
	
			return Integer.parseInt(req.getParameter(name));
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public static java.util.Date getDate(HttpServletRequest req, String name, String pattern) {
		try {
			String value = req.getParameter(name);
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(value);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
