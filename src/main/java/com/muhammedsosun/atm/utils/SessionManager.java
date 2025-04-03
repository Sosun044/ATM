package com.muhammedsosun.atm.utils;

import com.muhammedsosun.atm.dto.UserDTO;

public class SessionManager {
    private static UserDTO currentUser;

    public static void setCurrentUser(UserDTO user) {
        currentUser = user;
    }

    public static UserDTO getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}


