package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.OrderItem;

@WebServlet(urlPatterns = "/api/barista/orders")
public class OrderApiServlet extends HttpServlet {
    private final OrderDAO orderDAO = new OrderDAO();
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
        List<Order> orders = orderDAO.getOrdersByStatus("ALL");
        
        List<Map<String, Object>> serializedOrders = new ArrayList<>();
        for (Order order : orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("id", order.getId());
            orderMap.put("tableNumber", order.getTableNumber());
            orderMap.put("customerName", order.getCustomerName());
            orderMap.put("status", order.getStatus());
            orderMap.put("note", order.getReason() != null ? order.getReason() : "");
            orderMap.put("totalPrice", order.getTotalPrice());
            
            // Xử lý định dạng Thời gian trực tiếp (Không sợ lỗi thiếu hàm)
            if (order.getCreatedAt() != null) {
                orderMap.put("createdAtFormatted", sdfFull.format(order.getCreatedAt()));
                orderMap.put("timeFormatted", sdfTime.format(order.getCreatedAt()));
                orderMap.put("createdAtMillis", order.getCreatedAt().getTime());
            } else {
                orderMap.put("createdAtFormatted", "");
                orderMap.put("timeFormatted", "");
                orderMap.put("createdAtMillis", 0);
            }
            
            List<Map<String, Object>> serializedItems = new ArrayList<>();
            // Kiểm tra chống NullPointer khi duyệt danh sách món
            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put("itemName", item.getItemName());
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