package com.omsai.spring_7_webapp.repositories;

import com.omsai.spring_7_webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
