package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.business.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    public List<Comment> getCommentsByCarWashId(Long carWashId);
}
