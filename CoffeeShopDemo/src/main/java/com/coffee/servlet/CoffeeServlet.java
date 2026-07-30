package com.coffee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.coffee.entity.coffee;
import com.coffee.service.CoffeeService;
import com.coffee.service.CoffeeServiceImpl;
import com.coffee.utils.XDispatcher;

/**
 * Servlet implementation class CoffeeServlet
 */
@WebServlet({
	"/coffee/menu",
	"/coffee/create",
	"/coffee/update",
	"/coffee/delete/*",
	"/coffee/edit/*",
	"/coffee/clear"
	})
@MultipartConfig(maxFileSize = 1204*1204*50)
public class CoffeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CoffeeService service = new CoffeeServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoffeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		var path = req.getServletPath();
		coffee coffee = new coffee();
		
		if (path.contains("delete")) {
			String id = req.getPathInfo().substring(1);
			service.deleteByID(id);
		} else if (path.contains("edit")) {
			String id = req.getPathInfo().substring(1);
			coffee = service.findByID(id);
		}
		
		req.setAttribute("form", coffee);
		req.setAttribute("items", service.findAll());
		
		req.getRequestDispatcher("/coffee/coffee.jsp").forward(req, res);
		
//		XDispatcher.forward("/coffee/coffee.jsp", req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		var path = req.getServletPath();
		coffee coffee = new coffee();
		
		try {
	        BeanUtils.populate(coffee, req.getParameterMap());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		 Part file = req.getPart("photo-file");

		    if (file.getSize() > 0) {

		        coffee.setPhoto(file.getSubmittedFileName());

		        String filename = req.getServletContext()
		                .getRealPath("/photos/" + coffee.getPhoto());

		        new File(filename).getParentFile().mkdir();

		        file.write(filename);

		    } else {

		        if (path.contains("create")) {
		            coffee.setPhoto("coffee.png");
		        } else {
		            coffee oldCoffee = service.findByID(coffee.getId());
		            coffee.setPhoto(oldCoffee.getPhoto());
		        }
		    }

		    if (path.contains("create")) {
		        service.create(coffee);

		    } else if (path.contains("update")) {
		        service.update(coffee);
		    }

		    req.setAttribute("form", coffee);
		    req.setAttribute("items", service.findAll());

		    XDispatcher.forward("/coffee/index.jsp", req, res);
		
	}

}
