package com.perfectu.pay.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

/**
 *
 * @author sherly
 */
@Data
public class PaymentResponse extends AbstractResponse{
    private static final long serialVersionUID = 436341660723282981L;
    /**构建html表单*/
    private String htmlStr;
    /**微信支付下单的返回id*/
    private String prepayId;
    /**微信支付下单构建的二维码地址*/
    private String codeUrl; 
}
