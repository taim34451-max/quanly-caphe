package com.thanhtai.Bai1.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;
import java.nio.file.*;
import java.util.List;

@WebListener
public class AppListener implements HttpSessionListener, ServletContextListener {

    // Khi app start
    @Override
    public void contextInitialized(ServletContextEvent e) {
        ServletContext application = e.getServletContext();
        Integer visitors = 0;

        try {
            String path = application.getRealPath("/visitors.txt");
            List<String> lines = Files.readAllLines(Paths.get(path));
            visitors = Integer.valueOf(lines.get(0));
        } catch (Exception ex) {
            visitors = 1000; // mặc định
        }

        application.setAttribute("visitors", visitors);
    }

    // Khi app tắt
    @Override
    public void contextDestroyed(ServletContextEvent e) {
        ServletContext application = e.getServletContext();
        Integer visitors = (Integer) application.getAttribute("visitors");

        try {
            String path = application.getRealPath("/visitors.txt");
            Files.write(Paths.get(path), String.valueOf(visitors).getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Khi có user truy cập
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        ServletContext application = e.getSession().getServletContext();
        Integer visitors = (Integer) application.getAttribute("visitors");

        application.setAttribute("visitors", visitors + 1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {}
}