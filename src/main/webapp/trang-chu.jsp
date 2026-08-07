<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/NewFile.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ - Highlands Coffee</title>
    <link rel="stylesheet" href="User/dinh-dang.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <!-- HEADER HIGHLANDS -->
    <header class="main-header">
        <div class="header-container">
            <div class="logo">Highlands Coffee</div>
            <!-- Dọn trống Nav, nhường quyền kiểm soát cho JavaScript -->
            <nav id="main-nav">
            <a href="trang-chu.jsp" class="active">Trang chủ</a>
            	<c:choose>
            		<c:when test="${sessionScope.user.role == 'ADMIN' }">
            			<a href="quan-ly-thuc-don.jsp">QL Thực đơn</a>
	                    <a href="quan-ly-kho.jsp">QL Kho hàng</a>
	                    <a href="quan-ly-nhan-vien.jsp">QL Nhân viên</a>
	                    <a href="bao-cao-doanh-thu.jsp">Báo cáo</a>
            		</c:when>
            		<c:when test="${sessionScope.user.role == 'BARISTA' }">
            			<a href="nhan-don-hang.jsp">Nhận Đơn Hàng</a>
                    	<a href="cap-nhat-trang-thai.jsp">Cập Nhật Trạng Thái</a>
            		</c:when>
            		<c:when test="${sessionScope.user.role == 'USER' }">
            			<a href="${ctx}/Menu">Thực đơn</a>
                    	<a href="gio-hang.jsp">Giỏ hàng</a>
            		</c:when>
            		<c:otherwise>
			            <a href="${ctx}/Menu">Thực đơn</a>
			            <a href="gio-hang.jsp">Giỏ hàng</a>
			            <span style="color: #fff; margin: 0 15px;">|</span>
			            <a href="dang-ky.jsp" style="color: var(--primary-color); font-weight: bold;">Đăng ký</a>
			            <a href="${ctx }/User/dang-nhap.jsp">Đăng nhập</a>
            		</c:otherwise>
            	</c:choose>
            </nav>
        </div>
    </header>

    <!-- BANNER SLIDER -->
    <section class="banner-slider">
        <div class="slides">
            <div class="slide"><img src="hinh-anh/imgs/trangchu1.jpg" alt="Banner Highlands 1"></div>
            <div class="slide"><img src="hinh-anh/imgs/trangchu2.jpg" alt="Banner Highlands 2"></div>
            <div class="slide"><img src="hinh-anh/imgs/trangchu3.jpg" alt="Banner Highlands 3"></div>
            <div class="slide"><img src="hinh-anh/imgs/trangchu4.jpg" alt="Banner Highlands 4"></div>
            <div class="slide"><img src="hinh-anh/imgs/trangchu5.png" alt="Banner Highlands 5"></div>
            <div class="slide"><img src="hinh-anh/imgs/trangchu6.jpg" alt="Banner Highlands 6"></div>
        </div>
        <div class="slider-dots">
            <div class="dot active"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </div>
    </section>

    <!-- DANH SÁCH SẢN PHẨM -->
    <section class="section">
        <h2 class="page-title">Món Mới Phải Thử</h2>
        <div class="products">
            <div class="item">
                <!-- Đã thêm ?id=1 -->
                <a href="chi-tiet-san-pham.jsp?id=1" class="product-link">
                    <img src="hinh-anh/imgs/PHINDI_HANH_NHAN.jpg" alt="Phindi Hạnh Nhân">
                    <p>Phindi Hạnh Nhân</p>
                    <span class="old">55.000đ</span>
                    <span class="new">49.000đ</span>
                </a>
            </div>
            <!-- Đã thêm ?id=2 -->
	        <div class="item">
	            <a href="chi-tiet-san-pham.jsp?id=2" class="product-link">
		            <img src="hinh-anh/imgs/TSV_CU_NANG.jpg" alt="Trà Sen Vàng">
		            <p>Trà Sen Vàng</p>
		            <span class="old">55.000đ</span>
		            <span class="new">45.000đ</span>
	            </a>
            </div>
            <!-- Đã thêm ?id=3 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=3" class="product-link"><img src="hinh-anh/imgs/FREEZE_TRA_XANH.jpg" alt="Freeze Trà Xanh"><p>Freeze Trà Xanh</p><span class="old">65.000đ</span><span class="new">55.000đ</span></a></div>
            <!-- Đã thêm ?id=4 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=4" class="product-link"><img src="hinh-anh/imgs/PHIN_SUA_DA.jpg" alt="Cà Phê Sữa Đá"><p>Cà Phê Sữa Đá (Size L)</p><span class="old">39.000đ</span><span class="new">35.000đ</span></a></div>
        </div>
    </section>

    <section class="section">
        <h2 class="page-title">Cà Phê Phin Điển Hình</h2>
        <div class="products">
            <!-- Đã thêm ?id=5 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=5" class="product-link"><img src="hinh-anh/imgs/PHIN_SUA_DA.jpg" alt="Phin Sữa Đá"><p>Phin Sữa Đá</p><span class="old">35.000đ</span><span class="new">29.000đ</span></a></div>
            <!-- Đã thêm ?id=6 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=6" class="product-link"><img src="hinh-anh/imgs/PHIN_DEN_DA.jpg" alt="Phin Đen Đá"><p>Phin Đen Đá</p><span class="old">35.000đ</span><span class="new">29.000đ</span></a></div>
            <!-- Đã thêm ?id=7 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=7" class="product-link"><img src="hinh-anh/imgs/BAC_SIU_1.jpg" alt="Bạc Xỉu Đá"><p>Bạc Xỉu Đá</p><span class="old">39.000đ</span><span class="new">33.000đ</span></a></div>
            <!-- Đã thêm ?id=8 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=8" class="product-link"><img src="hinh-anh/imgs/PHIN_SUA_NONG.jpg" alt="Phin Sữa Nóng"><p>Phin Sữa Nóng</p><span class="old">35.000đ</span><span class="new">29.000đ</span></a></div>
        </div>
    </section>

    <section class="section">
        <h2 class="page-title">Bánh Mì & Đồ Ăn Nhẹ</h2>
        <div class="products">
            <!-- Đã thêm ?id=9 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=9" class="product-link"><img src="hinh-anh/imgs/BANH_PHO_MAI_TRA_XANH.jpg" alt="Bánh Phô Mai Trà Xanh"><p>Bánh Phô Mai Trà Xanh</p><span class="old">28.000đ</span><span class="new">25.000đ</span></a></div>
            <!-- Đã thêm ?id=10 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=10" class="product-link"><img src="hinh-anh/imgs/BMQ_GA.jpg" alt="Bánh Mì Que (Gà Phô Mai)"><p>Bánh Mì Que (Gà Phô Mai)</p><span class="old">22.000đ</span><span class="new">19.000đ</span></a></div>
            <!-- Đã thêm ?id=11 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=11" class="product-link"><img src="hinh-anh/imgs/BANH_TIRAMISU.jpg" alt="Bánh Tiramisu"><p>Bánh Tiramisu</p><span class="old">39.000đ</span><span class="new">35.000đ</span></a></div>
            <!-- Đã thêm ?id=12 -->
            <div class="item"><a href="chi-tiet-san-pham.jsp?id=12" class="product-link"><img src="hinh-anh/imgs/BANH_CHUOI.jpg" alt="Bánh Chuối"><p>Bánh Chuối</p><span class="old">32.000đ</span><span class="new">29.000đ</span></a></div>
        </div>
    </section>

  <!-- FOOTER HIGHLANDS -->
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

    <!-- Chỉ cần gọi duy nhất file main.js là hệ thống sẽ tự động xử lý Menu và Slider -->
    <script src="main.js"></script>
</body>
</html>