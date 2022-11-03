package com.pmall.user.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;


@Data
public class AddressDetailResponse extends AbstractResponse {
	private AddressDto addressDto;
    
}
