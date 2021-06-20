package com.ezwash.backend.domain.repository.business;

import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("select c from Comment c where carwash_id= :id")
    public List<Comment> listCommentByCarWashId(@Param("id") Long id);

}
