package com.pmall.user.controller;

import com.pmall.commons.result.ResponseData;
import com.pmall.commons.result.ResponseUtil;
import com.pmall.user.IMemberService;
import com.pmall.user.constants.SysRetCodeConstants;
import com.pmall.user.dto.QueryMemberRequest;
import com.pmall.user.dto.QueryMemberResponse;
import com.pmall.user.dto.UpdateMemberRequest;
import com.pmall.user.dto.UpdateMemberResponse;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class MemberController {

    @Reference(timeout = 3000)
    IMemberService memberService;

    /**
     * 根据ID查询单条会员信息
     * @param id 编号
     * @return
     */
    @GetMapping("/member/{id}")
    public ResponseData searchMemberById(@PathVariable(name = "id")long id) {
        QueryMemberRequest request=new QueryMemberRequest();
        request.setUserId(id);
        QueryMemberResponse queryMemberResponse = memberService.queryMemberById(request);
        if (!queryMemberResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return new ResponseUtil<>().setErrorMsg(queryMemberResponse.getMsg());
        }
        return new ResponseUtil<>().setData(queryMemberResponse);
    }

    /**
     * 会员信息更新
     * @return
     */
    @PutMapping("member")
    public ResponseData updateUser(@RequestBody UpdateMemberRequest request) {
        UpdateMemberResponse response = memberService.updateMember(request);
        if(response.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return new ResponseUtil().setData(null);
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }
}

