package demo.bank.springboot.sonar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rojar Smith
 *
 * @date 2023 Jan 1
 */
@Component
@Slf4j
@Profile({ "default", "prod", "dev", "test" })
public class BackendApplicationRunner implements ApplicationRunner {

	@Value("${spring.profiles.active}")
	private String propertiesActive;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("[InitialApplication] {}", propertiesActive);
	}

}
