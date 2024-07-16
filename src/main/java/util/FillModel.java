package util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum FillModel {
    AMOUNT("1.00"),
    CURRENCY("RUB"),
    CARD_HOLDER("MR CARDHOLDER"),
    CSC("213"),
    MONTH("08"),
    YEAR("2028"),
    CARD_NUMBER("5555555555554444"),
    METHOD("bank_card"),
    CONFIRM_TYPE("redirect"),
    RETURN_URL("https://www.example.com/return_url"),
    DESCRIPTION("Заказ №74"),
    REASON("canceled_by_merchant");

    private final String filler;

    FillModel(String filler) {
        this.filler = filler;
    }


}
