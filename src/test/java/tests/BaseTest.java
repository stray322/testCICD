package tests;

import io.qameta.allure.Allure;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

/**
 * Базовый класс для настройки тестового окружения.
 * Содержит общие конфигурации для всех API-тестов:
 * - Инициализация базового URL
 * - Настройка спецификации HTTP-запросов
 * - Логирование ключевых параметров в Allure-отчёт
 */
public class BaseTest {

    /**
     * Базовый URL API-сервиса, загружаемый из конфигурационного файла.
     * @see ConfigReader#getProperty(String)
     */
    protected static final String BASE_URL = ConfigReader.getProperty("base.url");

    /**
     * Спецификация HTTP-запроса, используемая для всех тестовых методов.
     * Включает:
     * - Базовый URL
     * - Content-Type: application/json
     */
    protected RequestSpecification requestSpec;

    /**
     * Метод инициализации перед запуском тестового класса.
     * Выполняет:
     * 1. Настройку базовых параметров запроса
     * 2. Логирование параметров в Allure-отчёт
     */
    @BeforeClass
    public void setup() {
        Allure.step("Настройка базовой спецификации запроса");
        configureRequestSpec();
        logSpecDetailsToAllure();
    }

    /**
     * Создаёт спецификацию запроса с параметрами:
     * - Базовый URL из конфигурации
     * - Content-Type: JSON
     */
    private void configureRequestSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * Добавляет ключевые параметры в Allure-отчёт:
     * - Базовый URL
     * - Content-Type
     */
    private void logSpecDetailsToAllure() {
        Allure.addAttachment("Base URL", "text/uri-list", BASE_URL);
        Allure.addAttachment("Content-Type", ContentType.JSON.toString());
    }
}
