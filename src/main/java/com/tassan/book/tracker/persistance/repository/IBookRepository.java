package com.tassan.book.tracker.persistance.repository;

import com.tassan.book.tracker.persistance.domain.Book;
import com.tassan.book.tracker.persistance.domain.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(String genre);

    List<Book> findByBookStatus(BookStatus bookStatus);
}
