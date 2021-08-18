package br.com.eleflow.challenge.it;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import br.com.eleflow.challenge.EleflowChallengeApplication;

@SpringBootTest(classes = EleflowChallengeApplication.class)
class EleflowChallengeApplicationIT {
	
	public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.26")
			.withCommand("--default-authentication-plugin=mysql_native_password")
			.withUsername("test")
			.withPassword("test")
			.withDatabaseName("challenge");
	
	static {
		mySQLContainer.start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> mySQLContainer.stop()));
	}
	
	@DynamicPropertySource
	static void mySQLProperties(DynamicPropertyRegistry registry) {
		registry.add("MYSQL_HOST", mySQLContainer::getHost);
		registry.add("MYSQL_PORT", mySQLContainer::getFirstMappedPort);	
		registry.add("MYSQL_USERNAME", mySQLContainer::getUsername);
		registry.add("MYSQL_PASSWORD", mySQLContainer::getPassword);
	}

}
