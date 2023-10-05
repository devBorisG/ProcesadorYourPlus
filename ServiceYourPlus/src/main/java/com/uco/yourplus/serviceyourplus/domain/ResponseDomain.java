package com.uco.yourplus.serviceyourplus.domain;

import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;

public class ResponseDomain {

    StateResponse stateResponse;
    String message;

    public StateResponse getStateResponse() {
        return stateResponse;
    }

    public void setStateResponse(StateResponse stateResponse) {
        this.stateResponse = stateResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
