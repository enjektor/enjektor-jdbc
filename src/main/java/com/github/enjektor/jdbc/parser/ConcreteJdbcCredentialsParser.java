package com.github.enjektor.jdbc.parser;

import com.github.enjektor.jdbc.credentials.JdbcCredentials;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConcreteJdbcCredentialsParser implements JdbcCredentialsParser {

    private final static String DS_KEYWORD = "datasource";

    @Override
    public JdbcCredentials parse(String configuration) {
        Map<String, String> map = new HashMap<>(3);

        try (Scanner scanner = new Scanner(configuration)) {
            scanner.useDelimiter(System.lineSeparator());

            while (scanner.hasNext()) {
                final String next = scanner.next();
                if (next.contains(DS_KEYWORD)) {
                    final String[] split = next.split("=");
                    String key = split[0];
                    String value = split[1];
                    map.put(key, value);
                }
            }
        }

        String url = map.get("enjektor.datasource.url");
        String username = map.get("enjektor.datasource.username");
        String password = map.get("enjektor.datasource.password");

        return JdbcCredentials
                .builder()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
