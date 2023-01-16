package com.api.book.bootRestApiBook.controllers;

import com.api.book.bootRestApiBook.entities.Books;
import com.api.book.bootRestApiBook.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookServices bookServices;

    //get all books
    @GetMapping("/books")
    public ResponseEntity<List<Books>> getBooks(){
        List<Books> list = this.bookServices.getAllBooks();
        if(list.size()==0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    //get book by id
    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") int id)
    {
        Boolean books = bookServices.getBookById(id);
        if(books==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(books));
    }

    //add book
    @PostMapping("/books")
    public ResponseEntity<Optional<Books>> addBook(@RequestBody Books books)
    {
        Books b = null;
        try
        {
            b = this.bookServices.addBook(books);
            System.out.println(books);
            return ResponseEntity.ok(Optional.of(b));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //delete book by id
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId)
    {
        try
        {
            this.bookServices.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update book handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Books> updateBook(@RequestBody Books book, @PathVariable("bookId") int bookId)
    {
        try{
            this.bookServices.updateBook(book, bookId);
            return ResponseEntity.ok().body(book);
        }catch(Exception e){
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}