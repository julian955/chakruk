package com.semillero.crakruk.service;

import com.semillero.crakruk.dto.NewsDto;

import java.util.List;

public interface INewsService {

    NewsDto createNews(NewsDto dto);

    NewsDto getById(Long id);

    void deleteNews(Long id);

    NewsDto updateNews(Long id, NewsDto dto);

    List<NewsDto> getAllNews();
}
