//package WebSocket;
//
//import java.util.Set;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//import jakarta.websocket.Session;
//
//public class WebSocketManager {
//	
//	private static Set<Session> clients = new CopyOnWriteArraySet<Session>();
//	
//	public static void addClient(Session session) {
//		clients.add(session);
//	}
//	
//	public static void removeClient(Session session) {
//		clients.remove(session);
//	}
//
//	
//	public static void boardcast(String mess) {
//		for(Session client : clients) {
//			try {
//				client.getBasicRemote().sendText(mess);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//	}
//}
