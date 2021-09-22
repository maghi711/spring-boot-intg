package com.boot.app.controller;

import com.boot.app.dao.BookDAO;
import com.boot.app.model.Book;
import com.boot.app.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDAO bookDao;

    @GetMapping(path = "/", produces = "application/json")
    public Books getAllBooks() {
        return bookDao.getAllBooks();
    }

    @GetMapping(path = "/info", produces = "application/json")
    public Book info() {
        return bookDao.info();
    }
}
