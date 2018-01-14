package com.avasilyev.sprboot.repository;

import com.avasilyev.sprboot.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
