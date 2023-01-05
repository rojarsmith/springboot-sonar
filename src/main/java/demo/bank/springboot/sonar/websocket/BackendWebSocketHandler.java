package demo.bank.springboot.sonar.websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import demo.bank.springboot.sonar.BackendApplicationRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Rojar Smith
 *
 * @date 2023 Jan 5
 */
@Component
@Slf4j
@WebSocketEndpoint("/ws")
public class BackendWebSocketHandler implements WebSocketHandler {

	private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connected, session id: {}, client address {}", session.getId(), session.getRemoteAddress());
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		log.info("Received: {}", message.getPayload());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log.info("Error!");
		exception.printStackTrace();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		log.info("Disconnected, session id: {}, client address {}", session.getId(), session.getRemoteAddress());
		sessions.remove(session.getId(), session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/*
	 * Push text to client.
	 * 
	 * @param msg text
	 */
	public void pushMsg(String msg) {
		final Collection<WebSocketSession> webSocketSessions = sessions.values();
		final TextMessage textMessage = new TextMessage(msg);
		webSocketSessions.forEach(s -> {
			try {
				s.sendMessage(textMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

}
