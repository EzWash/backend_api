package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Contract;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByCarWashId(Long carWashId);
    Comment postComment(Long customerId, Long carWashId,Long contractId, Comment comment);

}
