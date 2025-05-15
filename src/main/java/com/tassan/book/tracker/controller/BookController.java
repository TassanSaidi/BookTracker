package com.tassan.book.tracker.controller;


import com.tassan.book.tracker.controller.dto.BookDTO;
import com.tassan.book.tracker.controller.dto.FailedBook;
import com.tassan.book.tracker.controller.mapper.BookMapper;
import com.tassan.book.tracker.persistance.domain.Book;
import com.tassan.book.tracker.persistance.domain.enums.BookStatus;
import com.tassan.book.tracker.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/api/books")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        try {
            List<BookDTO> books = bookService.findAllBooks().stream().map(BookMapper::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            log.error("Error retrieving books");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@PathVariable(name = "genre") String genre) {
        try {
            List<BookDTO> books = bookService.findByGenre(genre).stream().map(BookMapper::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            log.error("Error retrieving books");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/status/{bookStatus}")
    public ResponseEntity<List<BookDTO>> getBooksByStatus(@PathVariable(name = "bookStatus") String bookStatus) {
        try {
            List<BookDTO> books = bookService.findBookByStatus(BookStatus.valueOf(bookStatus)).stream().map(BookMapper::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            log.error("Error retrieving books");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @DeleteMapping("/bookId")
    public ResponseEntity.BodyBuilder deleteBook(@PathVariable(name = "bookId") String bookId) {
        try {
            bookService.deleteBook(Long.valueOf(bookId));
            return ResponseEntity.accepted();
        } catch (Exception e) {
            log.error("Error deleting book: {}", bookId);
            return ResponseEntity.internalServerError();
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> findBook(@PathVariable("bookId") String bookId) {
        try {
            Long id = Long.parseLong(bookId);
            return bookService.findBookById(id).map(book -> ResponseEntity.ok(BookMapper.toDTO(book))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build(); // Invalid ID format
        } catch (Exception e) {
            log.error("Failed to retrieve book with id {}", bookId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<SaveBooksResponse> saveBooks(@RequestBody List<BookDTO> bookDTOList) {
        List<BookDTO> saved = new ArrayList<>();
        List<FailedBook> failed = new ArrayList<>();

        for (BookDTO dto : bookDTOList) {
            try {
                Book savedBook = bookService.createBook(BookMapper.fromDTO(dto,null));
                saved.add(BookMapper.toDTO(savedBook));
            } catch (Exception ex) {
                failed.add(new FailedBook(dto, ex.getMessage()));
            }
        }

        SaveBooksResponse response = new SaveBooksResponse();
        response.setSaved(saved);
        response.setFailed(failed);

        return ResponseEntity.ok(response);
    }


}
