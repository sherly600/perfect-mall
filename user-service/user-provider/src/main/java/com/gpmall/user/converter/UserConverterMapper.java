package com.pmall.user.converter;

import com.pmall.user.dal.entitys.Member;
import com.pmall.user.dal.entitys.User;
import com.pmall.user.dto.UserLoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserConverterMapper {

    UserConverterMapper INSTANCE= Mappers.getMapper(UserConverterMapper.class);

    @Mappings({})
    UserLoginResponse converter(Member member);

}
