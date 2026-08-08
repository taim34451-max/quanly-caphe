<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="NewFile.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm - Highlands Coffee</title>
    <link rel="stylesheet" href="${ctx}/dinh-dang.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <!-- HEADER CHUẨN ĐỒNG BỘ -->
<%@ include file="Header.jsp" %>

    <section class="section">
        <div class="product-detail-container">
            <!-- PHẦN HÌNH ẢNH & TABS -->
            <div class="detail-image">
                <img id="detail-img" src="${ctx}/hinh-anh/imgs/${item.productId}.jsp" alt="Hình sản phẩm">
                
                <div class="product-tabs" style="margin-top: 20px;">
                    <input type="radio" name="det-tabs" id="det1" checked>
                    <label for="det1">Mô tả sản phẩm</label>
                    
                    <input type="radio" name="det-tabs" id="det2">
                    <label for="det2">Thành phần</label>

                    <div class="tab-content det1">
                        <p id="detail-tab-desc">${item.drinkDescription}</p>
                    </div>
                    <div class="tab-content det2">
                        <table class="size-table" style="margin-top: 0;" id="detail-tab-ingredients">
                            <tr><td>Đang tải...</td><td>...</td></tr>
                        </table>
                    </div>
                </div>
            </div>
            
            <!-- PHẦN THÔNG TIN & ĐẶT HÀNG -->
            <div class="detail-info">
                <h1 id="detail-title">${item.productName}</h1>
                
                <div class="price-box">
                    <span class="new" id="detail-price">${item.price}</span>
                    <span class="badge-hot" id="detail-badge" style="position: static; display: inline-block; margin-left: 10px;">HOT</span>
                </div>
                
                <p class="description" id="detail-desc">${item.drinkDescription}</p>
              
                <!-- FORM ĐẶT HÀNG CÓ CHỨA LOGIC CHỌN SIZE -->
                <form action="gio-hang.jsp" method="GET" id="order-form" style="margin-bottom: 20px;">
                    <input type="hidden" name="id" id="detail-id-input" value="">
                    
                    <!-- Khối chọn Size (Chỉ hiện cho nước) -->
                    <div id="size-options" style="display: none; margin-bottom: 15px;">
                        <label style="font-weight: bold; display: block; margin-bottom: 8px;">Chọn Size:</label>
                        <div style="display: flex; gap: 20px;">
                            <label><input type="radio" name="size" value="S" checked> Size S</label>
                            <label><input type="radio" name="size" value="M"> Size M</label>
                            <label><input type="radio" name="size" value="L"> Size L</label>
                        </div>
                    </div>

                    <div class="quantity-box" style="display: flex; align-items: center; gap: 10px; margin-bottom: 20px;">
                        <label for="soluong">Số lượng:</label>
                        <input type="number" name="sl" id="soluong" value="1" min="1">
                        <output name="ketqua" id="total-price" class="price-red">0đ</output>
                    </div>

                    <div class="action-buttons">
                        <button type="submit" class="btn-buy-now">Thêm vào giỏ / Mua ngay</button>
                    </div>
                </form> 
                
                <ul class="policy-list">
                    <li>✔️ Giao hàng hỏa tốc nội thành trong 30 phút</li>
                    <li>✔️ Đóng gói cẩn thận, ly cứng cáp chống tràn</li>
                    <li>✔️ Luôn kèm đá viên riêng biệt (áp dụng giao đi)</li>
                </ul>
            </div>
        </div>
    </section>

    <!-- SECTION HƯỚNG DẪN CHỌN SIZE -->
    <section class="section" style="background: #f9f9f9;">
        <h2 class="page-title" style="margin-bottom: 20px;">Bảng Hướng Dẫn Chọn Size</h2>
        <div class="info-container" style="max-width: 800px; margin: 0 auto; text-align: left;">
            <div class="table-section">
                <table class="size-table">
                    <tr><th>Size</th><th>Dung tích (ml)</th><th>Gợi ý sử dụng</th></tr>
                    <tr><td>S (Nhỏ)</td><td>295 ml</td><td>Thưởng thức nhanh gọn, vừa vặn</td></tr>
                    <tr><td>M (Vừa)</td><td>355 ml</td><td>Nạp năng lượng tiêu chuẩn mỗi sáng</td></tr>
                    <tr><td>L (Lớn)</td><td>473 ml</td><td>Cần sự tỉnh táo cho ngày dài làm việc</td></tr>
                </table>
            </div>
        </div>
    </section>

    <!-- FOOTER CHUẨN ĐỒNG BỘ -->
    <footer class="main-footer">
        <div class="footer-container">
            <div class="footer-column">
                <h4>Về Highlands Coffee</h4>
                <p>Khởi nguồn từ quán cà phê đóng gói tại Hà Nội vào năm 2000, Highlands Coffee đã không ngừng phát triển và trở thành thương hiệu cà phê nổi tiếng nhất Việt Nam.</p>
            </div>
            <div class="footer-column">
                <h4>Thông Liên Hệ</h4>
                <p>Hotline: 1900 1755</p>
                <p>Email: customerservice@highlandscoffee.com.vn</p>
                <p>Địa chỉ: 130-132 Hồng Hà, Phường 9, Quận Phú Nhuận, TP.HCM</p>
            </div>
            <div class="footer-column map-column">
                <h4>Bản đồ Cửa hàng</h4>
                <iframe src="https://maps.google.com/maps?q=Highlands%20Coffee%20Ho%20Chi%20Minh&t=&z=13&ie=UTF8&iwloc=&output=embed" title="Bản đồ Highlands Coffee" width="100%" height="100" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
            </div>
            <div class="footer-column copyright-column">
                <p class="copy-text">© 2026 Highlands Coffee.<br>Tất cả các quyền được bảo lưu.</p>
            </div>
        </div>
    </footer>

    <!-- Gọi file JavaScript điều khiển logic động cho trang Chi tiết -->
    <script src="chi-tiet.js"></script>
</body>
</html>