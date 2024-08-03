package com.VRThemePark.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class UserActionActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String ipAddress;
    private String city;
    private String latitude;
    private String longitude;
    private String action;
    private String requestBody;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
    private Integer createdBy;
    private String type;
    private String requestId;
    private Integer status;
    private String email;
    private String lcTraceId;
    private long startTime;
    private long endTime;
    private Float totalExecutionTime;
    private String responseBody;

}
