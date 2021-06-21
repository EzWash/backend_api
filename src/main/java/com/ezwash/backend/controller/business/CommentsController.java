package com.ezwash.backend.controller.business;

import com.ezwash.backend.domain.model.accounts.Staff;
import com.ezwash.backend.domain.model.business.Comment;
import com.ezwash.backend.domain.model.business.Report;
import com.ezwash.backend.domain.service.business.CommentService;
import com.ezwash.backend.resource.business.CommentResource;
import com.ezwash.backend.resource.business.ContractResource;
import com.ezwash.backend.resource.business.ReportResource;
import com.ezwash.backend.resource.business.SaveCommentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentsController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Create Comments", description = "Create and return a Comment", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment created successfully", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/customers/{customerId}/carwashes/{carWashId}/contract/{contractId}/comments")
    public CommentResource postComment(@PathVariable Long customerId, @PathVariable Long carWashId,
                                       @PathVariable Long contractId, @Valid @RequestBody  SaveCommentResource resource){
        Comment comment = convertToEntity(resource);
        return convertToResource(commentService.postComment(customerId, carWashId, contractId, comment));
    }
    @Operation(summary = "Get Car Wash's comments", description = "Get a comment given the Car Wash ID", tags = {"CarWashes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments obtained successfully", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/carwashes/{carWashId}/comments")
    public List<CommentResource> getCommentByCarWashId(@PathVariable Long carWashId){
        List<Comment>commentList = commentService.getCommentByCarWashId(carWashId);
        List<CommentResource> commentResourceList = commentList
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return commentResourceList;
    }


    private Comment convertToEntity(SaveCommentResource resource){
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment comment){
        return mapper.map(comment, CommentResource.class);
    }
}
