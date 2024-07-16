package dao;

import lombok.Data;

@Data
public class ConfirmModel {
    private Amount amount;
    @Data
    public static class Amount{
        private String value;
        private String currency;
    }
}
