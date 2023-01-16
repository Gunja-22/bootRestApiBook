package com.api.book.bootRestApiBook.dao;

import com.api.book.bootRestApiBook.entities.Books;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Books, Integer> {
   public Boolean findById(int id);
}