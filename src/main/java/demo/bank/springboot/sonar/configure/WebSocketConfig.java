package demo.bank.springboot.sonar.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import demo.bank.springboot.sonar.websocket.BackendWebSocketHandler;

/**
 * @author Rojar Smith
 *
 * @date 2023 Jan 5
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final BackendWebSocketHandler backendWebSocketHandler;

	public WebSocketConfig(BackendWebSocketHandler backendWebSocketHandler) {
		this.backendWebSocketHandler = backendWebSocketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(backendWebSocketHandler, "/ws");
	}

}
