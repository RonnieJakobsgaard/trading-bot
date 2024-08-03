package org.trading.ticker;

import org.springframework.boot.SpringApplication;

public class TestTickerApplication {

	public static void main(String[] args) {
		SpringApplication.from(TickerApplication::main).with(TestContainersConfiguration.class).run(args);
	}

}
