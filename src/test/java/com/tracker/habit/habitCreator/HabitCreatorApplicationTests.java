package com.tracker.habit.habitCreator;

import com.tracker.habit.habitCreator.dto.HabitRecord;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HabitCreatorApplicationTests {

	@LocalServerPort
	private Integer port;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:15-alpine"
	);

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
	}

	@Test
	@Sql("/scripts/test_data.sql")
	void getHabitsIntegrationTest() {

		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/api/habits/")
				.then()
				.statusCode(200)
				.body(".", hasSize(3));
	}

	@Test
	void postHabitIntegrationTest() {
		HabitRecord habitRecord = new HabitRecord(1L, "Eat healthy food", 25, 1L);

		given()
				.contentType(ContentType.JSON)
				.body(habitRecord)
				.when()
				.post("/api/habits/")
				.then()
				.statusCode(201)
				.header("Location", "http://localhost:8081/api/habits/7");
	}

}
