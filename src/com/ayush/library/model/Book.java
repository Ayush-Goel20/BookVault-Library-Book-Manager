package com.ayush.library.model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String genre;
    private int year;
    private boolean available;

    // Constructor
    public Book(int id, String title, String author, String genre, int year, boolean available) {
        this.id        = id;
        this.title     = title;
        this.author    = author;
        this.genre     = genre;
        this.year      = year;
        this.available = available;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public int     getId()        { return id; }
    public String  getTitle()     { return title; }
    public String  getAuthor()    { return author; }
    public String  getGenre()     { return genre; }
    public int     getYear()      { return year; }
    public boolean isAvailable()  { return available; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setAvailable(boolean available) { this.available = available; }

    // ── Convert book to one CSV line for file storage ─────────────────────
    // Format:  id,title,author,genre,year,available
    public String toCsv() {
        return id + "," + title + "," + author + "," + genre + "," + year + "," + available;
    }

    // ── Rebuild a Book object from a CSV line ─────────────────────────────
    public static Book fromCsv(String csvLine) {
        String[] parts = csvLine.split(",", 6);   // max 6 parts
        int     id        = Integer.parseInt(parts[0].trim());
        String  title     = parts[1].trim();
        String  author    = parts[2].trim();
        String  genre     = parts[3].trim();
        int     year      = Integer.parseInt(parts[4].trim());
        boolean available = Boolean.parseBoolean(parts[5].trim());
        return new Book(id, title, author, genre, year, available);
    }

    // ── Pretty print ─────────────────────────────────────────────────────
    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";
        return String.format("[%d] %-35s | %-20s | %-15s | %d | %s",
                id, title, author, genre, year, status);
    }
}
