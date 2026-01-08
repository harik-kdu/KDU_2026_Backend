package org.example.controller;

import org.example.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/books")
public class BookController {

    private final Map<Long, Book> bookshelf = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Long id = idGenerator.getAndIncrement();
        book.setId(id);
        bookshelf.put(id, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(new ArrayList<>(bookshelf.values()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {

        if (!bookshelf.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        book.setId(id);
        bookshelf.put(id, book);
        
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookshelf.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        bookshelf.remove(id);
        return ResponseEntity.noContent().build();  
    }
}
