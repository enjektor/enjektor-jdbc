package com.github.enjektor.jdbc.parser;

import com.github.enjektor.jdbc.credentials.JdbcCredentials;

public interface JdbcCredentialsParser {
    JdbcCredentials parse(String configuration);
}
