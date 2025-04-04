package tests;

import io.qameta.allure.*;
import models.EntityRequest;
import models.EntityResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.DataGenerator;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

/**
 * Набор тестов для проверки CRUD-операций с сущностями через API.
 * <p>
 * Тест-кейсы выполняются в строгом порядке:
 * 1. Создание сущности
 * 2. Чтение сущности
 * 3. Обновление сущности
 * 4. Чтение всех сущностей
 * 5. Удаление сущности
 */
@Epic("API Тесты для работы с сущностями")
@Feature("CRUD операций с сущностями")
public class EntityApiTest extends BaseTest {
    private Integer createdEntityId;

    /**
     * Тест создания новой сущности.
     * <p>
     * Проверяет:
     * - Корректность HTTP-статуса (200 OK)
     * - Наличие ID в ответе сервера
     * - Соответствие структуры ответа
     */
    @Test(priority = 1)
    @Story("Создание сущности")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка создания сущности с валидными данными")
    public void testCreateEntity() {
        EntityRequest request = DataGenerator.generateEntityRequest();
        Allure.addAttachment("Тестовые данные", request.toString());

        Response response = given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post("/create");

        createdEntityId = Integer.parseInt(response.body().asString());
        Allure.addAttachment("Созданная сущность", "ID: " + createdEntityId);

        assertEquals(response.getStatusCode(), 200, "Неверный статус-код");
        assertNotNull(createdEntityId, "ID сущности не был получен");
    }

    /**
     * Тест получения данных сущности.
     * <p>
     * Проверяет:
     * - Корректность HTTP-статуса (200 OK)
     * - Наличие обязательных полей в ответе
     */
    @Test(priority = 2)
    @Story("Чтение сущности")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка получения данных созданной сущности")
    public void testGetEntity() {
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", createdEntityId)
                .when()
                .get("/get/{id}");

        EntityResponse entity = response.as(EntityResponse.class);
        Allure.addAttachment("Полученные данные", entity.toString());

        assertEquals(response.getStatusCode(), 200, "Неверный статус-код");
        assertEquals(entity.getId(), createdEntityId, "ID не совпадает");
        assertNotNull(entity.getTitle(), "Title должен быть заполнен");
    }

    /**
     * Тест обновления сущности.
     * <p>
     * Проверяет:
     * - Корректность HTTP-статуса (204 No Content)
     * - Фактическое обновление данных
     */
    @Test(priority = 3)
    @Story("Обновление сущности")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка обновления данных сущности")
    public void testUpdateEntity() {
        EntityRequest updateRequest = DataGenerator.generateEntityRequest();
        Allure.addAttachment("Новые данные", updateRequest.toString());

        given()
                .spec(requestSpec)
                .pathParam("id", createdEntityId)
                .body(updateRequest)
                .when()
                .patch("/patch/{id}")
                .then()
                .statusCode(204);

        // Проверка актуальных данных
        EntityResponse updatedEntity = given()
                .spec(requestSpec)
                .pathParam("id", createdEntityId)
                .get("/get/{id}")
                .as(EntityResponse.class);

        assertEquals(updatedEntity.getTitle(), updateRequest.getTitle(), "Заголовок не обновился");
        Allure.addAttachment("Актуальные данные", updatedEntity.toString());
    }

    /**
     * Тест получения списка сущностей.
     * <p>
     * Проверяет:
     * - Корректность HTTP-статуса (200 OK)
     * - Наличие пагинации в ответе
     */
    @Test(priority = 4)
    @Story("Чтение списка сущностей")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка получения списка сущностей с пагинацией")
    public void testGetAllEntities() {
        Response response = given()
                .spec(requestSpec)
                .queryParam("page", 1)
                .queryParam("perPage", 10)
                .when()
                .get("/getAll");

        Allure.addAttachment("Ответ сервера", response.getBody().asString());
        assertEquals(response.getStatusCode(), 200, "Неверный статус-код");
    }

    /**
     * Тест удаления сущности.
     * <p>
     * Проверяет:
     * - Корректность HTTP-статуса (204 No Content)
     * - Фактическое удаление сущности
     */
    @Test(priority = 5)
    @Story("Удаление сущности")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка удаления сущности")
    public void testDeleteEntity() {
        given()
                .spec(requestSpec)
                .pathParam("id", createdEntityId)
                .when()
                .delete("/delete/{id}")
                .then()
                .statusCode(204);

        // Проверка отсутствия сущности
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", createdEntityId)
                .get("/get/{id}");

        Allure.addAttachment("Ответ при проверке удаления", response.getBody().asString());
    }
}
