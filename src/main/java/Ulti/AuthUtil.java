package Ulti;

import Entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthUtil {

	
	public static final String SESSION_USER="user";
	
	public static void setUser(HttpServletRequest req, Users user) {
		HttpSession session = req.getSession();
		session.setAttribute(SESSION_USER, user);
	}
	
	
	public static Users getUser(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session==null) return null;
		return (Users) session.getAttribute(SESSION_USER);
	}
	
	
	public static boolean isAuthenticated(HttpServletRequest req) {
		return getUser(req) != null;		
	}
	
	public static boolean isManager(HttpServletRequest req) {
		Users u = getUser(req);
		return u.getRole()!= "MANAGER" ? true : false ;
	}
	
	public static void clear(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute(SESSION_USER);
	}
}
