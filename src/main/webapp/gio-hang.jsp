<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng - Highlands Coffee</title>
    <link rel="stylesheet" href="dinh-dang.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <!-- HEADER CHUẨN ĐỒNG BỘ -->
    <%@ include file="Header.jsp" %>>

    <section class="section">
        <h2 class="page-title">Giỏ Hàng Của Bạn</h2>
        
        <!-- ĐIỂM THAY ĐỔI 1: Gọi hàm handleOrderSubmit() thay vì xử lý tĩnh -->
        <form class="cart-container" style="margin-top: 30px;" onsubmit="handleOrderSubmit(event)">
            <div class="cart-items">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Sản phẩm</th>
                            <th>Đơn giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th>Xóa</th>
                        </tr>
                    </thead>
                    <tbody id="cart-items">
                        <!-- Dữ liệu sẽ tự động sinh ra từ file gio-hang.js -->
                    </tbody>
                </table>
            </div>

            <div class="cart-summary">
                <h3>Thông tin giao hàng</h3>
                <div class="checkout-form">
                    <input type="text" id="customer-name" placeholder="Họ và tên người nhận" autocomplete="name" required>
                    <input type="tel" id="customer-phone" placeholder="Số điện thoại" autocomplete="tel" required>
                    <input type="text" id="customer-address" placeholder="Địa chỉ giao hàng chi tiết" autocomplete="street-address" required>
                    <textarea id="customer-note" placeholder="Ghi chú đơn hàng" rows="3"></textarea>
                    
                    <div class="total-price">
                        <strong>Tổng cộng:</strong>
                        <output name="tong" class="new" id="cart-total">0đ</output>
                    </div>
                    
                    <button type="submit" class="btn-checkout" style="background-color: var(--dark-bg);">Tiến hành đặt hàng</button>
                </div>
            </div>
        </form>
    </section>

    <!-- ĐIỂM THAY ĐỔI 2: POPUP ĐẶT HÀNG THÀNH CÔNG (LAYOUT 2 CỘT SONG SONG) -->
    <input type="checkbox" id="show-success-modal" class="modal-trigger">
    <div class="success-modal">
        <!-- Đã mở rộng width lên 750px và ép hiển thị Flexbox nằm ngang, bỏ flex-wrap -->
        <div class="form-box" style="width: 750px; max-width: 95vw; display: flex; align-items: stretch; gap: 20px; padding: 30px; position: relative; background: #fff; border-radius: 8px; margin: 0 auto;">
            <label for="show-success-modal" class="close" style="cursor: pointer; position: absolute; right: 15px; top: 10px; font-size: 24px; color: #333;">×</label>
            
            <!-- CỘT TRÁI: HÓA ĐƠN ĐỘNG (BILL) - Chiếm cứng 50% -->
            <div style="width: 50%; border-right: 2px dashed #ddd; padding-right: 20px; display: flex; flex-direction: column;">
                <h3 style="color: var(--dark-bg); text-align: center; margin-bottom: 15px; text-transform: uppercase; border-bottom: 2px solid var(--dark-bg); padding-bottom: 10px;">Hóa Đơn Mua Hàng</h3>
                
                <div id="modal-bill-content" style="flex: 1; max-height: 300px; overflow-y: auto; padding-right: 5px;">
                    <!-- Dữ liệu bill sẽ hiển thị ở đây -->
                </div>
                
                <div style="margin-top: 15px; padding-top: 15px; border-top: 2px solid #333; display: flex; justify-content: space-between; font-size: 18px;">
                    <strong>Tổng thanh toán:</strong>
                    <strong id="modal-bill-total" style="color: #b22830;">0đ</strong>
                </div>
            </div>

            <!-- CỘT PHẢI: THÔNG BÁO & THANH TOÁN - Chiếm cứng 50% -->
            <div style="width: 50%; text-align: center; display: flex; flex-direction: column; justify-content: center; align-items: center; padding-left: 10px;">
                <h2 style="color: #28a745; margin-bottom: 10px; font-size: 22px;"><i class="fas fa-check-circle"></i> Đặt Thành Công!</h2>
                <p style="color: #666; font-size: 14px; margin-bottom: 15px;">Vui lòng quét mã QR để thanh toán. Nhân viên sẽ liên hệ xác nhận trong 15 phút.</p>
                
                <div style="padding: 10px; border: 1px solid #ddd; border-radius: 10px; background: #f9f9f9;">
                    <img src="hinh-anh/imgs/1784860941626_2143688342375985594_g665967586608572216_7d5734403ef9dd35920f3448d248f933.jpg" alt="Zalo QR Code" style="width: 170px; height: 170px; object-fit: cover; border-radius: 8px;">
                </div>
                
                <h3 style="color: #333; margin-top: 15px; font-size: 16px;">
                    Mai Thành Tài<br>
                    <span style="color: var(--dark-bg); font-size: 18px; display: inline-block; margin-top: 5px;">0344591828</span>
                </h3>
                
                <!-- Nút đóng gọi hàm clearCart() -->
                <label for="show-success-modal" style="margin-top: 15px; display: block; width: 100%; padding: 12px; background: var(--dark-bg); color: white; border-radius: 5px; cursor: pointer; font-weight: bold; text-align: center; transition: 0.3s;" onclick="clearCart()">Hoàn Tất & Về Trang Chủ</label>
            </div>
        </div>
    </div>

    <!-- FOOTER CHUẨN ĐỒNG BỘ -->
    <footer class="main-footer">
        <div class="footer-container">
            <div class="footer-column">
                <h4>Về Highlands Coffee</h4>
                <p>Khởi nguồn từ quán cà phê đóng gói tại Hà Nội vào năm 2000, Highlands Coffee đã không ngừng phát triển và trở thành thương hiệu cà phê nổi tiếng nhất Việt Nam.</p>
            </div>
            <div class="footer-column">
                <h4>Thông Tin Liên Hệ</h4>
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

    <!-- GỌI CÁC FILE SCRIPT -->
    <script src="main.js"></script>
    <script src="gio-hang.js"></script>
</body>
</html>