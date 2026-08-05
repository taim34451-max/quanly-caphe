package com.thanhtai.controller;

import com.thanhtai.dao.employeedao;
import com.thanhtai.model.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/employees/*")
public class employeecontroller extends HttpServlet {
    private employeedao dao;

    @Override
    public void init() {
        dao = new employeedao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null) action = "/";

        switch (action) {
            case "/create":
                req.getRequestDispatcher("/views/create.jsp").forward(req, resp);
                break;
            case "/edit":
                String editCode = req.getParameter("code");
                req.setAttribute("emp", dao.getByCode(editCode));
                req.getRequestDispatcher("/views/edit.jsp").forward(req, resp);
                break;
            case "/view":
                String viewCode = req.getParameter("code");
                req.setAttribute("emp", dao.getByCode(viewCode));
                req.getRequestDispatcher("/views/view.jsp").forward(req, resp);
                break;
            default: // Lấy toàn bộ và hiển thị List
                req.setAttribute("empList", dao.getAll());
                req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getPathInfo();

        switch (action) {
            case "/create":
                insertEmployee(req, resp);
                break;
            case "/update":
                updateEmployee(req, resp);
                break;
            case "/delete":
                String code = req.getParameter("code");
                dao.delete(code);
                resp.sendRedirect(req.getContextPath() + "/employees");
                break;
        }
    }

    // Logic thêm mới kèm Server-side Validation
    private void insertEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("emp_code");
        String name = req.getParameter("full_name");
        String email = req.getParameter("email");

        if (dao.insert(new employee(code, name, email))) {
            resp.sendRedirect(req.getContextPath() + "/employees"); 
        } else {
            // Thay vì khẳng định trùng mã, hãy báo lỗi chung để đi check kết nối
            req.setAttribute("error", "Lưu thất bại! Hãy kiểm tra lại kết nối SQL Server hoặc trùng mã.");
            req.setAttribute("emp_code", code);
            req.setAttribute("full_name", name);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/create.jsp").forward(req, resp);
        }
  }
    

    // Logic cập nhật
    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("emp_code"); // Field ẩn (readonly)
        String name = req.getParameter("full_name");
        String email = req.getParameter("email");

        if (validate(req, code, name, email)) {
            dao.update(new employee(code, name, email));
            resp.sendRedirect(req.getContextPath() + "/employees");
        } else {
            req.setAttribute("emp", new employee(code, name, email));
            req.getRequestDispatcher("/views/edit.jsp").forward(req, resp);
        }
    }

    // Server-side validation
    private boolean validate(HttpServletRequest req, String code, String name, String email) {
        boolean isValid = true;
        if (code == null || code.trim().isEmpty()) {
            req.setAttribute("errCode", "Mã không được để trống");
            isValid = false;
        }
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("errName", "Tên không được để trống");
            isValid = false;
        }
        if (email == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
            req.setAttribute("errEmail", "Email sai định dạng");
            isValid = false;
        }
        return isValid;
    }
}