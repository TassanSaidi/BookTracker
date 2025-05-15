package com.tassan.book.tracker.service.usecases;

import com.tassan.book.tracker.persistance.domain.Book;
import com.tassan.book.tracker.persistance.domain.enums.BookStatus;

import java.util.List;
import java.util.Optional;

public interface IBookUseCase {

    public Optional<Book> findBookById(Long id);

    public Book createBook(Book bookDetails);

    public List<Book> findByGenre(String genre);

    public void deleteBook(Long bookId);

    public List findBookByStatus(BookStatus status);
}
