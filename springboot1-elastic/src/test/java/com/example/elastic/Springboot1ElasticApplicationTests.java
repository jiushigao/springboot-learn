package com.example.elastic;


import com.example.elastic.bean.Article;
import com.example.elastic.bean.Book;
import com.example.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1ElasticApplicationTests {

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void test2(){
//        Book book = new Book(1,"西游记","六铁承莱");
//        bookRepository.index(book);

        Book book = bookRepository.findBookByBookNameLike("游");
        System.out.println(book);
    }

    @Test
    public void contextLoads() {
        //1.给ES索引（保存）一个文档
        Article article = new Article();
        article.setId(1);
        article.setAuthor("张三");
        article.setTitle("好运来");
        article.setContent("hello world");

        //构建一个索引
        Index build = new Index.Builder(article).index("esindex").type("news").build();

        try {
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试搜索
    @Test
    public void search(){
        //查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search build = new Search.Builder(json).addIndex("esindex").addType("news").build();

        try {
            SearchResult execute = jestClient.execute(build);
            System.out.println(execute.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
