package io.github.nichetoolkit.rest.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * <code>ServerConfig</code>
 * <p>The server config class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Getter
@Setter
@SuppressWarnings("SameNameButDifferent")
public class ServerConfig {
    /**
     * <code>IP_ADDRESS</code>
     * {@link java.lang.String} <p>The constant <code>IP_ADDRESS</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String IP_ADDRESS = "spring.cloud.client.ip-address";
    /**
     * <code>SERVER_PORT</code>
     * {@link java.lang.String} <p>The constant <code>SERVER_PORT</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String SERVER_PORT = "server.port";
    /**
     * <code>APP_NAME</code>
     * {@link java.lang.String} <p>The constant <code>APP_NAME</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String APP_NAME = "spring.application.name";

    /**
     * <code>ip</code>
     * {@link java.lang.String} <p>The <code>ip</code> field.</p>
     * @see java.lang.String
     */
    private String ip;
    /**
     * <code>port</code>
     * {@link java.lang.String} <p>The <code>port</code> field.</p>
     * @see java.lang.String
     */
    private String port;
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see java.lang.String
     */
    private String name;

    /**
     * <code>ServerConfig</code>
     * <p>Instantiates a new server config.</p>
     */
    public ServerConfig() {
    }

    /**
     * <code>ServerConfig</code>
     * <p>Instantiates a new server config.</p>
     * @param ip   {@link java.lang.String} <p>The ip parameter is <code>String</code> type.</p>
     * @param port {@link java.lang.String} <p>The port parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServerConfig(String ip, String port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    /**
     * <code>toServer</code>
     * <p>The to server method.</p>
     * @return {@link java.lang.String} <p>The to server return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String toServer() {
        return name + "/" + ip + ":" + name + ":" + port;
    }
}
