package com.VRThemePark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VrThemeParkApplication {

	private static final Logger log = LoggerFactory.getLogger(VrThemeParkApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VrThemeParkApplication.class, args);
		log.info("Application started......");
	}

}
