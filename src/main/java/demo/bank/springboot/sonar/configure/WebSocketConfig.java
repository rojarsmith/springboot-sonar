package demo.bank.springboot.sonar.configure;

import java.util.logging.Handler;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import demo.bank.springboot.sonar.websocket.BackendWebSocketHandler;
import demo.bank.springboot.sonar.websocket.WebSocketEndpoint;

/**
 * @author Rojar Smith
 *
 * @date 2023 Jan 5
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final ObjectProvider<WebSocketHandler> webSocketHandlers;

	public WebSocketConfig(ObjectProvider<WebSocketHandler> webSocketHandlers) {
		this.webSocketHandlers = webSocketHandlers;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		webSocketHandlers.forEach(handler -> {
			final WebSocketEndpoint annotation = handler.getClass().getAnnotation(WebSocketEndpoint.class);
			if (annotation != null) {
				final String endpoint = annotation.value();
				registry.addHandler(handler, endpoint);
			}
		});
	}

}
