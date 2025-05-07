package com.example.clientApi.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    static {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry -> {
            if (System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }
}

