package model;

public class Location {
    private int section;
    private int shelf;
    private int number;

    public Location(int section, int shelf, int number) {
        setSection(section);
        setShelf(shelf);
        setNumber(number);
    }

    public Location(Location other) {
        this(other.section, other.shelf, other.number);
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        if (section <= 0) {
            throw new IllegalArgumentException("Section must be a positive number.");
        }
        this.section = section;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        if (shelf <= 0) {
            throw new IllegalArgumentException("Shelf must be a positive number.");
        }
        this.shelf = shelf;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be a positive number.");
        }
        this.number = number;
    }

    @Override
    public String toString() {
        return "Section " + section + ", Shelf " + shelf + ", Number " + number;
    }
}
