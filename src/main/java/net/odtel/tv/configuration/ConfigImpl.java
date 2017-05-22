package net.odtel.tv.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class ConfigImpl implements Config {
    @Value("${vlcTemplate.db.dbPrefix}")
    private String dbPrefix;
    @Value("${vlcTemplate.db.host}")
    private String host;
    @Value("${vlcTemplate.db.port}")
    private String port;
    @Value("${vlcTemplate.db.dbName}")
    private String dbName;
    @Value("${vlcTemplate.db.driverName:com.mysql.jdbc.Driver}")
    private String driverName;
    @Value("${vlcTemplate.db.username}")
    private String username;
    @Value("${vlcTemplate.db.password}")
    private String password;
}
