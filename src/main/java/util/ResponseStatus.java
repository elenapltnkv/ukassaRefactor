package util;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    WAITING("waiting_for_capture"),
    SUCC("succeeded"),
    CANC("canceled");

    private final String respStatus;

    ResponseStatus(String respStatus) {
        this.respStatus = respStatus;
    }


}
