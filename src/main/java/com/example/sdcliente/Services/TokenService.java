package com.example.sdcliente.Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TokenService {
    private static final String FILE_PATH = "jwt_token.txt";

    public static void saveJwtToken(String token) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJwtToken() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            if (scanner.hasNext()) {
                return scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
