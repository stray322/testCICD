package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.Allure;
import java.util.List;

/**
 * Модель ответа API с данными сущности.
 * Содержит полную информацию о созданной/полученной сущности.
 */
public class EntityResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("verified")
    private boolean verified;

    @JsonProperty("importantNumbers")
    private List<Integer> importantNumbers;

    @JsonProperty("addition")
    private Addition addition;

    /**
     * Вложенный класс для дополнительных данных сущности.
     */
    public static class Addition {
        @JsonProperty("additionalInfo")
        private String additionalInfo;

        @JsonProperty("additionalNumber")
        private int additionalNumber;

        @JsonProperty("id")
        private Integer id;

        /**
         * @return Уникальный идентификатор дополнения
         */
        public Integer getId() {
            Allure.addAttachment("Addition ID", String.valueOf(id));
            return id;
        }

        /**
         * @param id Устанавливает уникальный идентификатор дополнения
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * @return Дополнительная текстовая информация
         */
        public String getAdditionalInfo() {
            Allure.addAttachment("Additional Info", additionalInfo);
            return additionalInfo;
        }

        /**
         * @param additionalInfo Устанавливает дополнительную текстовую информацию
         */
        public void setAdditionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
        }

        /**
         * @return Дополнительное числовое значение
         */
        public int getAdditionalNumber() {
            Allure.addAttachment("Additional Number", String.valueOf(additionalNumber));
            return additionalNumber;
        }

        /**
         * @param additionalNumber Устанавливает дополнительное числовое значение
         */
        public void setAdditionalNumber(int additionalNumber) {
            this.additionalNumber = additionalNumber;
        }

        @Override
        public String toString() {
            return String.format(
                    "Addition{id=%d, additionalInfo='%s', additionalNumber=%d}",
                    id, additionalInfo, additionalNumber
            );
        }
    }

    public EntityResponse() {}

    /**
     * @return Уникальный идентификатор сущности
     */
    public Integer getId() {
        Allure.addAttachment("Entity ID", String.valueOf(id));
        return id;
    }

    /**
     * @param id Устанавливает уникальный идентификатор сущности
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Заголовок сущности
     */
    public String getTitle() {
        Allure.addAttachment("Entity Title", title);
        return title;
    }

    /**
     * @param title Устанавливает заголовок сущности
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Статус верификации сущности
     */
    public boolean isVerified() {
        Allure.addAttachment("Verified Status", String.valueOf(verified));
        return verified;
    }

    /**
     * @param verified Устанавливает статус верификации
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    /**
     * @return Список важных чисел, связанных с сущностью
     */
    public List<Integer> getImportantNumbers() {
        Allure.addAttachment("Important Numbers", importantNumbers.toString());
        return importantNumbers;
    }

    /**
     * @param importantNumbers Устанавливает список важных чисел
     */
    public void setImportantNumbers(List<Integer> importantNumbers) {
        this.importantNumbers = importantNumbers;
    }

/**
 * @return Дополнительная информация о сущности
 */
public Addition getAddition() {
    return addition;
}

    /**
     * @param addition Устанавливает дополнительную информацию
     */
    public void setAddition(Addition addition) {
        Allure.addAttachment("Addition Data", addition.toString());
        this.addition = addition;
    }

    @Override
    public String toString() {
        return String.format(
                "EntityResponse{id=%d, title='%s', verified=%s, importantNumbers=%s, addition=%s}",
                id, title, verified, importantNumbers, addition
        );
    }
}
