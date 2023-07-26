package cn.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {
    List<Book> bookList = new ArrayList<>();

    private static final Map<UUID, Book> bookRepository = new HashMap<>();
    public boolean removeBook(UUID id) {
        bookRepository.remove(id);
        return !bookRepository.containsKey(id);
    }
    public Book getBookById(UUID bookID) {
        return bookRepository.get(bookID);
    }
    public Book getBookByTitle(String title) {
        return bookRepository.get(title);
    }

    public List<Book> listBooks(){
        return new ArrayList<>(bookRepository.values());
    }

    public void addBook(Book newBook) {

        bookList.add(newBook);
        bookRepository.put(newBook.getId(), newBook);
    }
}
