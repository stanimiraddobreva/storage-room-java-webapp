package model;

import java.time.LocalDate;

public abstract class Product implements Searchable {
    private String name;
    private LocalDate expiryDate;
    private LocalDate arrivalDate;
    private String manufacturerName;
    private MeasurementUnit measurementUnit;
    private double availableQuantity;
    private Location location;
    private String comment;

    public Product(String name, LocalDate expiryDate, LocalDate arrivalDate,
                   String manufacturerName, MeasurementUnit measurementUnit,
                   double availableQuantity, Location location,
                   String comment) {
        setName(name);
        setExpiryDate(expiryDate);
        setArrivalDate(arrivalDate);
        setManufacturerName(manufacturerName);
        setMeasurementUnit(measurementUnit);
        setAvailableQuantity(availableQuantity);
        setLocation(location);
        setComment(comment);
    }

    public Product(Product other) {
        this(other.name, other.expiryDate, other.arrivalDate,
                other.manufacturerName, other.measurementUnit,
                other.availableQuantity, new Location(other.location),
                other.comment);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null.");
        }
        this.expiryDate = expiryDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        if (arrivalDate == null) {
            throw new IllegalArgumentException("Arrival date cannot be null.");
        }
        if (this.expiryDate != null && arrivalDate.isAfter(this.expiryDate)) {
            throw new IllegalArgumentException("Arrival date cannot be after expiry date.");
        }
        this.arrivalDate = arrivalDate;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        if (manufacturerName == null || manufacturerName.isBlank()) {
            throw new IllegalArgumentException("Manufacturer name cannot be empty.");
        }
        this.manufacturerName = manufacturerName;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        if (measurementUnit == null) {
            throw new IllegalArgumentException("Measurement unit cannot be null.");
        }
        this.measurementUnit = measurementUnit;
    }

    public double getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(double availableQuantity) {
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative.");
        }
        this.availableQuantity = availableQuantity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = (comment == null) ? "" : comment;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public abstract String getCategory();
    public abstract double getStorageFee();

    @Override
    public boolean matches(String keyword) {
        return name.toLowerCase().contains(keyword.toLowerCase())
                || manufacturerName.toLowerCase().contains(keyword.toLowerCase());
    }
}