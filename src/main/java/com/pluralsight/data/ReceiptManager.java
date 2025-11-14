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
            // 1. Correctly define the receipts FOLDER/DIRECTORY
            File directory = new File("receipts");
            if (!directory.exists()) {
                directory.mkdir(); // Creates the 'receipts' folder if it doesn't exist
            }

            // 2. path for the actual receipt FILE inside that folder
            String filename = "receipts/" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";

            // 3. Write the content to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("=== Jollof Express Receipt ===\n");
                writer.write(order.toString());
                writer.write("\nTotal: GHS " + String.format("%.2f", order.calculateTotal()) + "\n");
            }
            // If I reach this point, saving was successful.
            System.out.println("✅ Order saved successfully!");

        } catch (IOException e) {
            // If an error occurred in any part of the process, only print the error
            System.out.println("Error saving receipt: " + e.getMessage());
            // Do NOT print the success message here.
        }
    }
}

