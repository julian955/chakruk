package com.semillero.crakruk.mapper;

import com.semillero.crakruk.dto.NewsDto;
import com.semillero.crakruk.mapper.util.IMapper;
import com.semillero.crakruk.model.News;
import org.springframework.stereotype.Component;

import static com.semillero.crakruk.mapper.util.MapperUtil.streamListNonNull;

import java.util.List;

@Component
public class NewsMapper implements IMapper<News, NewsDto> {
    @Override
    public News toEntity(NewsDto dto) {
        return News.builder()
                .id(dto.getId())
                .name(dto.getName())
                .content(dto.getContent())
                .image(dto.getImage())
                .deleted(false)
                .build();
    }

    @Override
    public News toEntity(Long id, NewsDto dto) {
        return News.builder()
                .id(id)
                .name(dto.getName())
                .content(dto.getContent())
                .image(dto.getImage())
                .deleted(false)
                .build();
    }

    @Override
    public NewsDto toDto(News entity) {
        return NewsDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                .build();
    }

    public void update(News entity, NewsDto dto){
        if (!(dto.getName() == null)){
            entity.setName(dto.getName());
        }
        if (!(dto.getContent() == null)){
            entity.setContent(dto.getContent());
        }
        if (!(dto.getImage() == null)){
            entity.setImage(dto.getImage());
        }

    }

    @Override
    public List<NewsDto> toDtoList(List<News> list) {
        return streamListNonNull(list, this::toDto);
    }
}
