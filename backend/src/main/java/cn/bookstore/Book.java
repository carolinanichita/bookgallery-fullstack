package cn.bookstore;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class Book {
    private UUID id;
    String imageURL;
    String title;
    List<String> authors;
    String description;
    double price;

    public Book() {
        this.id = UUID.randomUUID();
    }

    public Book(UUID id, String imageURL, String title, List<String> authors, String description, double price) {
        this.id = id;
        this.imageURL = imageURL;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
