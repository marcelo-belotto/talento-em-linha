package com.talentoemlinha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TalentoemlinhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentoemlinhaApplication.class, args);
	}

}
