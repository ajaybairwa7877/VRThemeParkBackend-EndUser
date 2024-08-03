package com.VRThemePark.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ErrorMessage {
    private Boolean status;
    private int exceptionCode;
    @JsonInclude(Include.NON_NULL)
    private String developerMessage;
    @JsonInclude(Include.NON_NULL)
    private String userErrorMessage;

    @JsonInclude()
    private Object data;

    public ErrorMessage(int i, String s, String s1) {
    }

    public ErrorMessage(Boolean status, int exceptionCode, String developerMessage, String userErrorMessage) {
        this.status = status;
        this.exceptionCode = exceptionCode;
        this.developerMessage = developerMessage;
        this.userErrorMessage = userErrorMessage;
    }

    public ErrorMessage(Boolean status, int exceptionCode, String developerMessage, String userErrorMessage,Object data) {
        this.status = status;
        this.exceptionCode = exceptionCode;
        this.developerMessage = developerMessage;
        this.userErrorMessage = userErrorMessage;
        this.data=data;
    }


//    public ErrorMessage(int status, int scUnauthorized, String message, String message1) {
//    }


    //private List<String> vaildationErrorList;
}