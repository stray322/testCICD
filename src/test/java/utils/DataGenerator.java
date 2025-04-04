package utils;

import io.qameta.allure.Allure;
import models.EntityRequest;
import java.util.Arrays;

/**
 * Утилита для генерации тестовых данных.
 * <p>
 * Содержит методы для создания готовых объектов запросов
 * с предустановленными или рандомными значениями.
 */
public class DataGenerator {

    /**
     * Генерирует объект запроса для создания/обновления сущности.
     * <p>
     * Автоматически устанавливает:
     * - Уникальный заголовок с временной меткой
     * - Статус verified = true
     * - Фиксированный набор importantNumbers
     * - Дополнительные данные (Addition)
     *
     * @return EntityRequest с тестовыми данными
     */
    public static EntityRequest generateEntityRequest() {
        Allure.step("Генерация тестовых данных для сущности");

        EntityRequest request = new EntityRequest();
        String title = "Test Entity " + System.currentTimeMillis();
        request.setTitle(title);
        request.setVerified(true);
        request.setImportantNumbers(Arrays.asList(4, 5, 6));

        EntityRequest.Addition addition = new EntityRequest.Addition();
        addition.setAdditionalInfo("Обновленная информация");
        addition.setAdditionalNumber(100);
        request.setAddition(addition);

        // Логирование в Allure
        Allure.addAttachment("Сгенерированные данные", "text/plain",
                "Тестовый объект:\n" + request.toString());
        Allure.addAttachment("Уникальный заголовок", title);

        return request;
    }
}
