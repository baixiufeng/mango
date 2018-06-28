package com.mango;

import com.mango.config.Appctx;
import com.mango.model.Users;
import com.mango.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(Application.class);
		Appctx.ctx = application.run(args);
		logger.info("mango State success");
	}

	@PostConstruct
	public void initApplication() {
		logger.info("Running with Spring profile(s) : {}");
	}

	public void initialUser() {
		UserService suserService = (UserService) Appctx.ctx.getBean("userService");
		Users su = suserService.findByName("TEST");
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
		su.setPassword(bc.encode(su.getPassword()));
		logger.info("密码" + su.getPassword());
		suserService.update(su);
	}


}
