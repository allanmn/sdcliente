package com.example.sdcliente.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TokenService {
    private static final String SECRET_KEY = "AoT3QFTTEkj16rCby/TPVBWvfSQHL3GeEz3zVwEd6LDrQDT97sgDY8HJyxgnH79jupBWFOQ1+7fRPBLZfpuA2lwwHqTgk+NJcWQnDpHn31CVm63Or5c5gb4H7/eSIdd+7hf3v+0a5qVsnyxkHbcxXquqk9ezxrUe93cFppxH4/kF/kGBBamm3kuUVbdBUY39c4U3NRkzSO+XdGs69ssK5SPzshn01axCJoNXqqj+ytebuMwF8oI9+ZDqj/XsQ1CLnChbsL+HCl68ioTeoYU9PLrO4on+rNHGPI0Cx6HrVse7M3WQBPGzOd1TvRh9eWJrvQrP/hm6kOR7KrWKuyJzrQh7OoDxrweXFH8toXeQRD8=";
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

    public static boolean eraseJwtToken() {
        File jwt = new File(FILE_PATH);

        if (jwt.exists()) {
            return jwt.delete();
        }

        return false;
    }

    private static Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    }

    public static boolean isAdmin(String token) {
        Jws<Claims> parsedToken = parseToken(token);

        System.out.println(parsedToken);

        return (boolean) parsedToken.getBody().get("admin", Boolean.class);
    }
}
