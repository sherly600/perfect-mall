package com.pmall.comment.convert;

import com.pmall.comment.dal.entitys.Comment;
import com.pmall.comment.dal.entitys.CommentReply;
import com.pmall.comment.dto.CommentDto;
import com.pmall.comment.dto.CommentReplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentConverter {

    @Mappings({})
    CommentDto comment2Dto(Comment comment);

    List<CommentDto> comment2Dto(List<Comment> commentList);

    CommentReplyDto commentReply2Dto(CommentReply commentReply);

    List<CommentReplyDto> commentReply2Dto(List<CommentReply> commentReplyList);
}
