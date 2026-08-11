package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/barista/*", "/api/barista/*"})
public class AuthFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String contextPath = httpRequest.getContextPath();
        
        // 🔑 Sửa thành đường dẫn /login chính xác
        String loginURL = contextPath + "/login";

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        String requestURI = httpRequest.getRequestURI();
        
        boolean isAssetRequest = requestURI.contains("/assets/");
        boolean isLoginRequest = requestURI.endsWith("/login") || requestURI.endsWith("/loginBarista.jsp");

        if (loggedIn || isLoginRequest || isAssetRequest) {
            chain.doFilter(request, response);
        } else {
            // 🔑 Đá về /login nếu chưa đăng nhập (Tránh lỗi 404)
            httpResponse.sendRedirect(loginURL);
        }
    }

    @Override
    public void destroy() {}
}