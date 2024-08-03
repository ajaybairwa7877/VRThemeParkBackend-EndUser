package com.VRThemePark.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class MessageResponse {
    private String message;
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object paginationObject;
    private Boolean success;
    private int statusCode;

    //private String excelData;

    public MessageResponse(){

    }

    public MessageResponse(int statusCode, Boolean success, Object data, String message) {
        this.statusCode=statusCode;
        this.success=success;
        this.data=data;
        this.message = message;
    }

    /*Pagination Constructor */
    public MessageResponse(int statusCode, Boolean success, Object data, String message, Object pagination) {
        this.statusCode=statusCode;
        this.success=success;
        this.data=data;
        this.message = message;
        this.paginationObject=pagination;
    }

    // excel-generator
    public MessageResponse(int status, boolean success, byte[] excelBytes, String message) {
        this.statusCode = status;
        this.success = success;
        this.message = message;
        //this.excelData = Base64.getEncoder().encodeToString(excelBytes);
    }

}