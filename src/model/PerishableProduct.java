package model;

import java.time.LocalDate;

public class PerishableProduct extends Product {
    private int shelfLifeDays;

    public PerishableProduct(String name, LocalDate expiryDate, LocalDate arrivalDate,
                             String manufacturerName, MeasurementUnit measurementUnit,
                             double availableQuantity, Location location,
                             String comment, int shelfLifeDays) {
        super(name, expiryDate, arrivalDate, manufacturerName, measurementUnit,
                availableQuantity, location, comment);
        setShelfLifeDays(shelfLifeDays);
    }

    public PerishableProduct(PerishableProduct other) {
        super(other);
        this.shelfLifeDays = other.shelfLifeDays;
    }

    public int getShelfLifeDays() {
        return shelfLifeDays;
    }

    public void setShelfLifeDays(int shelfLifeDays) {
        if (shelfLifeDays < 0) {
            throw new IllegalArgumentException("Shelf life cannot be negative.");
        }
        this.shelfLifeDays = shelfLifeDays;
    }

    @Override
    public String getCategory() {
        return "Perishable";
    }

    @Override
    public double getStorageFee() {
        return getAvailableQuantity() * 0.05;
    }

    @Override
    public String toString() {
        return getName() + " (Perishable, shelf life: " + shelfLifeDays + " days)";
    }
}
