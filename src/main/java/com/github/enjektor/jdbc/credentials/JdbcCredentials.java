package com.github.enjektor.jdbc.credentials;

public class JdbcCredentials {

    private final String url;
    private final String username;
    private final String password;
    private final String database;
    private final String host;

    private JdbcCredentials(Builder builder) {
        this.url = builder.url;
        this.username = builder.username;
        this.password = builder.password;
        this.database = builder.database;
        this.host = builder.host;
    }

    public static class Builder {
        private String url;
        private String username;
        private String password;
        private String database;
        private String host;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public JdbcCredentials build() {
            return new JdbcCredentials(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }
}
