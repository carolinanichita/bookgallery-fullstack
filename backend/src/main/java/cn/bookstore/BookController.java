package cn.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    BookService service;

    public BookController(@Autowired BookService service) {
        this.service = service;
        service.fetchBook();
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllbooks(){
        List<Book> books = service.getAllBooks();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "/api/books/");
        responseHeaders.add("content-type", "application/json");
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        Book book = service.getBookById(id);
        if (book == null) {
            ResponseEntity.notFound().build();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "/api/books/" + book.getId());
        responseHeaders.add("content-type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(book);
    }

    @DeleteMapping("/{id}")
 public ResponseEntity<Book> deleteBook(@PathVariable UUID id) {
        boolean deleted = service.removeBook(id);

        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/favorites")
    public ResponseEntity<String> addBookToFavorites(@PathVariable("id") UUID bookId)
    {
        service.addBookToFavorites(bookId);
        return ResponseEntity.ok("Book added to favorites");
    }

    @GetMapping
    @RequestMapping("/favorites")
    public ResponseEntity<List<Book>> getAllFavoriteBooks(){
        List<Book> books = service.getAllFavoriteBooks();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("location", "/api/books/favorites");
        responseHeaders.add("content-type", "application/json");
        return ResponseEntity.ok().body(books);
    }

    @DeleteMapping(path="/favorites/delete/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable UUID bookId) {
        service.deleteBookFromFavorite(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
