package com.omsai.spring_7_webapp.service;

import com.omsai.spring_7_webapp.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();
}
