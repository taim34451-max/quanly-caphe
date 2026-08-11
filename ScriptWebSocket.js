// Tự động nhận diện WebSocket URL linh hoạt cho mọi máy
const protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
const pathParts = window.location.pathname.split('/');
const contextPath = pathParts.length > 1 && pathParts[1] ? '/' + pathParts[1] : '';
const socketUrl = protocol + window.location.host + contextPath + "/barista-websocket";

const socket = new WebSocket(socketUrl);

// Khi nhận được tín hiệu "NEW_ORDER" ➔ Tự động nạp lại danh sách đơn hàng cho Barista (không cần F5)
socket.onmessage = function(event) {
    if (typeof renderOrders === "function") renderOrders();
    else if (typeof loadData === "function") loadData();
    else location.reload();
};

//nhúng dòng này và barista.jsp
<script src="ScriptWebSocket.js"></script>
