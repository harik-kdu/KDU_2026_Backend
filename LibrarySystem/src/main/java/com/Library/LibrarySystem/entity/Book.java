package com.Library.LibrarySystem.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String status; // PROCESSING | AVAILABLE

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getStatus() {
        return status;
    }
}
