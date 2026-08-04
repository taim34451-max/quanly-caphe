console.log("1. File JS đã được trình duyệt đọc thành công!");

document.addEventListener('DOMContentLoaded', () => {
    // 1. Chạy logic hiển thị Menu Phân quyền
  /*  renderMenu();*/

    // 2. Chạy logic Slider (nếu trang hiện tại có slider)
    initSlider();

/*    // 3. Chạy logic Nhớ thông tin giao hàng (nếu đang ở trang Giỏ hàng)
    initCheckoutForm();

    // 4. Chạy logic Đăng nhập (nếu đang ở trang Đăng nhập)
    initLoginForm();

    initAdminProduct(); // Gọi Module Admin CRUD*/
});
/*
// ==========================================
// MODULE 1: LOGIC PHÂN QUYỀN RENDER MENU (ĐÃ REFACTOR THEO DRY)
// ==========================================
function renderMenu() {
    const nav = document.getElementById('main-nav');
    if (!nav) return; 

    const userData = localStorage.getItem('currentUser');
    const currentUser = userData ? JSON.parse(userData) : null;

    // 1. Menu dùng chung (Tất cả mọi người đều thấy Trang chủ)
    let menuHTML = `<a href="trang-chu.jsp" class="active">Trang chủ</a>`;

    if (!currentUser) {
        // 2A. Khối dành riêng cho Khách chưa đăng nhập
        menuHTML += `
            <a href="thuc-don.jsp">Thực đơn</a>
            <a href="gio-hang.jsp">Giỏ hàng</a>
            <span style="color: #fff; margin: 0 15px;">|</span>
            <a href="dang-ky.jsp" style="color: var(--primary-color); font-weight: bold;">Đăng ký</a>
            <a href="dang-nhap.jsp">Đăng nhập</a>
        `;
    } else {
        // 2B. Khối dành cho người đã đăng nhập, rẽ nhánh theo Role
        switch (currentUser.role) {
            case 'CUSTOMER':
                menuHTML += `
                    <a href="thuc-don.jsp">Thực đơn</a>
                    <a href="gio-hang.jsp">Giỏ hàng</a>
                `;
                break;
            case 'MANAGER':
                menuHTML += `
                    <a href="quan-ly-thuc-don.jsp">QL Thực đơn</a>
                    <a href="quan-ly-kho.jsp">QL Kho hàng</a>
                    <a href="quan-ly-nhan-vien.jsp">QL Nhân viên</a>
                    <a href="bao-cao-doanh-thu.jsp">Báo cáo</a>
                `;
                break;
            case 'BARISTA':
                menuHTML += `
                    <a href="nhan-don-hang.jsp">Nhận Đơn Hàng</a>
                    <a href="cap-nhat-trang-thai.jsp">Cập Nhật Trạng Thái</a>
                `;
                break;
        }

        // 3. Cấu hình màu sắc và danh xưng (Object Mapping)
        const roleConfig = {
            'CUSTOMER': { color: 'var(--primary-color)', prefix: 'Chào,' },
            'MANAGER': { color: 'red', prefix: 'Admin:' },
            'BARISTA': { color: 'orange', prefix: 'Pha chế:' }
        };

        const config = roleConfig[currentUser.role] || roleConfig['CUSTOMER']; // Fallback nếu lỗi role

        // 4. Khối User & Đăng xuất (Dùng chung cho TẤT CẢ user đã đăng nhập)
        menuHTML += `
            <span style="color: #fff; margin: 0 15px;">|</span>
            <span style="color: ${config.color}; font-weight: bold;">${config.prefix} ${currentUser.fullName}</span>
            <a href="#" onclick="logout()" style="color: #ccc; margin-left: 10px; text-decoration: none;">Đăng xuất</a>
        `;
    }

    // Gắn HTML vào DOM một lần duy nhất
    nav.innerHTML = menuHTML;
}

window.logout = function() {
    localStorage.removeItem('currentUser');
    window.location.href = 'dang-nhap.jsp';
};
*/
// ==========================================
// MODULE 2: LOGIC SLIDER BANNER
// ==========================================
function initSlider() {
    const slides = document.querySelector('.slides');
    const dots = document.querySelectorAll('.dot');
    
    if (!slides || dots.length === 0) return;

    let currentIndex = 0;
    let slideInterval;

    function goToSlide(index) {
        currentIndex = index;
        slides.style.transform = `translateX(-${currentIndex * 16.6666}%)`;
        
        dots.forEach(dot => dot.classList.remove('active'));
        dots[currentIndex].classList.add('active');
    }

    dots.forEach((dot, index) => {
        dot.addEventListener('click', () => {
            goToSlide(index);
            resetInterval(); 
        });
    });

    function startInterval() {
        slideInterval = setInterval(() => {
            let nextIndex = (currentIndex + 1) % dots.length;
            goToSlide(nextIndex);
        }, 4000); 
    }

    function resetInterval() {
        clearInterval(slideInterval);
        startInterval();
    }

    goToSlide(0); 
    startInterval();
}
/*
// ==========================================
// MODULE 3: LƯU TRỮ THÔNG TIN GIAO HÀNG
// ==========================================
function initCheckoutForm() {
    const nameInput = document.getElementById('customer-name');
    const phoneInput = document.getElementById('customer-phone');
    const addressInput = document.getElementById('customer-address');
    const noteInput = document.getElementById('customer-note');

    if (!nameInput || !phoneInput || !addressInput) return;

    const savedData = JSON.parse(localStorage.getItem('checkoutInfo')) || {};
    
    if (savedData.name) nameInput.value = savedData.name;
    if (savedData.phone) phoneInput.value = savedData.phone;
    if (savedData.address) addressInput.value = savedData.address;
    if (savedData.note) noteInput.value = savedData.note;

    function saveToStorage() {
        const checkoutInfo = {
            name: nameInput.value,
            phone: phoneInput.value,
            address: addressInput.value,
            note: noteInput.value
        };
        localStorage.setItem('checkoutInfo', JSON.stringify(checkoutInfo));
    }

    // Rút gọn việc lắng nghe sự kiện bằng mảng (DRY)
    [nameInput, phoneInput, addressInput, noteInput].forEach(input => {
        if (input) input.addEventListener('input', saveToStorage);
    });
}

// ==========================================
// MODULE 4: LOGIC ĐĂNG NHẬP (MOCK DATA)
// ==========================================
function initLoginForm() {
    // Tìm form đăng nhập bằng ID
    const loginForm = document.getElementById('login-form');
    
    // Nếu không tìm thấy form (nghĩa là đang ở trang khác), thoát hàm ngay để tránh lỗi
    if (!loginForm) return; 

    // Dữ liệu giả lập (Mock Database) - Cấu trúc role phải khớp với roleConfig ở Module 1
    const mockDatabase = [
        { username: "khach", password: "123", role: "CUSTOMER", fullName: "Khách Hàng VIP" },
        { username: "admin", password: "123", role: "MANAGER", fullName: "Quản Trị Viên" },
        { username: "barista", password: "123", role: "BARISTA", fullName: "Nhân Viên Pha Chế" }
    ];

    // Lắng nghe sự kiện submit của form
    loginForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Chặn load lại trang

        const userIn = document.getElementById("username").value;
        const passIn = document.getElementById("password").value;

        // Thuật toán tìm kiếm tài khoản
        const validUser = mockDatabase.find(u => u.username === userIn && u.password === passIn);

        if (validUser) {
            // Lưu vào localStorage. Key 'currentUser' và các trường dữ liệu khớp 100% với Module 1
            localStorage.setItem("currentUser", JSON.stringify({
                username: validUser.username,
                role: validUser.role,
                fullName: validUser.fullName
            }));

            alert(`Đăng nhập thành công! Xin chào ${validUser.fullName}`);
            window.location.href = "trang-chu.jsp"; // Chuyển hướng về trang chủ
        } else {
            alert("Sai tài khoản hoặc mật khẩu! Vui lòng thử lại.");
        }
    });

    // ==========================================
// MODULE 5: QUẢN LÝ THỰC ĐƠN (CRUD SẢN PHẨM)
// ==========================================
function initAdminProduct() {
    const tableBody = document.getElementById('product-list-body');
    const modal = document.getElementById('product-modal');
    const form = document.getElementById('product-form');
    const btnAdd = document.getElementById('btn-add-product');
    const btnClose = document.getElementById('close-modal');
    const searchInput = document.getElementById('search-product');

    // Nếu không ở trang Quản lý thực đơn thì thoát
    if (!tableBody) return;

    // 1. Khởi tạo Mock Database Sản phẩm (Nếu chưa có thì tạo mẫu)
    let products = JSON.parse(localStorage.getItem('productsDatabase')) || [
        { id: "SP01", name: "Cà Phê Sữa Đá", price: 29000, img: "imgs/cf.jpg", category: "Cà phê" },
        { id: "SP02", name: "Trà Sen Vàng", price: 45000, img: "imgs/tra.jpg", category: "Trà" }
    ];

    // Cập nhật lại localStorage
    const saveToDB = () => localStorage.setItem('productsDatabase', JSON.stringify(products));

    // 2. READ: Hàm render dữ liệu ra bảng
    const renderTable = (dataToRender) => {
        tableBody.innerHTML = '';
        if (dataToRender.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="6" style="text-align:center;">Không tìm thấy sản phẩm nào.</td></tr>`;
            return;
        }

        dataToRender.forEach((sp, index) => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${sp.id}</td>
                <td><img src="${sp.img}" alt="${sp.name}" style="width: 50px; height: 50px; object-fit: cover; border-radius: 4px;"></td>
                <td style="font-weight: bold;">${sp.name}</td>
                <td style="color: red;">${sp.price.toLocaleString('vi-VN')} đ</td>
                <td>${sp.category}</td>
                <td>
                    <button class="btn-edit" data-id="${sp.id}" style="background: orange; color: white; border: none; padding: 5px 10px; cursor: pointer; margin-right: 5px;">Sửa</button>
                    <button class="btn-delete" data-id="${sp.id}" style="background: red; color: white; border: none; padding: 5px 10px; cursor: pointer;">Xóa</button>
                </td>
            `;
            tableBody.appendChild(tr);
        });
    };

    // Chạy render lần đầu
    renderTable(products);

    // 3. UI Tương tác: Mở / Đóng Modal
    const openModal = (title) => {
        document.getElementById('modal-title').innerText = title;
        modal.style.display = 'flex';
    };
    
    const closeModal = () => {
        modal.style.display = 'none';
        form.reset();
        document.getElementById('product-id').value = ''; // Xóa hidden ID
    };

    btnAdd.addEventListener('click', () => openModal('Thêm Món Mới'));
    btnClose.addEventListener('click', closeModal);

    // 4. CREATE & UPDATE: Xử lý Submit Form
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const hiddenId = document.getElementById('product-id').value;
        const newProduct = {
            name: document.getElementById('product-name').value,
            price: parseInt(document.getElementById('product-price').value),
            img: document.getElementById('product-img').value,
            category: document.getElementById('product-category').value
        };

        if (hiddenId) {
            // Trường hợp có ID -> UPDATE
            const index = products.findIndex(p => p.id === hiddenId);
            if (index !== -1) {
                products[index] = { ...products[index], ...newProduct };
                alert('Cập nhật thành công!');
            }
        } else {
            // Trường hợp không có ID -> CREATE
            newProduct.id = "SP" + (new Date().getTime().toString().slice(-4)); // Tạo ID ngẫu nhiên
            products.push(newProduct);
            alert('Thêm mới thành công!');
        }

        saveToDB();
        renderTable(products);
        closeModal();
    });

    // 5. DELETE & Nút Sửa: Dùng Event Delegation trên thẻ <tbody>
    tableBody.addEventListener('click', (e) => {
        const id = e.target.getAttribute('data-id');
        
        if (e.target.classList.contains('btn-delete')) {
            // Logic XÓA
            if (confirm('Bạn có chắc chắn muốn xóa sản phẩm này?')) {
                products = products.filter(p => p.id !== id);
                saveToDB();
                renderTable(products);
            }
        } else if (e.target.classList.contains('btn-edit')) {
            // Logic SỬA (Đẩy dữ liệu lên form)
            const sp = products.find(p => p.id === id);
            if (sp) {
                document.getElementById('product-id').value = sp.id;
                document.getElementById('product-name').value = sp.name;
                document.getElementById('product-price').value = sp.price;
                document.getElementById('product-img').value = sp.img;
                document.getElementById('product-category').value = sp.category;
                openModal('Cập Nhật Món');
            }
        }
    });

    // 6. SEARCH: Tìm kiếm realtime
    searchInput.addEventListener('input', (e) => {
        const keyword = e.target.value.toLowerCase();
        const filteredProducts = products.filter(p => 
            p.name.toLowerCase().includes(keyword) || 
            p.id.toLowerCase().includes(keyword)
        );
        renderTable(filteredProducts);
    });
}
*/
