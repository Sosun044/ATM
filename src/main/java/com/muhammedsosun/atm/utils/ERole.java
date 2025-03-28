package com.muhammedsosun.atm.utils;

public enum ERole {
    USER("KULLANICI"),
    MODERATOR("MODERATOR"),
    ADMIN("YÖNETİCİ"),
    ;

    private final String description;

    ERole(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 📌 String’den Enum’a güvenli dönüşüm yapar.
     */
    public static ERole fromString(String role) {
        try {
            return ERole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("❌ Geçersiz rol: " + role);
        }
    }
}
