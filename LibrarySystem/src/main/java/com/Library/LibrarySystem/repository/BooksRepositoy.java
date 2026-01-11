package com.Library.LibrarySystem.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.Library.LibrarySystem.entity.Book;

@Repository
public class BooksRepositoy {
    private static Long idCounter = 0L;
    private final Map<Long, Book> books = new HashMap<>();

    public BooksRepositoy () {
        // Initializing with some books
        books.put(++idCounter, new Book(idCounter, "Book 1", "PROCESSING")); 
        books.put(++idCounter, new Book(idCounter, "Book 2", "PROCESSING"));
        books.put(++idCounter, new Book(idCounter, "Book 3", "AVAILABLE"));
    }

    public Map<Long, Book> getAllBooks() {
        return books;
    }
    
    public void updateBookStatus(Long id, String status) {
        Book book = books.get(id);
        if (book != null) {
            books.put(id, new Book(book.getId(), book.getTitle(), status));
        }
    }

    public Book addBook(String title, String status) {
        //Handling duplicate titles
        if(books.values().stream().anyMatch(book -> book.getTitle().equalsIgnoreCase(title))) {
            throw new IllegalArgumentException("Book with the same title already exists.");
        }
        Book newBook = new Book(++idCounter, title, status);
        books.put(idCounter, newBook);
        return newBook;
    }
    
}
