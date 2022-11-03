package com.pmall.user.registerVerification;

import com.pmall.user.dal.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * sherly
 * 18:31
 * 自定义的生产者工厂，也可以不要这个类
 */
public class KafKaRegisterSuccProducerFactory extends DefaultKafkaProducerFactory {
    public KafKaRegisterSuccProducerFactory(Map confings) {
        super(confings);
    }
}
