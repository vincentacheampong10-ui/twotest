package com.pluralsight.data;

import com.pluralsight.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {
    public static void saveReceipt(Order order) {
        try {
            File directory = new File("receipts");
            if (!directory.exists()) {
                directory.mkdir();
            }

            String filename = "receipts/" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("=== Jollof Express Receipt ===\n");
                writer.write(order.toString());
                writer.write("\nTotal: GHS " + String.format("%.2f", order.calculateTotal()) + "\n");
            }

            System.out.println("✅ Order saved successfully!");
            System.out.println("📄 Receipt saved");

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}
