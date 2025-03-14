package com.example.TextVoid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fourchan")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull(message="is required")
    @Size(min = 1, message = "is required")
    @Column(name="message")
    private String message;

    @Column(name="ip_address")
    private String ipAddress;

    @Column(name="genre")
    private String genre;

    @Column(name="date")
    private String date;

    public Post(){}

    public Post(String message, String ipAddress, String genre, String date) {
        this.message = message;
        this.ipAddress = ipAddress;
        this.genre = genre;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

