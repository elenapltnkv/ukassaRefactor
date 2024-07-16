package ru.elenapltnkvtest.test;

import dao.DataReqModel;
import dao.DataResponseNegativeModel;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.elenapltnkvtest.spec.Specifications.resPect;
import static ru.elenapltnkvtest.spec.Specifications.spec;

public class PayoutNegativeTest extends BaseTest {

    DataReqModel dataReqModel = new DataReqModel();
    DataResponseNegativeModel negativeModel = new DataResponseNegativeModel();

    public void fillPaymentData(String number) {
        DataReqModel.Amount amount = new DataReqModel.Amount();
        amount.setValue("1.00");
        amount.setCurrency("RUB");
        dataReqModel.setAmount(amount);

        DataReqModel.Card card = new DataReqModel.Card();
        card.setCardholder("MR CARDHOLDER");
        card.setCsc("213");
        card.setExpiry_month("01");
        card.setExpiry_year("2020");
        card.setNumber(number);
        DataReqModel.PaymentMethodData methodData = new DataReqModel.PaymentMethodData();
        methodData.setType("bank_card");
        methodData.setCard(card);

        dataReqModel.setPayment_method_data(methodData);
        DataReqModel.Confirmation confirmation = new DataReqModel.Confirmation();
        confirmation.setType("redirect");
        confirmation.setReturn_url("https://www.example.com/return_url");
        dataReqModel.setConfirmation(confirmation);
        dataReqModel.setDescription("Заказ №74");
    }



    @Tag("negative")
    @Owner("samigullaeva.ea")
    @DisplayName("Проверка отклонения платежа 'card_expired'")
    @Test

    public void payoutCancelCardExpireTest() {
        fillPaymentData("5555555555554543");
        negativeModel = given(spec)
                //.spec(spec)
                .body(dataReqModel)
                .expect()
                .spec(resPect)
                .when()
                .post()
                .prettyPeek()
                .then()
                .extract()
                .as(DataResponseNegativeModel.class);
        assertThat(negativeModel.getStatus()).isEqualTo("canceled");
        assertThat(negativeModel.getCancellation_details().getReason()).isEqualTo("card_expired");
    }
}

