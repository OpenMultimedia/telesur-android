package net.telesurtv.www.telesur.data.api.models.news;

import com.google.gson.annotations.Expose;

/**
 * Created by Jhordan on 25/09/15.
 */
public class Book {

    @Expose
    private String author;
    @Expose
    private String title;
    private Integer year;
    private Double price;

    public Book() {
        this("camelcode.org", "Exclude properties with Gson", 1989, 49.55);
    }

    public Book(String author, String title, Integer year, Double price) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.price = price;
    }
}