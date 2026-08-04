<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Thực đơn - Admin Highlands</title>
    <link rel="stylesheet" href="dinh-dang.css">
</head>
<body>
    <!-- HEADER CHUẨN (Nhường JS phân quyền) -->
    <header class="main-header">
        <div class="header-container">
            <div class="logo">Highlands Admin</div>
            <nav id="main-nav"></nav>
        </div>
    </header>

    <section class="section admin-section">
        <h2 class="page-title">Quản Lý Danh Sách Sản Phẩm</h2>
        
        <!-- BẢNG ĐIỀU KHIỂN & TÌM KIẾM -->
        <div class="admin-controls" style="display: flex; justify-content: space-between; margin-bottom: 20px;">
            <button id="btn-add-product" class="btn-checkout" style="width: auto; background-color: var(--dark-bg);">+ Thêm Món Mới</button>
            <input type="text" id="search-product" placeholder="Tìm kiếm tên món..." style="padding: 10px; width: 300px; border-radius: 5px; border: 1px solid #ccc;">
        </div>

        <!-- BẢNG HIỂN THỊ DỮ LIỆU SẢN PHẨM -->
        <table class="cart-table" id="admin-product-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá bán</th>
                    <th>Danh mục</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody id="product-list-body">
                <!-- Chỗ này JS sẽ tự động render dữ liệu sản phẩm ra -->
                <tr>
                    <td colspan="6" style="text-align: center; padding: 20px;">Đang tải dữ liệu...</td>
                </tr>
            </tbody>
        </table>
    </section>

    <!-- POPUP FORM THÊM/SỬA SẢN PHẨM (Đã được đưa vào trong thẻ body) -->
    <div id="product-modal" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 1000; align-items: center; justify-content: center;">
        <div class="form-box" style="background: white; padding: 20px; border-radius: 8px; width: 400px; position: relative;">
            <span id="close-modal" style="position: absolute; right: 15px; top: 10px; font-size: 20px; cursor: pointer; font-weight: bold;">&times;</span>
            <h3 id="modal-title" style="color: var(--dark-bg); margin-bottom: 15px; text-align: center;">Thêm Món Mới</h3>
            
            <form id="product-form">
                <!-- Dùng hidden input để chứa ID khi cần Sửa món -->
                <input type="hidden" id="product-id">
                
                <label style="display: block; margin-bottom: 5px; font-weight: bold;">Tên sản phẩm:</label>
                <input type="text" id="product-name" class="form-input" required style="width: 100%; margin-bottom: 15px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
                
                <label style="display: block; margin-bottom: 5px; font-weight: bold;">Giá bán (VNĐ):</label>
                <input type="number" id="product-price" class="form-input" required min="0" style="width: 100%; margin-bottom: 15px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
                
                <label style="display: block; margin-bottom: 5px; font-weight: bold;">Đường dẫn hình ảnh:</label>
                <input type="text" id="product-img" class="form-input" placeholder="VD: hinh-anh/imgs/cf.jpg" required style="width: 100%; margin-bottom: 15px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
                
                <label style="display: block; margin-bottom: 5px; font-weight: bold;">Danh mục:</label>
                <select id="product-category" class="form-input" style="width: 100%; margin-bottom: 20px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
                    <option value="Cà phê">Cà phê</option>
                    <option value="Trà">Trà</option>
                    <option value="Freeze">Freeze</option>
                </select>
                
                <button type="submit" class="btn-checkout" style="width: 100%; background-color: var(--dark-bg); border: none; color: white; padding: 10px; border-radius: 4px; cursor: pointer;">Lưu Sản Phẩm</button>
            </form>
        </div>
    </div>

    <!-- Gọi file JS duy nhất -->
    <script src="main.js"></script>
</body>
</html>