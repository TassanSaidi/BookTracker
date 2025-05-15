package com.tassan.book.tracker.controller.dto;


import com.tassan.book.tracker.persistance.domain.enums.BookStatus;
import lombok.Data;


@Data
public class BookDTO {
    private Long bookId;
    private String title;
    private String author;
    private Integer publishedYear;
    private BookStatus bookStatus;
}

