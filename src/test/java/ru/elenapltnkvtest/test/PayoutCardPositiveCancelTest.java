package ru.elenapltnkvtest.test;


import dao.*;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.elenapltnkvtest.spec.Specifications.resPect;
import static ru.elenapltnkvtest.spec.Specifications.spec;
import static util.Endpoints.CANCEL;

public class PayoutCardPositiveCancelTest extends BaseTest {
    DataReqModel dataReqModel = new DataReqModel();
    DataRespModel dataRespModel = new DataRespModel();
    public String payment_id;

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

    public void readInfo() {
        dataRespModel = given()
                .spec(spec)
                .body(dataReqModel)
                .expect()
                .spec(resPect)
                .when()
                .post()
                .prettyPeek()
                .then()
                .extract()
                .as(DataRespModel.class);
        payment_id = dataRespModel.getId();
    }

    CancelRespModel cancelRespModel = new CancelRespModel();

    @Tag("cancel")
    @Owner("samigullaeva.ea")
    @DisplayName("Проверка отклонения платежа 'canceled_by_merchant'")
    @Test

    public void payoutCancelByMerchantTest() {

        fillPaymentData("5555555555554444");
        readInfo();
        cancelRespModel = given(spec)
                .basePath("/" + payment_id + CANCEL)
                .expect()
                .spec(resPect)
                .when()
                .post()
                .prettyPeek()
                .then()
                .extract()
                .as(CancelRespModel.class);

        assertThat(cancelRespModel.getStatus()).isEqualTo("canceled");
        assertThat(cancelRespModel.getCancellation_details().getReason()).isEqualTo("canceled_by_merchant");

        cancelRespModel = given()
                .spec(spec)
                .basePath("/" + payment_id)
                .expect()
                .spec(resPect)
                .when()
                .get()
                .prettyPeek()
                .then()
                .extract()
                .as(CancelRespModel.class);
        assertThat(cancelRespModel.getId()).isEqualTo(payment_id);
        assertThat(cancelRespModel.getStatus()).isEqualTo("canceled");
    }


}
