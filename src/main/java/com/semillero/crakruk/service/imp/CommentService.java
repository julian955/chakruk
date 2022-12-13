package com.semillero.crakruk.service.imp;

import com.semillero.crakruk.auth.model.UserModel;
import com.semillero.crakruk.auth.repository.UserRepository;
import com.semillero.crakruk.auth.service.IUserService;
import com.semillero.crakruk.dto.CommentDto;
import com.semillero.crakruk.dto.ReplyDto;
import com.semillero.crakruk.exeption.EntityNotFoundException;
import com.semillero.crakruk.mapper.CommentMapper;
import com.semillero.crakruk.model.Comment;
import com.semillero.crakruk.repository.CommentRepository;
import com.semillero.crakruk.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    private final IUserService userService;



    @Override
    @Transactional
    public CommentDto createComment(CommentDto dto , HttpServletRequest request) {

        UserModel user = userService.getUser(request);
        Comment comment = mapper.toEntity(dto,user);
        commentRepository.save(comment);
        return mapper.toDto(comment);
    }

    @Override
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment", "id", id));
        return mapper.toDto(comment);
    }

    @Override
    public void deleteComment(Long id) {

        if (!commentRepository.existsById(id)){
            throw new EntityNotFoundException("Comment", "id", id);
        }
        commentRepository.deleteById(id);
    }

    public Comment findById(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty())throw new EntityNotFoundException("Comment", "id", id);
        return comment.get();
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto dto) {
        Comment comment = this.findById(id);
        mapper.update(comment,dto);
        commentRepository.save(comment);
        return mapper.toDto(comment);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<CommentDto> dtoList = mapper.toDtoList(commentRepository.findAll());
        dtoList.forEach(x -> Collections.sort(x.getReply(), Comparator.comparing(ReplyDto::getCreated)));
        Collections.sort(dtoList, Comparator.comparing(CommentDto::getCreated).reversed());
        return dtoList;
    }
}
