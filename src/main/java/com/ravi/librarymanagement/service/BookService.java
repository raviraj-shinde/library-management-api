package com.ravi.librarymanagement.service;

import com.ravi.librarymanagement.DTO.BookDTO;
import com.ravi.librarymanagement.model.Book;
import com.ravi.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return book;
    }

    //ADMIN Book Services
    public Book addBook(BookDTO bookDTO) {
        Book book = convertTOBook(bookDTO);
        return bookRepo.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book oldBook = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Book updatedBook = convertTOBook(bookDTO);
        updatedBook.setId(oldBook.getId());

        return bookRepo.save(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }


    //POJO methods
    public Book convertTOBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setQuantity(bookDTO.getQuantity());
        book.setIsAvailabe(bookDTO.getIsAvailabe());
        return book;
    }

    public BookDTO convertToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setQuantity(book.getQuantity());
        bookDTO.setIsAvailabe(book.getIsAvailabe());
        return bookDTO;
    }
}
