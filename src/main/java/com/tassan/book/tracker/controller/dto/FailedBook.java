package com.tassan.book.tracker.controller.dto;


import lombok.Data;


@Data
public class FailedBook {
    private BookDTO book;
    private String reason;

    public FailedBook(BookDTO book, String reason) {
        this.book = book;
        this.reason = reason;
    }
}
