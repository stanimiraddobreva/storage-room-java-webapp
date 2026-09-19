package model;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.List;

public class StorageRoomServer {

    public static void main(String[] args) throws Exception {
        StorageRoom storageRoom = new StorageRoom();

        storageRoom.addProduct(new PerishableProduct("Rice", LocalDate.of(2027, 1, 1),
                LocalDate.now(), "FoodCorp", MeasurementUnit.KILOGRAMS,
                50, new Location(1, 1, 1), "Bulk bag", 365));

        storageRoom.addProduct(new NonPerishableProduct("Detergent", LocalDate.of(2030, 1, 1),
                LocalDate.now(), "CleanCo", MeasurementUnit.LITERS,
                20, new Location(2, 1, 3), "Industrial use"));

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/products", exchange -> {
            String json = toJson(storageRoom.getAllProducts());
            byte[] bytes = json.getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        });

        server.start();
        System.out.println("Server running on http://localhost:8000/products");
    }

    private static String toJson(List<Product> products) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            sb.append("{\"name\":\"").append(p.getName())
                    .append("\",\"expiryDate\":\"").append(p.getExpiryDate())
                    .append("\",\"manufacturer\":\"").append(p.getManufacturerName())
                    .append("\",\"quantity\":").append(p.getAvailableQuantity())
                    .append(",\"unit\":\"").append(p.getMeasurementUnit())
                    .append("\",\"category\":\"").append(p.getCategory())
                    .append("\",\"storageFee\":").append(p.getStorageFee())
                    .append("}");
            if (i < products.size() - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }
}