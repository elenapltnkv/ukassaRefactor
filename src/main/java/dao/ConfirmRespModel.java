package dao;

import lombok.Data;

import java.util.Date;

@Data
public class ConfirmRespModel {
    private String id;
    private String status;
    private Amount amount;
    private IncomeAmount income_amount;
    private String description;
    private Recipient recipient;
    private PaymentMethod payment_method;
    private Date captured_at;
    private Date created_at;
    private boolean test;
    private RefundedAmount refunded_amount;
    private boolean paid;
    private boolean refundable;
    private Metadata metadata;
    private AuthorizationDetails authorization_details;

    @Data
    public static class Amount {
        private String value;
        private String currency;
    }

    @Data
    public static class AuthorizationDetails {
        private String rrn;
        private String auth_code;
        private ThreeDSecure three_d_secure;
    }

    @Data
    public static class Card {
        private String first6;
        private String last4;
        private String expiry_year;
        private String expiry_month;
        private String card_type;
        private CardProduct card_product;
        private String issuer_country;
    }

    @Data
    public static class CardProduct {
        private String code;
    }

    @Data
    public static class IncomeAmount {
        private String value;
        private String currency;
    }

    @Data
    public static class Metadata {
    }

    @Data
    public static class PaymentMethod {
        private String type;
        private String id;
        private boolean saved;
        private String title;
        private Card card;
    }

    @Data
    public static class Recipient {
        private String account_id;
        private String gateway_id;
    }

    @Data
    public static class RefundedAmount {
        private String value;
        private String currency;
    }

    @Data
    public static class ThreeDSecure {
        private boolean applied;
        private boolean method_completed;
        private boolean challenge_completed;
    }
}
