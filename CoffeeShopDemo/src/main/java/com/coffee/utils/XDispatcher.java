package com.coffee.utils;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class XDispatcher {
	public static void forward(String view, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			request.setAttribute("view", view);
			var layout = "/coffee/coffee.jsp";
			request.getRequestDispatcher(layout).forward(request, response);
		}
}
