package com.test.mobile.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties properties = new Properties();

    public Config(String plataform) {
        String file = "/config/" + ("ios".equalsIgnoreCase(plataform) ? "ios.properties" : "android.properties");
        try (InputStream is = getClass().getResourceAsStream(file)) {
            if (is == null) {
                throw new IllegalStateException("No se encontró el archivo de configuración: " + file);
            }
            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración", e);
        }
        properties.putAll(System.getProperties());
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
