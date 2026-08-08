package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UserDAO;
import entity.User;
import ultis.AuthFilter;
import ultis.AuthUtil;

/**
 * Servlet implementation class dang_nhap
 */
@WebServlet("/dang_nhap")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/User/dang-nhap.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO userdao = new UserDAO();
		try {
			String username = request.getParameter("username");
			User u = userdao.findById(username);
			if (u==null) {
				request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
				doGet(request, response);
			}else {
			String password = request.getParameter("password");
			
		
			if(u.getPassword().equals(password)) {
				System.out.println("OK");
				AuthUtil.setUser(request, u);
				request.getRequestDispatcher("//trang-chu.jsp").forward(request, response);
	
			}
			doGet(request, response);
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
			doGet(request, response);
		}
		
		
		
		
}}
