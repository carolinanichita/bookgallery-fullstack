package cn.bookstore;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record BookDTO( String imageURL, String title, List<String> authors, String description, double price, UUID id) {
}
