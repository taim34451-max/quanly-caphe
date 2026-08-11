package WebSocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * ServerEndpoint điều hướng dữ liệu Real-time giữa User và Barista
 */
@ServerEndpoint("/barista-websocket")
public class BaristaWebSocket {

    // Danh sách thread-safe lưu các phiên làm việc của màn hình Barista
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("🟢 Màn hình Barista đã kết nối WebSocket ID: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("🔴 Barista ngắt kết nối WebSocket ID: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * Hàm phát thông báo đơn hàng mới tới TẤT CẢ màn hình Barista lập tức
     */
    public static void broadcast(String message) {
        synchronized (sessions) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    try {
                        s.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
