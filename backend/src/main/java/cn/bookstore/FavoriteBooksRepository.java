package cn.bookstore;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FavoriteBooksRepository {
    private static final Map<UUID, Book> favoriteBookRepository = new HashMap<>();

    public List<Book> getAllFavoriteBooks() {

        return favoriteBookRepository.values().stream().toList();
    }

    public void addBookToFavoriteList(Book book) {
        favoriteBookRepository.put(book.getId(), book);
    }

    public boolean deleteBookFromFavorite(UUID id) {
        if (!favoriteBookRepository.containsKey(id)) {
            return false;
        }
        favoriteBookRepository.remove(id);
        return true;
    }
}

