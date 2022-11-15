package com.semillero.crakruk.mapper;

import com.semillero.crakruk.auth.model.UserModel;
import com.semillero.crakruk.auth.repository.UserRepository;
import com.semillero.crakruk.dto.CommentDto;
import com.semillero.crakruk.mapper.util.IMapper;
import com.semillero.crakruk.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.semillero.crakruk.mapper.util.MapperUtil.streamListNonNull;

@Component
public class CommentMapper implements IMapper<Comment, CommentDto> {



    public Comment toEntity(CommentDto dto, UserModel user) {
        return Comment.builder()
                .id(dto.getId())
                .user(user)
                .body(dto.getBody())
                .deleted(false)
                .build();
    }

    @Override
    public Comment toEntity(CommentDto dto) {
        return Comment.builder()
                .id(dto.getId())
                .body(dto.getBody())
                .deleted(false)
                .build();
    }

    @Override
    public Comment toEntity(Long id, CommentDto dto) {
        return Comment.builder()
                .id(id)
                .body(dto.getBody())
                .build();
    }

    @Override
    public CommentDto toDto(Comment entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .userName(entity.getUser().getUser())
                .body(entity.getBody())
                .build();
    }

    public void update(Comment entity,CommentDto dto){
        if(!(dto.getBody() == null)){
            entity.setBody(dto.getBody());
        }
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> list) {
        return streamListNonNull(list, this::toDto);
    }
}
