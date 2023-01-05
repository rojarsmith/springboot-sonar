package demo.bank.springboot.sonar.websocket;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Rojar Smith
 *
 * @date 2023 Jan 5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebSocketEndpoint {
	/*
	 * WebSocket path
	 */
	String value();
}
