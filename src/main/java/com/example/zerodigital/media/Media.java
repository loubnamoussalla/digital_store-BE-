package com.example.zerodigital.media;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "media")
public class Media {
    @Id
    private String id;
    private String title;
    private int year;
    private String smallPoster;
    private String poster;
    private double rate;
    private String genre;
    private String synopsis;
    private double rentPrice;
    private double purchasePrice;
    private String pick;
    private String type;
    private boolean featured;
    private double price;

    public Media(String id, String title, int year, String smallPoster, String poster, double rate, String genre, String synopsis, double rentPrice, double purchasePrice, String pick, String type, boolean featured, double price) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.smallPoster = smallPoster;
        this.poster = poster;
        this.rate = rate;
        this.genre = genre;
        this.synopsis = synopsis;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
        this.pick = pick;
        this.type = type;
        this.featured = featured;
        this.price = price;
    }

    public Media() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSmallPoster() {
        return smallPoster;
    }

    public void setSmallPoster(String smallPoster) {
        this.smallPoster = smallPoster;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
