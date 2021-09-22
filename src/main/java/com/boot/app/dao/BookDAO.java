package com.boot.app.dao;

import com.boot.app.model.Book;
import com.boot.app.model.Books;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BookDAO {

    public Books getAllBooks() {
        return new Books(Arrays.asList(
                new Book("Head First Java", "Kathy Sierra"),
                new Book("Core Java Volume 1", "Cay Horstmann")
        ));
    }

    public Book info() {
        return new Book("Head First Java", "Kathy Sierra");
    }
}
