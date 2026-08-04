document.addEventListener("DOMContentLoaded", () => {
    // 1. Lấy tham số ?id=... từ URL
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

  // 2. Mock Data 16 món
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

    const product = productDatabase[productId] || productDatabase["5"];
    
    // 3. Logic xác định Giá cơ bản (Dựa trên loại món)
    let currentBasePrice = 0;
    const sizeOptionsDiv = document.getElementById("size-options");
    
    if (product.prices) {
        // Nếu là Nước (Có object prices) -> Hiện bảng chọn Size, lấy giá Size S làm gốc
        sizeOptionsDiv.style.display = "block";
        currentBasePrice = product.prices["S"];
    } else {
        // Nếu là Bánh (Chỉ có price) -> Giấu bảng chọn Size đi, lấy giá cố định
        sizeOptionsDiv.style.display = "none";
        currentBasePrice = product.price;
    }

    // 4. Cập nhật Giao diện hiển thị
    document.getElementById("detail-title").innerText = product.name;
    document.getElementById("detail-price").innerText = currentBasePrice.toLocaleString('vi-VN') + "đ";
    document.getElementById("total-price").innerText = currentBasePrice.toLocaleString('vi-VN') + "đ";
    document.getElementById("detail-img").src = product.img;
    document.getElementById("detail-desc").innerText = product.desc;
    document.getElementById("detail-tab-desc").innerText = product.desc;
    document.getElementById("detail-tab-ingredients").innerHTML = product.ingredient;
    document.getElementById("detail-badge").innerText = product.badge;
    document.getElementById("detail-id-input").value = productId;

    // 5. Hàm tính toán và cập nhật Tổng tiền liên tục
    const quantityInput = document.getElementById("soluong");
    const priceDisplay = document.getElementById("detail-price");
    const totalDisplay = document.getElementById("total-price");
    const sizeRadios = document.querySelectorAll('input[name="size"]');

    function updatePrice() {
        const qty = parseInt(quantityInput.value) || 1;
        const total = qty * currentBasePrice;
        
        // Cập nhật lên màn hình
        priceDisplay.innerText = currentBasePrice.toLocaleString('vi-VN') + "đ"; // Giá của 1 ly hiện tại
        totalDisplay.innerText = total.toLocaleString('vi-VN') + "đ";           // Tổng tiền dựa trên số lượng
    }

    // Lắng nghe sự kiện khi thay đổi Số lượng
    quantityInput.addEventListener("input", updatePrice);

    // Lắng nghe sự kiện khi click đổi Size (Chỉ chạy khi món có Size)
    sizeRadios.forEach(radio => {
        radio.addEventListener("change", (e) => {
            if (product.prices) {
                const selectedSize = e.target.value; // Trả về 'S', 'M', hoặc 'L'
                currentBasePrice = product.prices[selectedSize]; // Lấy giá mới từ Database
                updatePrice(); // Gọi lại hàm tính toán
            }
        });
    });
});