package com.ezwash.backend.domain.service.business;

import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Contract;

public interface CommentService {
    Comment postComment(Long customerId, Long carWashId,Long contractId, Comment comment);
}
