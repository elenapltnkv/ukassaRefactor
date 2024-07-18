package ru.elenapltnkvtest.test;


import dao.ConfirmModel;
import dao.ConfirmRespModel;
import dao.DataReqModel;
import dao.DataRespModel;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.elenapltnkvtest.spec.Specifications.resPect;
import static ru.elenapltnkvtest.spec.Specifications.spec;
import static util.Endpoints.CAPTURE;



public class PayoutCardPositiveConfirmTest extends BaseTest {
    ConfirmModel confirmModel = new ConfirmModel();
    ConfirmRespModel confirmRespModel = new ConfirmRespModel();
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
    @Tag("smoke")
    @Tag("positive")
    @Feature("Позитивные тесты")
    @Story("Succeeded payout")
    @Owner("samigullaeva.ea")
    @DisplayName("Проверка успешного платежа")
    @Test
    public void payoutConfirmTest() {

        fillPaymentData("5555555555554444");
        readInfo();
        ConfirmModel.Amount amount = new ConfirmModel.Amount();
        amount.setValue("1.00");
        amount.setCurrency("RUB");
        confirmModel.setAmount(amount);
        confirmRespModel = given()
                .spec(spec)
                .basePath("/" + payment_id + CAPTURE)
                .body(confirmModel)
                .expect()
                .spec(resPect)
                .when()
                .post()
                .prettyPeek()
                .then()
                .extract()
                .as(ConfirmRespModel.class);

        assertThat(confirmRespModel.getStatus()).isEqualTo("succeeded");
        assertThat(confirmRespModel.getDescription()).isEqualTo("Заказ №74");


        System.out.println("Запрашиваем информацию о платеже: ");
        confirmRespModel = given()
                .spec(spec)
                .basePath("/" + payment_id)
                .expect()
                .spec(resPect)
                .when()
                .get()
                .prettyPeek()
                .then()
                .extract()
                .as(ConfirmRespModel.class);
        assertThat(confirmRespModel.getId()).isEqualTo(payment_id);
        assertThat(confirmRespModel.getStatus()).isEqualTo("succeeded");
        assertThat(confirmRespModel.getAmount().getValue()).isEqualTo("1.00");
        assertThat(confirmRespModel.getAmount().getCurrency()).isEqualTo("RUB");
    }


}
