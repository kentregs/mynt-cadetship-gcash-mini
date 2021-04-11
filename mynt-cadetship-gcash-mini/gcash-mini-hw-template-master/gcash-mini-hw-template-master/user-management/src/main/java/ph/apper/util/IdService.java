package ph.apper.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.UUID;

public final class IdService {
    public static String getNextUserId() {
        return UUID.randomUUID().toString();
    }

    public static String generateCode(int size) {
        return RandomStringUtils.randomAlphanumeric(size);
    }
}