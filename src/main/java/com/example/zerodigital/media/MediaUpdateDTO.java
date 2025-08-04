package com.example.zerodigital.media;

import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.*;

public class MediaUpdateDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Type is required")
    private String type;

    @NotNull
    @Min(1800)
    @Max(2028)
    private int year;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Description is required")
    private String synopsis;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @PositiveOrZero(message = "Rent price must be 0 or greater")
    private double rentPrice;

    @PositiveOrZero(message = "Buy price must be 0 or greater")
    private double purchasePrice;

    private String smallPoster;
    private String poster;
    private boolean featured;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String description) {
        this.synopsis = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setPurchasePrice(double buyPrice) {
        this.purchasePrice = buyPrice;
    }

    public String getSmallPoster() {
        return smallPoster;
    }

    public void setSmallPoster(String posterSmall) {
        this.smallPoster = posterSmall;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public boolean getFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

}
