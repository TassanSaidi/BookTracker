package com.tassan.book.tracker.persistance.domain;


import com.tassan.book.tracker.persistance.domain.enums.BookStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "Books")
@Data
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private String genre;

    @Column(name = "published_year")
    private Integer publishedYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus bookStatus;
}
