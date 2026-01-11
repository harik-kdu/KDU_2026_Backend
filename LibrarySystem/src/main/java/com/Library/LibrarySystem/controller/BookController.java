package com.Library.LibrarySystem.controller;

import java.util.List;
import java.util.Map;

import com.Library.LibrarySystem.dto.CreateBookRequest;
import com.Library.LibrarySystem.entity.Book;

// import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Library.LibrarySystem.services.BookService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'MEMBER')")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Starts async book processing")
    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<?> addBook(@RequestBody CreateBookRequest request) {
        try{
            Book newBook = bookService.addBook(request.getTitle());
            return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(newBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
        }
    }

    @GetMapping("/analytics/audit")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Map<String, Long>> audit() {
        return ResponseEntity.ok(bookService.audit());
    }
}
