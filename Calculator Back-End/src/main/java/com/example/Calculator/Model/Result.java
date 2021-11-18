package com.example.Calculator.Model;

import java.math.BigDecimal;

public class Result {
    private String message = "";
    private Boolean error = false;
    private BigDecimal result = null;

    public Result(String message, Boolean error, BigDecimal result) {
        this.message = message;
        this.error = error;
        this.result = result;
    }

    public Result() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String written() {
        return "Result: \n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---> " +
                "Message = " + message + "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---> " +
                "Error = " + error + "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---> " +
                "Final Result = " + result + "\n";
    }
}
