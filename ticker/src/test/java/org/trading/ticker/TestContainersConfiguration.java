package org.trading.ticker;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestContainersConfiguration {

	@Bean
	@ServiceConnection
	@RestartScope
	PostgreSQLContainer<?> timescaledb() {
		return new PostgreSQLContainer<>(
				DockerImageName.parse("timescale/timescaledb:latest-pg16")
						.asCompatibleSubstituteFor("postgres"));
	}

//	@Bean
//	@ServiceConnection
//	@RestartScope
//	RabbitMQContainer rabbitContainer() {
//		return new RabbitMQContainer(DockerImageName.parse("rabbitmq:latest"));
//	}

}
