package com.pmall.user.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;


@Data
public class AddressListResponse extends AbstractResponse {

    private List<AddressDto> addressDtos;
}
