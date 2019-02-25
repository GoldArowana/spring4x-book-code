package com.smart.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
public class User implements Serializable {
    private int userId;
    private String userName;
    private String password;
    private int credits;
    private String lastIp;
    private Date lastVisit;
}
