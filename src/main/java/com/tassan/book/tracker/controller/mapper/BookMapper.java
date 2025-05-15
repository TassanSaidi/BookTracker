package com.tassan.book.tracker.controller.mapper;

import com.tassan.book.tracker.controller.dto.BookDTO;
import com.tassan.book.tracker.persistance.domain.Book;

import java.util.Optional;

public class BookMapper {

    public static BookDTO toDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getId());
        bookDTO.setBookStatus(book.getBookStatus());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublishedYear(book.getPublishedYear());
        return bookDTO;
    }

    public static Book fromDTO(BookDTO bookDTO, Long bookId){
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setBookStatus(bookDTO.getBookStatus());
        book.setId(Optional.ofNullable(bookId).orElse(null));
        book.setPublishedYear(bookDTO.getPublishedYear());
        book.setTitle(bookDTO.getTitle());
        return book;
    }
}
