
package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.BillDAO;
import DAO.CartDAO;
import DAO.DrinkDAO;
import Entity.Bill;
import Entity.BillDetail;
import Entity.CartItem;
import Entity.Product;
import Entity.Users;
import Ulti.AuthUtil;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.trim().isEmpty()) {
            action = "view";
        }

        HttpSession session = request.getSession();

        CartDAO cart = (CartDAO) session.getAttribute("cart");

        if (cart == null) {
            cart = new CartDAO();
            session.setAttribute("cart", cart);
        }

        DrinkDAO drinkDAO = new DrinkDAO();

        try {

            switch (action) {

                // ==========================================
                // THÊM SẢN PHẨM VÀO GIỎ
                // ==========================================
                case "add": {

                    String idParam = request.getParameter("id");
                    String size = request.getParameter("size");
                    String qtyParam = request.getParameter("sl");

                    System.out.println("===== CART ADD =====");
                    System.out.println("ID: " + idParam);
                    System.out.println("Size: " + size);
                    System.out.println("Quantity: " + qtyParam);

                    // Nếu không có sl thì thử lấy quantity
                    if (qtyParam == null || qtyParam.trim().isEmpty()) {
                        qtyParam = request.getParameter("quantity");
                    }

                    int quantity = 1;

                    try {
                        if (qtyParam != null && !qtyParam.trim().isEmpty()) {
                            quantity = Integer.parseInt(qtyParam);
                        }
                    } catch (NumberFormatException e) {
                        quantity = 1;
                    }

                    // Không cho quantity <= 0
                    if (quantity <= 0) {
                        quantity = 1;
                    }

                    // Kiểm tra ID
                    if (idParam == null || idParam.trim().isEmpty()) {
                        System.out.println("ERROR: ID is empty!");

                        response.sendRedirect(
                            request.getContextPath() + "/cart"
                        );
                        return;
                    }

                    // Kiểm tra size
                    if (size == null || size.trim().isEmpty()) {
                        // Nếu là bánh thì không có size
                        size = "";
                    }

                    try {

                        int productId = Integer.parseInt(idParam);

                        Product product = drinkDAO.FindByID(productId);

                        if (product != null) {

                            System.out.println(
                                "Product found: "
                                + product.getProductName()
                            );

                            System.out.println(
                                "Price: "
                                + product.getPrice()
                            );

                            /*
                             * Thêm sản phẩm vào Cart.
                             *
                             * CartDAO phải có:
                             *
                             * addItem(Product product, String size, int quantity)
                             */
                            cart.addItem(product, size, quantity);

                            session.setAttribute("cart", cart);

                            System.out.println(
                                "Cart total items: "
                                + cart.getItem().size()
                            );

                        } else {

                            System.out.println(
                                "ERROR: Product not found: "
                                + productId
                            );
                        }

                    } catch (NumberFormatException e) {

                        System.out.println(
                            "ERROR: Invalid product ID: "
                            + idParam
                        );

                    } catch (Exception e) {

                        System.out.println(
                            "ERROR adding product: "
                            + e.getMessage()
                        );

                        e.printStackTrace();
                    }

                    response.sendRedirect(
                        request.getContextPath() + "/cart"
                    );

                    return;
                }


                // ==========================================
                // XÓA SẢN PHẨM
                // ==========================================
                case "delete": {

                    String idParam = request.getParameter("id");
                    String size = request.getParameter("size");

                    if (idParam != null && !idParam.trim().isEmpty()) {

                        try {

                            int productId = Integer.parseInt(idParam);

                            cart.deleteItem(productId, size);

                            session.setAttribute("cart", cart);

                        } catch (NumberFormatException e) {

                            System.out.println(
                                "ERROR: Invalid product ID: "
                                + idParam
                            );
                        }
                    }

                    response.sendRedirect(
                        request.getContextPath() + "/cart"
                    );

                    return;
                }


                // ==========================================
                // CẬP NHẬT SỐ LƯỢNG
                // ==========================================
                case "update": {

                    String idParam = request.getParameter("id");
                    String qtyParam = request.getParameter("quantity");
                    String size = request.getParameter("size");

                    if (idParam != null
                            && !idParam.trim().isEmpty()
                            && qtyParam != null
                            && !qtyParam.trim().isEmpty()) {

                        try {

                            int productId =
                                Integer.parseInt(idParam);

                            int quantity =
                                Integer.parseInt(qtyParam);

                            if (quantity < 1) {
                                quantity = 1;
                            }

                            cart.updateItem(
                                productId,
                                quantity,
                                size
                            );

                            session.setAttribute(
                                "cart",
                                cart
                            );

                        } catch (NumberFormatException e) {

                            System.out.println(
                                "ERROR updating cart: "
                                + e.getMessage()
                            );
                        }
                    }

                    response.sendRedirect(
                        request.getContextPath() + "/cart"
                    );

                    return;
                }


                // ==========================================
                // THANH TOÁN
                // ==========================================
                case "checkout":

                    processCheckout(
                        request,
                        response,
                        session,
                        cart
                    );

                    return;


                // ==========================================
                // XEM GIỎ HÀNG
                // ==========================================
                default:

                    request.getRequestDispatcher(
                        "/gio-hang.jsp"
                    ).forward(request, response);

                    return;
            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                "error",
                "Có lỗi xảy ra khi xử lý giỏ hàng!"
            );

            request.getRequestDispatcher(
                "/gio-hang.jsp"
            ).forward(request, response);
        }
    }


    // ==========================================
    // POST
    // ==========================================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }


    // ==========================================
    // CHECKOUT
    // ==========================================
    private void processCheckout(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            CartDAO cart)
            throws IOException, ServletException {

        // Kiểm tra giỏ hàng rỗng
        if (cart.getItem() == null
                || cart.getItem().isEmpty()) {

            request.setAttribute(
                "error",
                "Giỏ hàng của bạn đang trống!"
            );

            request.getRequestDispatcher(
                "/gio-hang.jsp"
            ).forward(request, response);

            return;
        }


        // Lấy user hiện tại
        Users currentUser = AuthUtil.getUser(request);


        // ==========================================
        // TẠO BILL
        // ==========================================

        Bill bill = new Bill();

        bill.setUser(currentUser);

        String tableNumber =
            request.getParameter("tableNumber");

        if (tableNumber == null
                || tableNumber.trim().isEmpty()) {

            tableNumber = "01";
        }

        bill.setTableNumber(tableNumber);

        bill.setTotal(cart.getTotal());

        bill.setStatus("PENDING");

        bill.setCreatedDate(new Date());

        bill.setNote(
            request.getParameter("note")
        );


        // ==========================================
        // TẠO BILL DETAIL
        // ==========================================

        List<BillDetail> details =
            new ArrayList<>();

        for (CartItem item : cart.getItem()) {

            BillDetail detail =
                new BillDetail();

            detail.setBill(bill);

            detail.setProduct(
                item.getDrink()
            );

            detail.setQuantity(
                item.getQuantity()
            );


            /*
             * Giá phải lấy theo Size.
             *
             * CartItem cần có:
             *
             * getSize()
             */

            BigDecimal price =
                getPriceBySize(item);


            detail.setPrice(price);

            details.add(detail);
        }


        bill.setBillDetails(details);


        // ==========================================
        // LƯU BILL
        // ==========================================

        BillDAO billDAO =
            new BillDAO();

        boolean success =
            billDAO.createBill(bill);


        if (success) {

            cart.clear();

            session.setAttribute(
                "cart",
                cart
            );

            request.setAttribute(
                "message",
                "Đặt hàng thành công!"
            );

            request.getRequestDispatcher(
                "/gio-hang.jsp"
            ).forward(request, response);

        } else {

            request.setAttribute(
                "error",
                "Lỗi khi lưu hóa đơn, vui lòng thử lại!"
            );

            request.getRequestDispatcher(
                "/gio-hang.jsp"
            ).forward(request, response);
        }
    }


    // ==========================================
    // LẤY GIÁ THEO SIZE
    // ==========================================
    private BigDecimal getPriceBySize(CartItem item) {

        Product product =
            item.getDrink();

        String size =
            item.getSize();


        // Bánh / sản phẩm không có size
        if (size == null
                || size.trim().isEmpty()) {

            return product.getPrice();
        }


        // Lấy giá theo Size
        if ("S".equalsIgnoreCase(size)) {

            return product
                .getSizePrices()
                .getSizeS();

        } else if ("M".equalsIgnoreCase(size)) {

            return product
                .getSizePrices()
                .getSizeM();

        } else if ("L".equalsIgnoreCase(size)) {

            return product
                .getSizePrices()
                .getSizeL();
        }


        // Fallback
        return product.getPrice();}}
    
