package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import DAO.BillDAO;
import Entity.Bill;
import Entity.BillDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/barista/orders")
public class OrderApiServlet extends HttpServlet {
    private final BillDAO orderDAO = new BillDAO();
    private final Gson gson = new Gson();

    // Định dạng Ngày + Giờ
    private final SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
       
        Map<String, Integer> counts = orderDAO.getStatusCounts();
        List<Bill> orders = orderDAO.getOrdersByStatus("ALL");
        
        List<Map<String, Object>> serializedOrders = new ArrayList<>();
        for (Bill order : orders) {
        	
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("id", order.getBillId());
            orderMap.put("tableNumber", order.getTableNumber());
            if (order.getUser() != null) {
                orderMap.put("customerName", order.getUser().getUserName());
            } else {
                orderMap.put("customerName", "Khách");
            }
            System.out.println(
            	    "API BILL #" + order.getBillId()
            	    + " STATUS = [" + order.getStatus() + "]"
            	);
            orderMap.put("status", order.getStatus());
            orderMap.put("note", order.getNote() != null ? order.getNote() : "");
            orderMap.put("totalPrice", order.getTotal());
            
            // Xử lý định dạng Thời gian trực tiếp (Không sợ lỗi thiếu hàm)
            if (order.getCreatedDate() != null) {
                orderMap.put("createdAtFormatted", sdfFull.format(order.getCreatedDate()));
                orderMap.put("timeFormatted", sdfTime.format(order.getCreatedDate()));
                orderMap.put("createdAtMillis", order.getCreatedDate().getTime());
            } else {
                orderMap.put("createdAtFormatted", "");
                orderMap.put("timeFormatted", "");
                orderMap.put("createdAtMillis", 0);
            }
            
            List<Map<String, Object>> serializedItems = new ArrayList<>();
            // Kiểm tra chống NullPointer khi duyệt danh sách món
            if (order.getBillDetails() != null) {
            	
                for (BillDetail item : order.getBillDetails()) {
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put(
                    	    "itemName",
                    	    item.getProduct() != null
                    	        ? item.getProduct().getProductName()
                    	        : "Món không xác định"
                    	);
                    itemMap.put("quantity", item.getQuantity());
                    itemMap.put("note", item.getNote() != null ? item.getNote() : "");
                    itemMap.put("price", item.getPrice());
                    serializedItems.add(itemMap);
                }
            }
            orderMap.put("items", serializedItems);
            serializedOrders.add(orderMap);
        }
        
        Map<String, Object> apiResponse = new HashMap<>();
        apiResponse.put("counts", counts);
        apiResponse.put("orders", serializedOrders);
        
        try (PrintWriter out = response.getWriter()) {
            out.print(gson.toJson(apiResponse));
            out.flush();
        }
    }
}