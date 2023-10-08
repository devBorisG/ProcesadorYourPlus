package com.uco.yourplus.serviceyourplus.domain;

import com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper;
import com.uco.yourplus.crosscuttingyourplus.helper.StringHelper;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;

import java.util.ArrayList;
import java.util.List;

public class ResponseDomain<T> {

    private StateResponse stateResponse;
    private String message;

    private List<T> data;

    public ResponseDomain() {
        super();
        setData(new ArrayList<>());
        setMessage(StringHelper.EMPTY);
        setStateResponse(StateResponse.ERROR);
    }

    public ResponseDomain(StateResponse stateResponse, String message, List<T> data) {
        super();
        setData(data);
        setMessage(message);
        setStateResponse(stateResponse);
    }

    public StateResponse getStateResponse() {
        return stateResponse;
    }

    public void setStateResponse(StateResponse stateResponse) {
        this.stateResponse = ObjectHelper.getDefaultIfNull(stateResponse,StateResponse.ERROR);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = ObjectHelper.getDefaultIfNull(message, StringHelper.EMPTY);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = ObjectHelper.getDefaultIfNull(data, new ArrayList<>());
    }
}
