package com.Library.LibrarySystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Library.LibrarySystem.entity.Book;
import com.Library.LibrarySystem.repository.BooksRepositoy;

@Service
public class BookService {
    private final BooksRepositoy booksRepository;
    private final ExecutorService executorService;

    public BookService(BooksRepositoy booksRepository, ExecutorService executorService) {
        this.booksRepository = booksRepository;
        this.executorService = executorService;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(booksRepository.getAllBooks().values());
    }

    public Book addBook(String title) {

        Book book = booksRepository.addBook(title, "PROCESSING");

        executorService.submit(() -> {
            try {
                Thread.sleep(3000); // Simulating processing time
                booksRepository.updateBookStatus(book.getId(), "AVAILABLE");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        return book;
    }

    public Map<String, Long> audit() {
        return booksRepository.getAllBooks()
            .values()
            .stream()
            .collect(Collectors.groupingBy(Book::getStatus, Collectors.counting()));
    }
    
}
