package com.semillero.crakruk.service.imp;

import com.semillero.crakruk.dto.NewsDto;
import com.semillero.crakruk.exeption.EntityNotFoundException;
import com.semillero.crakruk.mapper.NewsMapper;
import com.semillero.crakruk.model.News;
import com.semillero.crakruk.repository.NewsRepository;
import com.semillero.crakruk.service.INewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class NewsService implements INewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper mapper;


    @Override
    public NewsDto getById(Long id){
        News news = newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("News", "id", id));
        return mapper.toDto(news);
    }

    @Override
    public void deleteNews(Long id){

        if (!newsRepository.existsById(id)){
            throw new EntityNotFoundException("News", "id", id);
        }

        newsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public NewsDto createNews(NewsDto dto){
        News news = mapper.toEntity(dto);
        newsRepository.save(news);
        return mapper.toDto(news);
    }


    public News findById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) throw new EntityNotFoundException("News", "id", id);
        return news.get();
    }

    @Override
    public NewsDto updateNews(Long id, NewsDto dto){
        News news = this.findById(id);
        mapper.update(news,dto);
        newsRepository.save(news);
        return mapper.toDto(news);
    }

    @Override
    public List<NewsDto> getAllNews(){
        return mapper.toDtoList(newsRepository.findAll());
    }
}
