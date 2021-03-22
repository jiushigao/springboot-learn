package com.example.elastic.repository;

import com.example.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

    Book findBookByBookNameLike(String bookName);
}
