package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.CarWash;
import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.repository.accounts.CarWashRepository;
import com.ezwash.backend.domain.repository.accounts.CustomerRepository;
import com.ezwash.backend.domain.repository.business.CommentRepository;
import com.ezwash.backend.domain.service.business.CommentService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
   @Autowired
   private CommentRepository commentRepository;

   @Autowired
   private CarWashRepository carWashRepository;

   @Autowired
   private CustomerRepository customerRepository;

   @Override
   public Comment postComment(Long customerId, Long carWashId, Comment comment){
      Customer customer = customerRepository.findById(customerId)
              .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));

      CarWash carWash = carWashRepository.findById(carWashId)
              .orElseThrow(() -> new ResourceNotFoundException("CarWash", "Id", carWashId));
      comment.setCarWash(carWash);
      comment.setUser(customer);

      return commentRepository.save(comment);
   }

   @Override
   public List<Comment> getCommentByCarWashId(Long carWashId){
      if(!carWashRepository.existsById(carWashId))
         throw new ResourceNotFoundException("CarWash", "Id", carWashId);
      List<Comment> commentList = commentRepository.listCommentByCarWashId(carWashId);
      if(commentList.size() == 0)
         throw new ResourceNotFoundException("CommentList", "Size", 0);
      return commentRepository.listCommentByCarWashId(carWashId);
   }

}
