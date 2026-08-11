// nhúng dòng này và barista.jsp
// <script src="ScriptWebSocket.js"></script>

/**
 * ScriptWebSocket.js - Module kết nối Real-time cho Barista
 * Chạy hoàn toàn độc lập - Tự động Reconnect khi mất mạng + Phát âm thanh
 */
(function() {
    let socket = null;
    let reconnectInterval = 3000; // Thử kết nối lại sau 3 giây nếu mất mạng

    function connectWebSocket() {
        // 1. Tự động xác định đường dẫn WebSocket tương thích mọi máy
        const protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
        const pathParts = window.location.pathname.split('/');
        const contextPath = pathParts.length > 1 && pathParts[1] ? '/' + pathParts[1] : '';
        const socketUrl = protocol + window.location.host + contextPath + "/barista-websocket";

        console.log("🔄 Đang kết nối tới WebSocket Server: " + socketUrl);
        socket = new WebSocket(socketUrl);

        // 2. Khi kết nối thành công
        socket.onopen = function() {
            console.log("🟢 WebSocket Barista đã kết nối thành công!");
        };

        // 3. KHI CÓ ĐƠN MỚI TỪ USER -> KÍCH HOẠT CẬP NHẬT GIAO DIỆN CỦA BẠN
        socket.onmessage = function(event) {
            console.log("⚡ Nhận tín hiệu đơn hàng mới:", event.data);
            
            // Phát tiếng chuông Ting-Ting báo đơn mới
            playNotificationSound();

            // Gọi các hàm hiển thị hiện có trên trang barista.jsp của bạn
            if (typeof renderOrders === "function") {
                renderOrders();
            } else if (typeof loadData === "function") {
                loadData();
            } else if (typeof fetchOrders === "function") {
                fetchOrders();
            } else {
                location.reload(); // Reload nhẹ nếu không tìm thấy hàm
            }
        };

        // 4. Khi bị ngắt kết nối -> Tự động kết nối lại ngầm
        socket.onclose = function() {
            console.warn("⚠️ Mất kết nối WebSocket. Tự động thử lại sau 3 giây...");
            setTimeout(connectWebSocket, reconnectInterval);
        };

        socket.onerror = function(error) {
            console.error("❌ Lỗi WebSocket:", error);
            socket.close();
        };
    }

    // Hàm phát âm thanh thông báo
    function playNotificationSound() {
        try {
            const audio = new Audio('https://actions.google.com/sounds/v1/alarms/beep_short.ogg');
            audio.play();
        } catch (e) {
            console.log("Âm thanh bị chặn do chính sách trình duyệt");
        }
    }

    // Khởi chạy kết nối ngay khi nạp file JS
    connectWebSocket();
})();
