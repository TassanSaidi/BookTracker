package com.tassan.book.tracker.service;


import com.tassan.book.tracker.persistance.domain.Book;
import com.tassan.book.tracker.persistance.domain.enums.BookStatus;
import com.tassan.book.tracker.persistance.implementation.BookPersistenceService;
import com.tassan.book.tracker.service.usecases.IBookUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookUseCase {

    private final BookPersistenceService bookPersistenceService;

    @Autowired
    private BookService(BookPersistenceService bookPersistenceService) {
        this.bookPersistenceService = bookPersistenceService;
    }

    public Optional<Book> findBookById(Long id) {
        return bookPersistenceService.findById(id);
    }

    @Override
    public Book createBook(Book bookDetails) {
        return bookPersistenceService.saveBook(bookDetails);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return bookPersistenceService.findBookByGenre(genre);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookPersistenceService.deleteBook(bookId);
    }

    @Override
    public List findBookByStatus(BookStatus status) {
        return bookPersistenceService.findByBookStatus(status);
    }
}
