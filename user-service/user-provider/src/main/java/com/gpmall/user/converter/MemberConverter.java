package com.pmall.user.converter;/**
 * Created by mic on 2019/7/31.
 */

import com.pmall.user.dal.entitys.Member;
import com.pmall.user.dto.QueryMemberResponse;
import com.pmall.user.dto.UpdateMemberRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface MemberConverter {

    @Mappings({})
    QueryMemberResponse member2Res(Member member);

    @Mappings({})
    Member updateReq2Member(UpdateMemberRequest request);
}
