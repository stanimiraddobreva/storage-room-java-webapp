package model;

import java.time.LocalDate;

public class NonPerishableProduct extends Product {

    public NonPerishableProduct(String name, LocalDate expiryDate, LocalDate arrivalDate,
                                String manufacturerName, MeasurementUnit measurementUnit,
                                double availableQuantity, Location location,
                                String comment) {
        super(name, expiryDate, arrivalDate, manufacturerName, measurementUnit,
                availableQuantity, location, comment);
    }

    public NonPerishableProduct(NonPerishableProduct other) {
        super(other);
    }

    @Override
    public String getCategory() {
        return "Non-Perishable";
    }

    @Override
    public double getStorageFee() {
        return getAvailableQuantity() * 0.02;
    }

    @Override
    public String toString() {
        return getName() + " (Non-Perishable)";
    }
}
