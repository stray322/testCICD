package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.Allure;
import java.util.List;

/**
 * Модель запроса для создания/обновления сущности.
 * Содержит основные параметры сущности и дополнительную информацию.
 */
public class EntityRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("verified")
    private boolean verified;

    @JsonProperty("importantNumbers")
    private List<Integer> importantNumbers;

    @JsonProperty("addition")
    private Addition addition;

    /**
     * Вложенный класс для дополнительной информации сущности.
     */
    public static class Addition {
        @JsonProperty("additionalInfo")
        private String additionalInfo;

        @JsonProperty("additionalNumber")
        private int additionalNumber;

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
                    "Addition{additionalInfo='%s', additionalNumber=%d}",
                    additionalInfo, additionalNumber
            );
        }
    }

    public EntityRequest() {}

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
                "EntityRequest{title='%s', verified=%s, importantNumbers=%s, addition=%s}",
                title, verified, importantNumbers, addition
        );
    }
}
