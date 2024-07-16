package dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataReqModel {


    @Data
    public static class Amount {
        private String value;
        private String currency;
    }

    @Data
    public static class Card {
        private String cardholder;
        private String csc;
        private String expiry_month;
        private String expiry_year;
        private String number;
    }

    @Data
    public static class Confirmation {
        private String type;
        private String return_url;
    }

    @Data
    public static class PaymentMethodData {
        private String type;
        private Card card;

    }

    private Amount amount;
    private PaymentMethodData payment_method_data;
    private Confirmation confirmation;
    private String description;

}
