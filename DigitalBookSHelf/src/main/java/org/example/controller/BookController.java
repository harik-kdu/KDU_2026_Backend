package org.example.controller;

import org.example.entity.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<List<Book>> getAllBooks(
        @RequestParam(name="author", required = false, defaultValue = "") String author,
        @RequestParam(name="sort", required = false, defaultValue = "asc") String sort,
        @RequestParam(name="pages", required = false, defaultValue = "1")  int page
        ) 
    {
       
        List<Book> result = new ArrayList<>(bookshelf.values());

        if(!author.isEmpty()){
            result = result.stream().filter((b)->
            b.getAuthor().toLowerCase().equals(author.toLowerCase())
        ).toList();
        }

        if (sort.equalsIgnoreCase("desc")) {
            result = result.stream()
                    .sorted(Comparator.comparing(Book::getTitle).reversed())
                    .toList();
        } 
        else {
            result = result.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .toList();
        }

        int pageSize = 10;
        int start = (page - 1) * pageSize;

        if (start >= result.size()) {
            return ResponseEntity.ok(List.of());
        }

        int end = Math.min(start + pageSize, result.size());
        List<Book> pagedResult = result.subList(start, end);

        return ResponseEntity.ok(pagedResult);
         
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


    @GetMapping("/{id}")
    public EntityModel<Book> getBookById(@PathVariable Long id){
        
        Book book = bookshelf.get(id);
        if (book == null) {
            throw new NoSuchElementException("Book not found");
        }

        return EntityModel.of(
                book,
                linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks("", "asc", 1)).withRel("all-books")
        );
    }
}
