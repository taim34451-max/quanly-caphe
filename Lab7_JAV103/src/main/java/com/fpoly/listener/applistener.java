package com.fpoly.listener;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class applistener implements HttpSessionListener, ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent e) {
        ServletContext application = e.getServletContext();
        Integer visitors = 0;
        try {
            String path = application.getRealPath("/visitors.txt");
            if (Files.exists(Paths.get(path))) {
                List<String> lines = Files.readAllLines(Paths.get(path));
                visitors = Integer.valueOf(lines.get(0));
            } else {
                visitors = 10000;
            }
        } catch (Exception e2) {
            visitors = 10000;
        }
        application.setAttribute("visitors", visitors);
    }

    @Override
    public void sessionCreated(HttpSessionEvent e) {
        HttpSession session = e.getSession();
        ServletContext application = session.getServletContext();
        Integer visitors = (Integer) application.getAttribute("visitors");
        if (visitors == null) {
            visitors = 10000;
        }
        application.setAttribute("visitors", visitors + 1);
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {
        ServletContext application = e.getServletContext();
        Integer visitors = (Integer) application.getAttribute("visitors");
        try {
            String path = application.getRealPath("/visitors.txt");
            byte[] data = String.valueOf(visitors).getBytes();
            Files.write(Paths.get(path), data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
    }
}