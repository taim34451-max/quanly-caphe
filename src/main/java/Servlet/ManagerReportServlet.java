package Servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import DAO.ManagerReportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Report")
public class ManagerReportServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManagerReportDAO dao = new ManagerReportDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LocalDate today = LocalDate.now();
		
		
//		LocalDate date1 = LocalDate.of(2026, 8, 8);
//		change the date to test

		BigDecimal daily = dao.getDailyRevenue(today);

		BigDecimal weekly = dao.getWeeklyRevenue(today);

		BigDecimal monthly = dao.getMonthlyRevenue(today);

		
		LocalDate startOfMonth = today.withDayOfMonth(1);
		LocalDate endOfMonth = startOfMonth.plusMonths(1);
		
		List<Object[]> highestItems = dao.getHighestRevenueItems(startOfMonth, endOfMonth);
		List<Object[]> categorySales = dao.getSalesByCategory(startOfMonth, endOfMonth);
		
		request.setAttribute("dailyRevenue", daily);
		request.setAttribute("weeklyRevenue", weekly);
		request.setAttribute("monthlyRevenue", monthly);
		
		request.setAttribute("highestItems", highestItems);
		request.setAttribute("categorySales", categorySales);

		request.getRequestDispatcher("/Admin/ManagerReport.jsp").forward(request, response);
	}
}
