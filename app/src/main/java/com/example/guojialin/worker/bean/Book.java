package com.example.guojialin.worker.bean;

public class Book {

    /**
     * author : 大师兄
     * id : 1001
     * name : 数据结构
     * publisher : fzt大师兄
     */

    private String author;
    private int id;
    private String name;
    private String publisher;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "作者='" + author + '\'' +
                ", 编号=" + id +
                ", 书名='" + name + '\'' +
                ", 出版社='" + publisher + '\'' +
                '}';
    }
}
