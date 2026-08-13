package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import DAO.DrinkDAO;
import Entity.Product;
import Entity.SizePrice;

@WebServlet("/DrinkServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,       // 1MB
    maxFileSize = 5 * 1024 * 1024,         // 5MB
    maxRequestSize = 10 * 1024 * 1024      // 10MB
)
public class DrinkServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // =====================================================
    // DO GET
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        DrinkDAO dao = new DrinkDAO();

        // =====================================================
        // CREATE FORM
        // /DrinkServlet?action=add
        // =====================================================

        if ("add".equals(action)) {

            request.setAttribute("edit", false);

            request.getRequestDispatcher(
                    "/Admin/DrinkForm.jsp"
            ).forward(request, response);

            return;
        }

        // =====================================================
        // UPDATE FORM
        // /DrinkServlet?action=update&id=1
        // =====================================================

        if ("update".equals(action)) {

            String id = request.getParameter("id");

            if (id != null && !id.isEmpty()) {

                try {

                    Product drink =
                            dao.FindByID(Integer.valueOf(id));

                    if (drink != null) {

                        request.setAttribute(
                                "drink",
                                drink
                        );

                        request.setAttribute(
                                "edit",
                                true
                        );

                        request.getRequestDispatcher(
                                "/Admin/DrinkForm.jsp"
                        ).forward(request, response);

                        return;
                    }

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }

            response.sendRedirect(
                    request.getContextPath()
                    + "/DrinkServlet"
            );

            return;
        }

        // =====================================================
        // DELETE
        // /DrinkServlet?action=delete&id=1
        // =====================================================

        if ("delete".equals(action)) {

            String id = request.getParameter("id");

            if (id != null && !id.isEmpty()) {

                try {

                    Product drink =
                            dao.FindByID(Integer.valueOf(id));

                    if (drink != null) {

                        // Xóa ảnh trước
                        deleteImage(
                                request,
                                drink.getProductIMG()
                        );

                        // Xóa product
                        dao.DeleteProduct(
                                Integer.valueOf(id)
                        );
                    }

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }

            response.sendRedirect(
                    request.getContextPath()
                    + "/DrinkServlet"
            );

            return;
        }

        // =====================================================
        // LIST
        // =====================================================

        List<Product> list = dao.findAll();

        request.setAttribute(
                "list",
                list
        );

        request.getRequestDispatcher(
                "/Admin/DrinkList.jsp"
        ).forward(request, response);
    }


    // =====================================================
    // DO POST
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action =
                request.getParameter("action");

        System.out.println(
                "ACTION = " + action
        );

        DrinkDAO dao = new DrinkDAO();


        // =====================================================
        // CREATE
        // =====================================================

        if ("create".equals(action)) {

            Product drink = new Product();

            // -------------------------------------------------
            // TÊN
            // -------------------------------------------------

            String drinkName =
                    request.getParameter("drinkName");

            drink.setProductName(drinkName);


            


            // -------------------------------------------------
            // CATEGORY
            // -------------------------------------------------

            drink.setCategory(
                    request.getParameter("category")
            );
            
            // -------------------------------------------------
            // GIÁ
            // -------------------------------------------------
            System.out.println("Giá");
            if(drink.getCategory().equals("Bánh")) {
                String price =
                        request.getParameter(
                                "price"
                        );

                if (price != null
                        && !price.isEmpty()) {

                    drink.setPrice(
                            new BigDecimal(price)
                    );
                }}
                else {
                	String priceS = request.getParameter("priceS");
                	String priceM = request.getParameter("priceM");
                	String priceL = request.getParameter("priceL");
                	
                	if( priceS != null && !priceS.isEmpty() &&
                			 priceM != null && !priceM.isEmpty() &&
                			 priceL != null && !priceL.isEmpty()) {
                		
                		SizePrice sz = new SizePrice();
                		sz.setSizeS(new BigDecimal(priceS));
                		sz.setSizeM(new BigDecimal(priceM));
                		sz.setSizeL(new BigDecimal(priceL));
                		drink.setSizePrices(sz);
                	}
                }

            // -------------------------------------------------
            // IMAGE
            // -------------------------------------------------
;
            Part imagePart =
                    request.getPart("drinkIMG");
            
            

            String imageName =
                    saveImage(
                            request,
                            imagePart
                    );

            drink.setProductIMG(
                    imageName
            );


            // -------------------------------------------------
            // STATUS
            // -------------------------------------------------

            drink.setIsAvailable(
                    request.getParameter("drinkActive")
                    != null
            );


            // -------------------------------------------------
            // SAVE DATABASE
            // -------------------------------------------------

            dao.CreateProduct(drink);


            // -------------------------------------------------
            // REDIRECT
            // -------------------------------------------------

            response.sendRedirect(
                    request.getContextPath()
                    + "/DrinkServlet"
            );

            return;
        }


        // =====================================================
        // UPDATE
        // =====================================================

        if ("update".equals(action)) {

            String id =
                    request.getParameter("idDrink");

            if (id != null && !id.isEmpty()) {

                try {

                    Product drink =
                            dao.FindByID(
                                    Integer.valueOf(id)
                            );

                    if (drink != null) {

                        // -----------------------------------------
                        // TÊN
                        // -----------------------------------------

                        drink.setProductName(
                                request.getParameter(
                                        "drinkName"
                                )
                        );
                        
                     // -----------------------------------------
                        // CATEGORY
                        // -----------------------------------------

                        drink.setCategory(
                                request.getParameter(
                                        "category"
                                )
                        );



                        // -----------------------------------------
                        // GIÁ
                        // -----------------------------------------
                        
                        if(drink.getCategory().equals("Bánh")) {
                        String price =
                                request.getParameter(
                                        "price"
                                );

                        if (price != null
                                && !price.isEmpty()) {

                            drink.setPrice(
                                    new BigDecimal(price)
                            );
                        }}
                        else {
                        	
                        	String priceS = request.getParameter("priceS");
                        	String priceM = request.getParameter("priceM");
                        	String priceL = request.getParameter("priceL");
                        	
                        	if( priceS != null && !priceS.isEmpty() &&
                        			 priceM != null && !priceM.isEmpty() &&
                        			 priceL != null && !priceL.isEmpty()) {
                        		
                        		SizePrice sz = new SizePrice();
                        		
                        		sz.setProduct(drink);
                        		sz.setSizeS(new BigDecimal(priceS));
                        		sz.setSizeM(new BigDecimal(priceM));
                        		sz.setSizeL(new BigDecimal(priceL));
                        	
                        		drink.setSizePrices(sz);
                        		
                        		}
                        }


                        

                        // -----------------------------------------
                        // IMAGE
                        // -----------------------------------------

                        Part imagePart =
                                request.getPart(
                                        "drinkIMG"
                                );

                        /*
                         * Nếu user KHÔNG chọn ảnh mới:
                         *
                         * imagePart.getSize() == 0
                         *
                         * => Không thay đổi ProductIMG
                         */

                        if (imagePart != null
                                && imagePart.getSize() > 0) {

                            // Ảnh cũ
                            String oldImage =
                                    drink.getProductIMG();

                            // Lưu ảnh mới
                            String newImage =
                                    saveImage(
                                            request,
                                            imagePart
                                    );

                            // Nếu lưu thành công
                            if (newImage != null) {

                                drink.setProductIMG(
                                        newImage
                                );

                                // Xóa ảnh cũ
                                deleteImage(
                                        request,
                                        oldImage
                                );
                            }
                        }


                        // -----------------------------------------
                        // STATUS
                        // -----------------------------------------

                        drink.setIsAvailable(
                                request.getParameter(
                                        "drinkActive"
                                ) != null
                        );


                        // -----------------------------------------
                        // UPDATE DATABASE
                        // -----------------------------------------

                        dao.UpdateProduct(drink);
                    }

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }
            }


            // -----------------------------------------
            // QUAY VỀ LIST
            // -----------------------------------------

            response.sendRedirect(
                    request.getContextPath()
                    + "/DrinkServlet"
            );

            return;
        }


        // =====================================================
        // ACTION KHÔNG HỢP LỆ
        // =====================================================

        response.sendRedirect(
                request.getContextPath()
                + "/DrinkServlet"
        );
    }


    // =====================================================
    // SAVE IMAGE
    // =====================================================

    private String saveImage(
            HttpServletRequest request,
            Part imagePart)
            throws IOException {

        if (imagePart == null
                || imagePart.getSize() == 0) {

            return null;
        }


        // -----------------------------------------------------
        // LẤY TÊN FILE GỐC
        // -----------------------------------------------------

        String originalName =
                imagePart.getSubmittedFileName();

        if (originalName == null
                || originalName.isEmpty()) {

            return null;
        }


        // -----------------------------------------------------
        // LẤY EXTENSION
        // -----------------------------------------------------

        String extension = "";

        int dotIndex =
                originalName.lastIndexOf(".");

        if (dotIndex >= 0) {

            extension =
                    originalName.substring(
                            dotIndex
                    );
        }


        // -----------------------------------------------------
        // CHỈ CHO PHÉP ẢNH
        // -----------------------------------------------------

        String lowerExtension =
                extension.toLowerCase();

        if (!lowerExtension.equals(".jpg")
                && !lowerExtension.equals(".jpeg")
                && !lowerExtension.equals(".png")
                && !lowerExtension.equals(".gif")
                && !lowerExtension.equals(".webp")) {

            return null;
        }


        // -----------------------------------------------------
        // TẠO TÊN FILE MỚI
        // -----------------------------------------------------

        String newFileName =
                UUID.randomUUID()
                + extension.toLowerCase();


        // -----------------------------------------------------
        // LẤY ĐƯỜNG DẪN
        // -----------------------------------------------------

        String uploadPath =
                getServletContext()
                .getRealPath(
                        "/hinh-anh/imgs"
                );


        // -----------------------------------------------------
        // TẠO FOLDER NẾU CHƯA CÓ
        // -----------------------------------------------------

        File uploadDir =
                new File(uploadPath);

        if (!uploadDir.exists()) {

            uploadDir.mkdirs();
        }


        // -----------------------------------------------------
        // ĐƯỜNG DẪN FILE
        // -----------------------------------------------------

        File file =
                new File(
                        uploadDir,
                        newFileName
                );


        // -----------------------------------------------------
        // LƯU FILE
        // -----------------------------------------------------

        imagePart.write(
                file.getAbsolutePath()
        );


        System.out.println(
                "IMAGE SAVED: "
                + file.getAbsolutePath()
        );


        // Trả về tên file để lưu database
        return newFileName;
    }


    // =====================================================
    // DELETE IMAGE
    // =====================================================

    private void deleteImage(
            HttpServletRequest request,
            String imageName) {

        if (imageName == null
                || imageName.isEmpty()) {

            return;
        }


        String imagePath =
                getServletContext()
                .getRealPath(
                        "/images/drinks/"
                        + imageName
                );


        if (imagePath == null) {

            return;
        }


        File file =
                new File(imagePath);


        if (file.exists()) {

            if (file.delete()) {

                System.out.println(
                        "OLD IMAGE DELETED: "
                        + imageName
                );

            } else {

                System.out.println(
                        "CANNOT DELETE IMAGE: "
                        + imageName
                );
            }
        }
    }
}