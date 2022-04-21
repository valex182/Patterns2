package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        private static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        static RegistrationInfo setUpAll(String status) {
            Faker faker = new Faker();
            RegistrationInfo user = new RegistrationInfo(faker.name().username(), faker.internet().password(), status);
            // сам запрос
            given() // "дано"
                    .spec(requestSpec) // указываем, какую спецификацию используем
                    .body(user) // передаём в теле объект, который будет преобразован в JSON
                    .when() // "когда"
                    .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                    .then() // "тогда ожидаем"
                    .statusCode(200); // код 200 OK
            return user;
        }

        static RegistrationInfo invalidData() {
            Faker faker1 = new Faker();
            return new RegistrationInfo(faker1.name().username(), faker1.internet().password());
        }

        static RegistrationInfo emptyData() {
            return new RegistrationInfo("", "");
        }
    }
}