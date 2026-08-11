// Web Audio API Synthetic Bell Sound
function playNotificationSound() {
    try {
        const audioCtx = new (window.AudioContext || window.webkitAudioContext)();
        
        // Create oscillator for the main tone
        const osc1 = audioCtx.createOscillator();
        // Create oscillator for the harmonic tone (crystal-like chime)
        const osc2 = audioCtx.createOscillator();
        
        const gainNode = audioCtx.createGain();
        
        osc1.type = 'sine';
        osc1.frequency.setValueAtTime(880, audioCtx.currentTime); // A5 note
        
        osc2.type = 'sine';
        osc2.frequency.setValueAtTime(1760, audioCtx.currentTime); // A6 (double freq for chime)
        
        gainNode.gain.setValueAtTime(0, audioCtx.currentTime);
        gainNode.gain.linearRampToValueAtTime(0.15, audioCtx.currentTime + 0.02);
        gainNode.gain.exponentialRampToValueAtTime(0.0001, audioCtx.currentTime + 1.0);
        
        osc1.connect(gainNode);
        osc2.connect(gainNode);
        gainNode.connect(audioCtx.destination);
        
        osc1.start();
        osc2.start();
        osc1.stop(audioCtx.currentTime + 1.0);
        osc2.stop(audioCtx.currentTime + 1.0);
    } catch (e) {
        console.warn("Trình duyệt chặn phát âm thanh trước khi tương tác: ", e);
    }
}

// Global Order State for Polling Comparison
let knownOrderIds = new Set();
let isFirstLoad = true;

// AJAX Polling Logic for Dashboard
function initDashboardPolling(contextPath) {
    const pendingContainer = document.getElementById('pending-orders-list');
    const makingContainer = document.getElementById('making-orders-list');
    
    if (!pendingContainer && !makingContainer) return; // Not on dashboard page
    
    // Initial load: collect existing order IDs from DOM
    document.querySelectorAll('.order-card').forEach(card => {
        const id = card.getAttribute('data-order-id');
        if (id) knownOrderIds.add(parseInt(id));
    });
    isFirstLoad = false;

    // Run polling every 5 seconds
    setInterval(() => {
        fetch(contextPath + '/api/barista/orders')
            .then(res => res.json())
            .then(data => {
                updateDashboardDOM(data, contextPath);
            })
            .catch(err => console.error("Lỗi Polling dữ liệu đơn hàng: ", err));
    }, 5000);
}

function updateDashboardDOM(data, contextPath) {
    // 1. Update stats counts in header/cards
    const statsMap = data.counts;
    
    updateTextAndBadge('stats-pending-count', statsMap.PENDING);
    updateTextAndBadge('stats-making-count', statsMap.MAKING);
    updateTextAndBadge('stats-completed-count', statsMap.COMPLETED);
    updateTextAndBadge('stats-cancelled-count', statsMap.CANCELLED);
    
    // Sidebar badges
    updateTextAndBadge('sidebar-pending-badge', statsMap.PENDING);
    updateTextAndBadge('sidebar-making-badge', statsMap.MAKING);

    // 2. Separate orders into PENDING and MAKING lists
    const pendingOrders = data.orders.filter(o => o.status === 'PENDING');
    const makingOrders = data.orders.filter(o => o.status === 'MAKING');

    // Check if there are any NEW pending order IDs compared to what we know
    let hasNewOrder = false;
    pendingOrders.forEach(o => {
        if (!knownOrderIds.has(o.id)) {
            knownOrderIds.add(o.id);
            hasNewOrder = true;
        }
    });

    if (hasNewOrder && !isFirstLoad) {
        playNotificationSound();
    }

    // 3. Re-render PENDING List
    const pendingContainer = document.getElementById('pending-orders-list');
    if (pendingContainer) {
        if (pendingOrders.length === 0) {
            pendingContainer.innerHTML = `
                <div class="empty-state">
                    <div class="empty-icon"><i class="fas fa-coffee"></i></div>
                    <p>Không có đơn hàng mới nào</p>
                </div>`;
        } else {
            pendingContainer.innerHTML = pendingOrders.map(o => renderOrderCard(o, contextPath)).join('');
        }
    }

    // 4. Re-render MAKING List
    const makingContainer = document.getElementById('making-orders-list');
    if (makingContainer) {
        if (makingOrders.length === 0) {
            makingContainer.innerHTML = `
                <div class="empty-state">
                    <div class="empty-icon"><i class="fas fa-mug-hot"></i></div>
                    <p>Không có đơn đang pha chế</p>
                </div>`;
        } else {
            makingContainer.innerHTML = makingOrders.map(o => renderOrderCard(o, contextPath)).join('');
        }
    }
}

function updateTextAndBadge(elementId, value) {
    const el = document.getElementById(elementId);
    if (el) {
        el.innerText = value;
        // Hide badge if count is 0
        if (elementId.includes('badge')) {
            el.style.display = value > 0 ? 'inline-block' : 'none';
        }
    }
}

function renderOrderCard(order, contextPath) {
    const isPending = order.status === 'PENDING';
    const actionBtn = isPending 
        ? `<form action="${contextPath}/barista/order-detail" method="POST" style="margin: 0;">
             <input type="hidden" name="id" value="${order.id}">
             <input type="hidden" name="status" value="MAKING">
             <input type="hidden" name="note" value="Đã nhận đơn">
             <button type="submit" class="btn"><i class="fas fa-play"></i> Nhận đơn (Making)</button>
           </form>`
        : `<a href="${contextPath}/barista/order-detail?id=${order.id}" class="btn btn-secondary">
             <i class="fas fa-eye"></i> Pha chế & Chi tiết
           </a>`;

    const itemsHtml = order.items.map(item => `
        <li class="order-item-row">
            <div>
                <div class="item-qty-name">
                    <span class="item-qty">${item.quantity}x</span>
                    <span class="item-name">${escapeHtml(item.itemName)}</span>
                </div>
                ${item.note ? `<span class="item-note"><i class="fas fa-info-circle"></i> ${escapeHtml(item.note)}</span>` : ''}
            </div>
            <span class="color-text-secondary">${formatCurrency(item.price * item.quantity)}</span>
        </li>
    `).join('');

    return `
        <div class="order-card" data-order-id="${order.id}">
            <div class="order-card-header">
                <div class="order-id-table">
                    <span class="order-id">Đơn #${order.id} - ${escapeHtml(order.tableNumber)}</span>
                    <span class="order-customer">Khách: <span>${escapeHtml(order.customerName)}</span></span>
                </div>
                <div class="order-time-badge">
                    <i class="far fa-clock"></i> ${order.timeFormatted}
                </div>
            </div>
            <ul class="order-items-list">
                ${itemsHtml}
            </ul>
            <div class="order-card-footer">
                <span class="order-price">${formatCurrency(order.totalPrice)}</span>
                ${actionBtn}
            </div>
        </div>
    `;
}

// Countdown Timer Logic (ĐÃ SỬA: Giữ nguyên mốc đếm ngược ban đầu qua localStorage)
function initBrewCountdown() {
    const countdownEl = document.getElementById('countdown-timer');
    const countdownBox = document.getElementById('countdown-box');
    if (!countdownEl || !countdownBox) return;

    // 1. Lấy ID đơn hàng từ thuộc tính data-order-id hoặc tiêu đề
    const orderId = countdownEl.getAttribute('data-order-id') || 'current_order';
    const storageKey = 'brew_start_time_order_' + orderId;

    let createdAt = parseInt(countdownEl.getAttribute('data-created-at'));

    // 2. Ưu tiên đọc thời điểm bắt đầu từ localStorage để không bị reset khi bấm nút/tải lại trang
    let savedStartTime = localStorage.getItem(storageKey);

    if (savedStartTime) {
        createdAt = parseInt(savedStartTime);
    } else if (createdAt && !isNaN(createdAt)) {
        localStorage.setItem(storageKey, createdAt);
    } else {
        createdAt = Date.now();
        localStorage.setItem(storageKey, createdAt);
    }

    // Tiêu chuẩn pha chế: 10 phút (600,000 ms)
    const brewingLimitMs = 10 * 60 * 1000;
    const targetTime = createdAt + brewingLimitMs;

    function updateTimer() {
        const now = Date.now();
        const diff = targetTime - now;

        if (diff <= 0) {
            countdownEl.innerText = "00:00";
            countdownBox.classList.add('overdue');
            
            // Cảnh báo quá hạn
            let alertEl = document.getElementById('overdue-alert');
            if (!alertEl) {
                alertEl = document.createElement('div');
                alertEl.id = 'overdue-alert';
                alertEl.style.fontSize = '0.85rem';
                alertEl.style.fontWeight = 'bold';
                alertEl.style.color = '#ef4444';
                alertEl.style.marginTop = '0.5rem';
                alertEl.innerText = "⚠️ ĐƠN HÀNG QUÁ HẠN PHA CHẾ!";
                countdownBox.appendChild(alertEl);
            }
        } else {
            const minutes = Math.floor(diff / 60000);
            const seconds = Math.floor((diff % 60000) / 1000);
            
            const minStr = minutes.toString().padStart(2, '0');
            const secStr = seconds.toString().padStart(2, '0');
            
            countdownEl.innerText = `${minStr}:${secStr}`;
            
            // Cảnh báo màu vàng khi còn dưới 2 phút
            if (diff < 2 * 60 * 1000) {
                countdownBox.style.borderColor = '#fbbf24';
            }
        }
    }

    // Chạy đếm ngược ngay và mỗi 1 giây
    updateTimer();
    const intervalId = setInterval(() => {
        if (!document.getElementById('countdown-timer')) {
            clearInterval(intervalId);
            return;
        }
        updateTimer();
    }, 1000);
}

// Utility Helpers
function formatCurrency(amount) {
    return amount.toLocaleString('vi-VN') + ' VNĐ';
}

function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/&/g, "&amp;")
              .replace(/</g, "&lt;")
              .replace(/>/g, "&gt;")
              .replace(/"/g, "&quot;")
              .replace(/'/g, "&#039;");
}

// Initialize on DOM Content Loaded
document.addEventListener('DOMContentLoaded', () => {
    // Check if context path is available in window object
    const contextPath = window.contextPath || '';
    
    // Auto-activate features based on current DOM elements
    initDashboardPolling(contextPath);
    initBrewCountdown();

    // Enable play sound on first user click to bypass browser audio policies
    const unlockAudio = () => {
        playNotificationSound();
        document.removeEventListener('click', unlockAudio);
    };
    document.addEventListener('click', unlockAudio);
});