package com.api.book.bootRestApiBook.services;

import com.api.book.bootRestApiBook.dao.BookRepository;
import com.api.book.bootRestApiBook.entities.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//commented lines are code written before sql connection.
//new codes are to connect data to sql.
@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

//    private static List<Books> list = new ArrayList<>();
//
//    static {
//        list.add(new Books(1, "Harry Potter and the Half Blood Prince", "J.K.Rowling"));
//        list.add(new Books(2, "Harry Potter and Deathly Hallows", "J.K.Rowling"));
//        list.add(new Books(3, "Harry Potter and the Goblet of Fire", "J.K.Rowling"));
//    }

    //get all books
    public List<Books> getAllBooks()
    {
        List<Books> list = (List<Books>)this.bookRepository.findAll();
        return list;
    }

    //get single book by id
    public Boolean getBookById(int id) {
        Boolean books = null;
        try {
//            books = list.stream().filter(e -> (e.getId() == id)).findFirst().get();
            books=this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    //create data of book
    public Books addBook(Books b)
    {
        Books result=bookRepository.save(b);
        return result;
    }

    //delete a book
    public void deleteBook(int bid)
    {
//       list = list.stream().filter(books -> {
//           if(books.getId()!=bid) return true;
//           else return false;
//       }).collect(Collectors.toList());
        bookRepository.deleteById(bid);
    }

    //update book handler
    public void updateBook(Books book, int bookId)
    {
//        list = list.stream().map(b -> {
//            if(b.getId()==bookId)
//            {
//                b.setTitle(book.getTitle());
//                b.setAuthor(book.getAuthor());
//            }
//            return b;
//        }).collect(Collectors.toList());

        book.setId(bookId);
        bookRepository.save(book);
    }
}