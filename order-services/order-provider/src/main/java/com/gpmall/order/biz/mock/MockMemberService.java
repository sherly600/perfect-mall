package com.pmall.order.biz.mock;

import com.pmall.user.IMemberService;
import com.pmall.user.dto.*;


public class MockMemberService implements IMemberService {
    @Override
    public QueryMemberResponse queryMemberById(QueryMemberRequest request) {
        return new QueryMemberResponse();
    }

    @Override
    public HeadImageResponse updateHeadImage(HeadImageRequest request) {
        return new HeadImageResponse();
    }

    @Override
    public UpdateMemberResponse updateMember(UpdateMemberRequest request) {
        return new UpdateMemberResponse();
    }
}
