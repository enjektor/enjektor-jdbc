package com.github.enjektor.jdbc;

import com.github.enesusta.jdbc.datasource.HikariJdbcDataSource;
import com.github.enesusta.jdbc.datasource.JdbcConfiguration;
import com.github.enjektor.core.auto.configuration.BeanAutoConfiguration;
import com.github.enjektor.core.bean.Bean;
import com.github.enjektor.core.bean.pair.Pair;
import com.github.enjektor.epel.yaml.converter.ConcreteYamlExporter;
import com.github.enjektor.epel.yaml.converter.YamlConverter;
import com.github.enjektor.jdbc.credentials.JdbcCredentials;
import com.github.enjektor.jdbc.parser.ConcreteJdbcCredentialsParser;
import com.github.enjektor.jdbc.parser.JdbcCredentialsParser;
import com.google.auto.service.AutoService;

import javax.sql.DataSource;

@AutoService(BeanAutoConfiguration.class)
public class EnjektorJdbcAutoConfiguration implements BeanAutoConfiguration {

    @Override
    public Pair export() {
        return export(null);
    }

    @Override
    public Pair export(String profileProperty) {
        final YamlConverter yamlConverter = new ConcreteYamlExporter();
        final JdbcCredentialsParser jdbcCredentialsParser = new ConcreteJdbcCredentialsParser();

        final String conf = yamlConverter.convert(profileProperty);
        final JdbcCredentials jdbcCredentials = jdbcCredentialsParser.parse(conf);

        final JdbcConfiguration configuration = new JdbcConfiguration.JdbcConfigurationBuilder()
                .username(jdbcCredentials.getUsername())
                .password(jdbcCredentials.getPassword())
                .jdbcUrl(jdbcCredentials.getUrl())
                .build();

        final HikariJdbcDataSource jdbcDataSource = new HikariJdbcDataSource(configuration);
        final DataSource dataSource = jdbcDataSource.getDataSource();

        final Bean bean = new Bean(DataSource.class);
        bean.register(DataSource.class.getSimpleName(), dataSource);

        return Pair.builder()
                .type(DataSource.class)
                .bean(bean)
                .build();
    }
}
