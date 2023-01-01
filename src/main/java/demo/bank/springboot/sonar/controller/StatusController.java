package demo.bank.springboot.sonar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rojar Smith
 *
 * @date 2023 Jan 1
 */
@RestController
@RequestMapping("/status")
public class StatusController {

	@GetMapping("hello")
	@ResponseBody
	public ResponseEntity<?> sayHello() {
		return ResponseEntity.ok().body(String.format("hello %s", "Hello"));
	}
}
