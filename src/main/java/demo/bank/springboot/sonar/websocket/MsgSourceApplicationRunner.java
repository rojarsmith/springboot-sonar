package demo.bank.springboot.sonar.websocket;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Rojar Smith
 *
 * @date 2023 Jan 5
 */
@Component
public class MsgSourceApplicationRunner implements ApplicationRunner {

	@Value("${app.websocket}")
	private boolean appWebsocket;

	private final BackendWebSocketHandler backendWebSocketHandler;

	public MsgSourceApplicationRunner(BackendWebSocketHandler backendWebSocketHandler) {
		this.backendWebSocketHandler = backendWebSocketHandler;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (appWebsocket) {
			new Thread(() -> {
				while (true) {
					backendWebSocketHandler.pushMsg("New message " + Instant.now());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

}
