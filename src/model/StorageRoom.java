package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class StorageRoom {
    private List<Product> products;

    public StorageRoom() {
        this.products = new ArrayList<>();
    }

    public StorageRoom(StorageRoom other) {
        this.products = new ArrayList<>();
        for (Product p : other.products) {
            // копираме според реалния тип, не просто като Product
            if (p instanceof PerishableProduct) {
                this.products.add(new PerishableProduct((PerishableProduct) p));
            } else if (p instanceof NonPerishableProduct) {
                this.products.add(new NonPerishableProduct((NonPerishableProduct) p));
            }
        }
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (findByName(product.getName()) != null) {
            throw new IllegalArgumentException("A product with this name already exists.");
        }
        products.add(product);
    }

    public boolean removeProduct(String name) {
        return products.removeIf(p -> p.getName().equals(name));
    }

    public Product findByName(String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public List<Product> search(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.matches(keyword)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> getExpiredProducts() {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.isExpired()) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> getProductsSortedByExpiryDate() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparing(Product::getExpiryDate));
        return sorted;
    }

    public List<Product> getProductsByLocation(int section) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getLocation().getSection() == section) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public double getTotalStorageFees() {
        double total = 0;
        for (Product p : products) {
            total += p.getStorageFee();
        }
        return total;
    }

    public int getProductCount() {
        return products.size();
    }
}