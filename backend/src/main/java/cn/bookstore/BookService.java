package cn.bookstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    BookRepository bookRepository;
    FavoriteBooksRepository favoriteBooksRepository;

    public BookService(@Autowired BookRepository bookRepository, @Autowired FavoriteBooksRepository favoriteBooksRepository) {
        this.bookRepository = bookRepository;
        this.favoriteBooksRepository = favoriteBooksRepository;
    }

    String searchQuery = "love+history";
    String API_KEY = "";
    private String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + searchQuery + "&key=" + API_KEY;

    public void fetchBook() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(entity.getBody());
            if (node.has("items")) {
                node.get("items").forEach(item -> {
                    Book book = new Book();
                    item.get("volumeInfo").ifPresent(volumeInfo -> {
                        volumeInfo.get("title").ifPresent(title -> book.setTitle(title.asText()));
                        volumeInfo.get("imageLinks").ifPresent(imageLinks -> {
                            imageLinks.get("thumbnail").ifPresent(thumbnail -> book.setImageURL(thumbnail.asText()));
                        });
                        volumeInfo.get("authors").ifPresent(authors -> {
                            List<String> authorList = authors.elements().stream().map(JsonNode::asText).collect(Collectors.toList());
                            book.setAuthors(authorList);
                        });
                        volumeInfo.get("description").ifPresent(description -> book.setDescription(description.asText()));
                    });
                    item.get("saleInfo").ifPresent(saleInfo -> {
                        saleInfo.get("listPrice").ifPresent(listPrice -> {
                            listPrice.get("amount").ifPresent(amount -> book.setPrice(amount.asDouble()));
                        });
                    });
                    bookRepository.addBook(book);
                });
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Book getBookById(UUID id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.listBooks();
    }

    public boolean removeBook(UUID id) {
        return bookRepository.removeBook(id);
    }

    public void addBookToFavorites(UUID bookId) {
        Book book = bookRepository.getBookById(bookId);

        favoriteBooksRepository.addBookToFavoriteList(book);
    }

    public List<Book> getAllFavoriteBooks() {
        return favoriteBooksRepository.getAllFavoriteBooks();
    }

    public boolean deleteBookFromFavorite(UUID id) {
        return favoriteBooksRepository.deleteBookFromFavorite(id);
    }
}
