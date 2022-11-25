package com.semillero.crakruk.service;



import com.semillero.crakruk.auth.model.UserModel;
import com.semillero.crakruk.dto.CommentDto;

import java.util.List;

public interface ICommentService {
    CommentDto createComment(CommentDto dto , UserModel user);

    CommentDto getById(Long id);

    void deleteComment(Long id);

    CommentDto updateComment(Long id, CommentDto dto);

    List<CommentDto> getAllComments();
}
