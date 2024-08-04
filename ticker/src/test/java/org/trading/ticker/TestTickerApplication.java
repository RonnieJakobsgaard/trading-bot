package org.trading.ticker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

public class TestTickerApplication {

	@Bean
	@ServiceConnection
	@RestartScope
	PostgreSQLContainer<?> timescaledb() {
		return new PostgreSQLContainer<>(
				DockerImageName.parse("timescale/timescaledb:latest-pg16")
						.asCompatibleSubstituteFor("postgres"));
	}

	@Bean
	@ServiceConnection
	@RestartScope
	RabbitMQContainer rabbitContainer() {
		return new RabbitMQContainer(DockerImageName.parse("rabbitmq:4.0.0-beta.3-management"));
	}

	public static void main(String[] args) {
		SpringApplication.from(TickerApplication::main).with(TestTickerApplication.class).run(args);
	}

}
