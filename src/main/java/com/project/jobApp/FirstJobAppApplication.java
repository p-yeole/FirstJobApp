package com.project.jobApp;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstJobAppApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println("JVM Timezone = " + TimeZone.getDefault());
        SpringApplication.run(FirstJobAppApplication.class, args);
	}

}
