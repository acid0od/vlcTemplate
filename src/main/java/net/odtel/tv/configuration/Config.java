
package net.odtel.tv.configuration;


public interface Config {
    String getDbPrefix();
    String getHost();
    String getPort();
    String getDbName();
    String getDriverName();
    String getUsername();
    String getPassword();
    String getJWTHeader();
    String getJWTSecret();
    String getJWTExpiration();
    String getTitle();
    String getPrefix();
}
