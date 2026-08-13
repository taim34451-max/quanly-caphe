package WebSocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocket {

	private static Set<Session> clients = new CopyOnWriteArraySet<Session>();
	
	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		System.out.println("Client kết nối: "+session.getId());
		System.out.println("Số người trong client: "+clients.size()	);
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("Client ngắt kết nối: "+session.getId());
	}
	
	public static void broadcast(String message) {

	    for (Session client : clients) {
	        try {
	            client.getBasicRemote().sendText(message);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
