package org.trading.ticker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickerApplication.class, args);
	}
}
