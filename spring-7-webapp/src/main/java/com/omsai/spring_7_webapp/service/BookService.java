package com.omsai.spring_7_webapp.service;

import com.omsai.spring_7_webapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
