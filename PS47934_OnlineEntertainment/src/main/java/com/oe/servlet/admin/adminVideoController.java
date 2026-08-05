package com.oe.servlet.admin;

import java.io.IOException;
import java.util.List;
import com.oe.dao.videoDAO;
import com.oe.entity.video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
    "/admin/video", "/admin/video/edit/*", "/admin/video/create",
    "/admin/video/update", "/admin/video/delete", "/admin/video/reset"
})
public class adminVideoController extends HttpServlet {
    private videoDAO dao = new videoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        if (uri.contains("/admin/video/edit/")) {
            String id = uri.substring(uri.lastIndexOf("/") + 1);
            video v = dao.findById(id);
            req.setAttribute("video", v); 
            req.setAttribute("activeTab", "edit");
        } else if (uri.contains("reset")) {
            req.setAttribute("video", new video());
        }
        
        List<video> videos = dao.findAll(); 
        req.setAttribute("videos", videos);
        req.setAttribute("view", "/views/admin/video.jsp"); 
        
        req.getRequestDispatcher("/views/admin/adminLayout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String poster = req.getParameter("poster");
        String description = req.getParameter("description");
        String viewsParam = req.getParameter("views");
        Integer views = (viewsParam != null && !viewsParam.isEmpty()) ? Integer.parseInt(viewsParam) : 0;
        Boolean active = req.getParameter("active") != null;
        
        try {
            if (uri.contains("create")) {
                dao.create(new video(id, title, poster, views, description, active));
            } else if (uri.contains("update")) {
                dao.update(new video(id, title, poster, views, description, active));
            } else if (uri.contains("delete")) {
                dao.delete(id);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        doGet(req, resp);
    }
}