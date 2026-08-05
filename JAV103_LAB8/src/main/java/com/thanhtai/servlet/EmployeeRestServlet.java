package com.thanhtai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.thanhtai.model.ModelEmployee;
import com.thanhtai.util.UtilRestIO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/employees/*")
public class EmployeeRestServlet extends HttpServlet {
    // Khởi tạo danh sách Map lưu trữ tạm thời trong RAM thay cho database
    private static final Map<String, ModelEmployee> map = new HashMap<>(Map.of(
        "NV01", new ModelEmployee("NV01", "Nhân viên 01", true, 500),
        "NV02", new ModelEmployee("NV02", "Nhân viên 02", false, 1500),
        "NV03", new ModelEmployee("NV03", "Nhân viên 03", true, 5000),
        "NV04", new ModelEmployee("NV04", "Nhân viên 04", false, 2500),
        "NV05", new ModelEmployee("NV05", "Nhân viên 05", true, 3500)
    ));

    // GET: /employees hoặc /employees/ID
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String info = req.getPathInfo();
        if (info == null || info.length() == 0 || info.equals("/")) {
            UtilRestIO.writeObject(resp, map.values());
        } else {
            String id = info.substring(1).trim();
            UtilRestIO.writeObject(resp, map.get(id));
        }
    }

    // POST: /employees
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        ModelEmployee employee = UtilRestIO.readObject(req, ModelEmployee.class);
        map.put(employee.getId(), employee);
        UtilRestIO.writeObject(resp, employee);
    }

    // PUT: /employees/ID
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String id = req.getPathInfo().substring(1).trim();
        ModelEmployee employee = UtilRestIO.readObject(req, ModelEmployee.class);
        map.put(id, employee);
        UtilRestIO.writeEmptyObject(resp);
    }

    // DELETE: /employees/ID
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String id = req.getPathInfo().substring(1).trim();
        map.remove(id);
        UtilRestIO.writeEmptyObject(resp);
    }
}