package com.example.springboot1amqp.bean;

import org.springframework.stereotype.Component;

public class Book {
    private String bookname;
    private String author;

    public Book(String bookname, String author) {
        this.bookname = bookname;
        this.author = author;
    }

    public Book(){

    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
