package com.example.learnSpringBoot.utils;

import java.util.UUID;

public class Utils {
    public String generateUserId() {
        return UUID.randomUUID().toString();
    }
}
