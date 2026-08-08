package com.omsai.spring_7_webapp.repositories;

import com.omsai.spring_7_webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
