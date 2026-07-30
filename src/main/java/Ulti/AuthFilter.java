package Ulti;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter({"/manager/*","/employee/*"})
public class AuthFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		
		HttpServletRequest httpServReq = (HttpServletRequest) request;
		HttpServletResponse httpServRes = (HttpServletResponse) response;
		String uriString = httpServReq.getRequestURI();
		String error = "";	
		if(!AuthUtil.isAuthenticated(httpServReq)) {
			error = "401";
		} else if(AuthUtil.isManager(httpServReq)==3&& uriString.contains("/manager")) {
			error = "403";
		}
		if(!error.isEmpty()) {
			httpServReq.setAttribute("REDIRECT_URL",uriString);
			httpServRes.sendRedirect(httpServReq.getContextPath()+"/dang-nhap");
		}
		else {
		chain.doFilter(request, response);}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
