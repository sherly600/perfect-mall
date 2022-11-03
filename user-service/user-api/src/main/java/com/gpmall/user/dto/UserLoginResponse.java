package com.pmall.user.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

/**
 * sherly
 * create-date: 2019/7/22-13:10
 */
@Data
public class UserLoginResponse extends AbstractResponse {

    private Long id;
    private String username;
    private String phone;
    private String email;
    private String sex;
    private String address;
    private String file;
    private String description;
    private Integer points;
    private Long balance;
    private int state;
    private String token;
}
