package com.muhammedsosun.atm.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    private static final ObservableResourceFactory factory = new ObservableResourceFactory();

    static {
        Locale defaultLocale = new Locale("tr");
        ResourceBundle bundle = ResourceBundle
                .getBundle("com.muhammedsosun.atm.lang", defaultLocale);
        factory.setResources(bundle);
    }

    public static ObservableResourceFactory getFactory() {
        return factory;
    }

    //dili değiştirmek için
    public static void changeLanguage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.muhammedsosun.atm.lang", locale);
        factory.setResources(bundle);
    }
}
