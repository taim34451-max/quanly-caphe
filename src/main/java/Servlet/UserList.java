package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DAO.UserDAO;
import Entity.Users;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		UserDAO dao = new UserDAO();

// ==========================
// CREATE - MỞ FORM
// ==========================
		if ("add".equals(action)) {

			request.setAttribute("mode", "create");
			request.setAttribute("user", null);

			request.getRequestDispatcher("/Admin/UserForm.jsp").forward(request, response);
		}

// ==========================
// UPDATE - MỞ FORM EDIT
// ==========================
		else if ("UpdateUser".equals(action)) {

			String id = request.getParameter("id");

			if (id != null) {

				Integer userId = Integer.parseInt(id);

				Users user = dao.findById(userId);

				request.setAttribute("user", user);
				request.setAttribute("mode", "update");

				request.getRequestDispatcher("/Admin/UserForm.jsp").forward(request, response);
			}
		}

// ==========================
// DELETE USER
// ==========================
		else if ("DeleteUser".equals(action)) {

			String id = request.getParameter("id");

			if (id != null) {

				Integer userId = Integer.parseInt(id);

				dao.DeleteUser(userId);
			}

// Xóa xong quay lại danh sách
			response.sendRedirect(request.getContextPath() + "/UserList");
		}

// ==========================
// HIỂN THỊ DANH SÁCH USER
// ==========================
		else {

			List<Users> list = dao.findAll();

			System.out.println("SO LUONG USER = " + list.size());

			request.setAttribute("list", list);

			request.getRequestDispatcher("/Admin/UserList.jsp").forward(request, response);
		}
	}

// =====================================================
// POST
// =====================================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		UserDAO dao = new UserDAO();

// ==========================
// CREATE USER
// ==========================
		if ("create".equals(action)) {

			Users user = new Users();

			user.setUserName(request.getParameter("userName"));

			user.setPassword(request.getParameter("userPass"));

			user.setUserPhone(request.getParameter("userPhone"));

			

// Role hiện tại là String
			user.setRole(request.getParameter("role"));

			dao.CreateUser(user);

			response.sendRedirect(request.getContextPath() + "/UserList");
		}

// ==========================
// UPDATE USER
// ==========================
		else if ("updateUser".equals(action)) {

			String id = request.getParameter("userId");

			if (id != null) {

				Integer userId = Integer.parseInt(id);

				Users user = dao.findById(userId);

				if (user != null) {

					user.setUserName(request.getParameter("userName"));

					user.setPassword(request.getParameter("userPass"));

					user.setUserPhone(request.getParameter("userPhone"));


					// Role là String
					user.setRole(request.getParameter("role"));

					dao.UpdateUser(user);
				}
			}

			response.sendRedirect(request.getContextPath() + "/UserList");
		}
	}
}
