package com.raphaelabila.apidashboard.repository.apiuser;

public class RestResponse {
    private String data;
    private String returnCode;
    private String returnMessage;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}
