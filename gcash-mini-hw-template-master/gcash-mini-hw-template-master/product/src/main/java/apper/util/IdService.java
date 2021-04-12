package apper.util;

import org.apache.commons.lang.RandomStringUtils;

public class IdService {
    public static String generateProductId(int size) {
        return RandomStringUtils.randomAlphanumeric(size);
    }
}
