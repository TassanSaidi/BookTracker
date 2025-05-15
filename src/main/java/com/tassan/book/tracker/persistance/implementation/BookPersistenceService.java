package com.tassan.book.tracker.persistance.implementation;

import com.tassan.book.tracker.persistance.domain.Book;
import com.tassan.book.tracker.persistance.domain.enums.BookStatus;
import com.tassan.book.tracker.persistance.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookPersistenceService {

    private final IBookRepository bookRepository;

    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }

    public Optional<Book> findBookByTitle(String title) {
        return Optional.ofNullable(bookRepository.findByTitle(title));
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findBookByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByBookStatus(BookStatus bookStatus) {
        return bookRepository.findByBookStatus(bookStatus);
    }

    public List<Book> findAllBooks(){
        return  bookRepository.findAll();
    }

}
