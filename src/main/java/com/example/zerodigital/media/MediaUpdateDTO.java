package com.example.zerodigital.media;
import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class MediaUpdateDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Description is required")
    private String synopsis;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @PositiveOrZero(message = "Rent price must be 0 or greater")
    private double rentPrice;

    @PositiveOrZero(message = "Buy price must be 0 or greater")
    private double purchasePrice;

    private String smallPoster;
    private String posterLarge;
    private boolean featured;

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

    public String getPosterSmall() {
        return smallPoster;
    }

    public void setPosterSmall(String posterSmall) {
        this.smallPoster = posterSmall;
    }

    public String getPosterLarge() {
        return posterLarge;
    }

    public void setPosterLarge(String posterLarge) {
        this.posterLarge = posterLarge;
    }

    public boolean getFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

}
