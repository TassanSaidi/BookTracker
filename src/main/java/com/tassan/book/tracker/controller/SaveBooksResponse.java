package com.tassan.book.tracker.controller;

import com.tassan.book.tracker.controller.dto.BookDTO;
import com.tassan.book.tracker.controller.dto.FailedBook;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class SaveBooksResponse {

        private List<BookDTO> saved;
        private List<FailedBook> failed;
}
