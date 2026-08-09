document.addEventListener("DOMContentLoaded", () => {
    // 1. MOCK DATABASE (Giữ nguyên)
    const productDatabase = {
        "1": { name: "Phindi Hạnh Nhân", prices: { S: 49000, M: 55000, L: 59000 }, img: "hinh-anh/imgs/PHINDI_HANH_NHAN.jpg", badge: "HOT", desc: "Cà phê Phin thế hệ mới kết hợp Hạnh Nhân.", ingredient: "<tr><td>Cà phê</td><td>Phin Espresso</td></tr>" },
        "2": { name: "Trà Sen Vàng", prices: { S: 45000, M: 55000, L: 65000 }, img: "hinh-anh/imgs/tsv_cu_nang.jpg", badge: "BEST SELLER", desc: "Trà Oolong thanh mát, hạt sen thơm bùi.", ingredient: "<tr><td>Trà</td><td>Oolong</td></tr>" },
        "3": { name: "Freeze Trà Xanh", prices: { S: 55000, M: 65000, L: 75000 }, img: "hinh-anh/imgs/freeze_tra_xanh.jpg", badge: "HOT", desc: "Đá xay đậm vị trà xanh Tây Bắc.", ingredient: "<tr><td>Trà</td><td>Matcha</td></tr>" },
        "4": { name: "Cà Phê Sữa Đá", prices: { S: 29000, M: 35000, L: 39000 }, img: "hinh-anh/imgs/PHIN_SUA_DA.jpg", badge: "CLASSIC", desc: "Đậm đà hương vị cà phê Phin Việt Nam.", ingredient: "<tr><td>Cà phê</td><td>Robusta</td></tr>" },
        "5": { name: "Phin Sữa Đá", prices: { S: 29000, M: 35000, L: 39000 }, img: "hinh-anh/imgs/phin_sua_da.jpg", badge: "BEST SELLER", desc: "Sự kết hợp hoàn hảo giữa cà phê Phin và sữa đặc.", ingredient: "<tr><td>Cà phê</td><td>Robusta</td></tr>" },
        "6": { name: "Phin Đen Đá", prices: { S: 29000, M: 35000, L: 39000 }, img: "hinh-anh/imgs/phin_den_da.jpg", badge: "PURE", desc: "Cà phê Phin đậm đà, nguyên bản không đường.", ingredient: "<tr><td>Cà phê</td><td>Robusta nguyên chất</td></tr>" },
        "7": { name: "Bạc Xỉu Đá", prices: { S: 33000, M: 39000, L: 45000 }, img: "hinh-anh/imgs/bac_siu_1.jpg", badge: "HOT", desc: "Bạc xỉu chính gốc Sài Gòn.", ingredient: "<tr><td>Sữa</td><td>Sữa đặc & tươi</td></tr>" },
        "8": { name: "Phin Sữa Nóng", prices: { S: 29000, M: 35000, L: 39000 }, img: "hinh-anh/imgs/PHIN_SUA_NONG.jpg", badge: "CLASSIC", desc: "Cà phê Phin phục vụ nóng hổi.", ingredient: "<tr><td>Cà phê</td><td>Robusta</td></tr>" },
        "9": { name: "Bánh Phô Mai Trà Xanh", price: 25000, img: "hinh-anh/imgs/banh_pho_mai_tra_xanh.jpg", badge: "NEW", desc: "Bánh phô mai mềm mịn tan trong miệng.", ingredient: "<tr><td>Thành phần</td><td>Cream cheese, Matcha</td></tr>" },
        "10": { name: "Bánh Mì Que Gà", price: 19000, img: "hinh-anh/imgs/bmq_ga.jpg", badge: "BEST SELLER", desc: "Bánh mì que giòn rụm với nhân thịt gà xé.", ingredient: "<tr><td>Thành phần</td><td>Thịt gà xé, Phô mai</td></tr>" },
        "11": { name: "Bánh Tiramisu", price: 35000, img: "hinh-anh/imgs/BANH_TIRAMISU.jpg", badge: "NEW", desc: "Bánh Tiramisu chuẩn vị Ý.", ingredient: "<tr><td>Thành phần</td><td>Mascarpone, Espresso</td></tr>" },
        "12": { name: "Bánh Chuối", price: 29000, img: "hinh-anh/imgs/BANH_CHUOI.jpg", badge: "NEW", desc: "Bánh chuối nướng truyền thống.", ingredient: "<tr><td>Thành phần</td><td>Chuối tươi</td></tr>" },
        "13": { name: "Trà Thanh Đào", prices: { S: 45000, M: 55000, L: 65000 }, img: "hinh-anh/imgs/tra_thanh_dao.jpg", badge: "NEW", desc: "Trà đào chua ngọt thanh mát.", ingredient: "<tr><td>Trà</td><td>Trà đen</td></tr>" },
        "14": { name: "Trà Thạch Vải", prices: { S: 45000, M: 55000, L: 65000 }, img: "hinh-anh/imgs/TRA_THACH_VAI_1.jpg", badge: "NEW", desc: "Trà đen nguyên lá kết hợp thạch vải.", ingredient: "<tr><td>Trà</td><td>Trà đen</td></tr>" },
        "15": { name: "Cookies & Cream", prices: { S: 55000, M: 65000, L: 75000 }, img: "hinh-anh/imgs/FREEZE_COOKIES.jpg", badge: "HOT", desc: "Thức uống đá xay kết hợp bánh oreo.", ingredient: "<tr><td>Thành phần</td><td>Oreo, Kem Whipping</td></tr>" },
        "16": { name: "Trà Xanh Đậu Đỏ", prices: { S: 45000, M: 55000, L: 65000 }, img: "hinh-anh/imgs/TRA_XANH_DAU_DO.jpg", badge: "NEW", desc: "Sự kết hợp hoàn hảo giữa trà xanh thanh mát và lớp topping đậu đỏ ngọt bùi.", ingredient: "<tr><td>Thành phần</td><td>Trà xanh, Đậu đỏ</td></tr>" }
    };

    // 2. KHỞI TẠO GIỎ HÀNG TỪ LOCALSTORAGE
    let cart = JSON.parse(localStorage.getItem('shoppingCart')) || [];

    // 3. THUẬT TOÁN: BẮT SẢN PHẨM MỚI TỪ URL VÀ ĐẨY VÀO MẢNG
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    if (productId && productDatabase[productId]) {
        const selectedSize = urlParams.get('size') || 'S';
        const quantity = parseInt(urlParams.get('sl')) || 1;
        const product = productDatabase[productId];

        let finalPrice = product.prices ? product.prices[selectedSize] : product.price;
        let displayName = product.prices ? `${product.name} (Size ${selectedSize})` : product.name;

        const existingItemIndex = cart.findIndex(item => item.id === productId && item.size === selectedSize);
        
        if (existingItemIndex !== -1) {
            cart[existingItemIndex].quantity += quantity;
        } else {
            cart.push({
                id: productId,
                size: selectedSize,
                name: displayName,
                price: finalPrice,
                img: product.img,
                quantity: quantity
            });
        }

        localStorage.setItem('shoppingCart', JSON.stringify(cart));
        window.history.replaceState({}, document.title, window.location.pathname);
    }

    // 4. KẾT NỐI MỎ NEO HTML
    const cartItemsContainer = document.getElementById("cart-items");
    const cartTotalElement = document.getElementById("cart-total");

    // 5. HÀM HIỂN THỊ (RENDER) DANH SÁCH SẢN PHẨM TỪ MẢNG
    function renderCart() {
        cartItemsContainer.innerHTML = ""; 
        let grandTotal = 0;

        if (cart.length === 0) {
            cartItemsContainer.innerHTML = `
                <tr>
                    <td colspan="5" style="text-align: center; padding: 40px; color: #777;">
                        <i class="fas fa-shopping-cart" style="font-size: 30px; margin-bottom: 10px; color: #ccc;"></i><br>
                        Giỏ hàng của bạn đang trống.<br>
                        <a href="thuc-don.jsp" style="color: #b22830; text-decoration: none; font-weight: bold; margin-top: 15px; display: inline-block; border: 1px solid #b22830; padding: 8px 20px; border-radius: 20px;">Khám phá thực đơn</a>
                    </td>
                </tr>`;
            cartTotalElement.innerText = "0đ";
            return; 
        }

        cart.forEach((item, index) => {
            const itemTotal = item.price * item.quantity;
            grandTotal += itemTotal; 

            cartItemsContainer.innerHTML += `
                <tr>
                    <td>
                        <div class="cart-item-info" style="display: flex; align-items: center; gap: 15px;">
                            <img src="${item.img}" alt="${item.name}" style="width: 70px; height: 70px; object-fit: cover; border-radius: 6px; border: 1px solid #eee;">
                            <span style="font-weight: 500;">${item.name}</span>
                        </div>
                    </td>
                    <td style="color: #555;">${item.price.toLocaleString('vi-VN')}đ</td>
                    <td>
                        <!-- ĐÃ SỬA: Bỏ readonly, thêm class input-qty, thêm data-index và min="1" -->
                        <input type="number" class="input-qty" data-index="${index}" value="${item.quantity}" min="1" style="width: 50px; text-align: center; border: 1px solid #ddd; padding: 5px; border-radius: 4px;">
                    </td>
                    <td><output style="font-weight: bold; color: #b22830;">${itemTotal.toLocaleString('vi-VN')}đ</output></td>
                    <td>
                        <button type="button" class="btn-remove" data-index="${index}" style="background: #b22830; color: white; border: none; width: 30px; height: 30px; cursor: pointer; border-radius: 50%; font-weight: bold;">X</button>
                    </td>
                </tr>
            `;
        });

        cartTotalElement.innerText = grandTotal.toLocaleString('vi-VN') + "đ";
    }

    // 6. XỬ LÝ SỰ KIỆN NÚT "XÓA" (EVENT DELEGATION)
    cartItemsContainer.addEventListener("click", function(e) {
        if (e.target.classList.contains("btn-remove")) {
            const itemIndex = e.target.getAttribute("data-index");
            cart.splice(itemIndex, 1); 
            localStorage.setItem('shoppingCart', JSON.stringify(cart));
            renderCart(); 
        }
    });

    // 7. XỬ LÝ SỰ KIỆN THAY ĐỔI SỐ LƯỢNG (MỚI THÊM VÀO)
    cartItemsContainer.addEventListener("change", function(e) {
        if (e.target.classList.contains("input-qty")) {
            const itemIndex = e.target.getAttribute("data-index");
            let newQuantity = parseInt(e.target.value);
            
            // Xử lý nếu nhập chữ, khoảng trắng hoặc số nhỏ hơn 1
            if (isNaN(newQuantity) || newQuantity < 1) {
                newQuantity = 1;
            }
            
            // Cập nhật mảng, lưu xuống localStorage và render lại giao diện
            cart[itemIndex].quantity = newQuantity; 
            localStorage.setItem('shoppingCart', JSON.stringify(cart));
            renderCart(); 
        }
    });

    renderCart();
});

// ==========================================
// 8. XỬ LÝ SỰ KIỆN SUBMIT ĐƠN HÀNG VÀ RENDER BILL BÊN TRONG POPUP
// ==========================================
window.handleOrderSubmit = function(event) {
    event.preventDefault(); // Chặn hành vi load lại trang mặc định của Form

    // Lấy giỏ hàng từ bộ nhớ
    let cart = JSON.parse(localStorage.getItem('shoppingCart')) || [];
    
    // Validate: Nếu giỏ hàng trống thì không cho đặt
    if (cart.length === 0) {
        alert("Giỏ hàng của bạn đang trống! Vui lòng chọn món trước khi đặt hàng.");
        return; 
    }

    const billContent = document.getElementById("modal-bill-content");
    const billTotal = document.getElementById("modal-bill-total");
    let grandTotal = 0;
    
    // Dọn dẹp nội dung cũ (nếu có)
    billContent.innerHTML = "";

    // Duyệt mảng cart để tạo từng dòng hóa đơn
    cart.forEach(item => {
        const itemTotal = item.price * item.quantity;
        grandTotal += itemTotal;
        
        // Bơm HTML vào mỏ neo bên trái của Popup
        billContent.innerHTML += `
            <div style="display: flex; justify-content: space-between; margin-bottom: 12px; border-bottom: 1px dashed #eee; padding-bottom: 8px;">
                <div style="flex: 2; text-align: left;">
                    <div style="font-weight: 500; color: #333; font-size: 15px;">${item.name}</div>
                    <div style="font-size: 13px; color: #888;">SL: ${item.quantity} x ${item.price.toLocaleString('vi-VN')}đ</div>
                </div>
                <div style="flex: 1; text-align: right; font-weight: bold; color: #555;">
                    ${itemTotal.toLocaleString('vi-VN')}đ
                </div>
            </div>
        `;
    });

    // Cập nhật tổng tiền vào Bill
    billTotal.innerText = grandTotal.toLocaleString('vi-VN') + "đ";

    // Kích hoạt check input để hiển thị Modal
    document.getElementById('show-success-modal').checked = true;
};

// ==========================================
// 9. XÓA GIỎ HÀNG SAU KHI ĐẶT THÀNH CÔNG VÀ CHUYỂN HƯỚNG
// ==========================================
window.clearCart = function() {
    // Xóa key 'shoppingCart' khỏi LocalStorage
    localStorage.removeItem('shoppingCart');
    // Chuyển hướng người dùng về trang chủ
    window.location.href = 'trang-chu.html';
};
