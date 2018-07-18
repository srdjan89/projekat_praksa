/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praksa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Срђан
 */
public class ConnectionManager {

    private static String protocol = "jdbc:mysql:";
    private static String ip = "localhost";
    private static String port = "3306";
    private static String dbName = "turisticki_portal";
    private static String urlString = protocol + "//" + ip + ":" + port + "/" + dbName;

    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "751356";

    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlString, username, password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection." + ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found." + ex);
        }
        return con;
    }

    public static String getProtocol() {
        return protocol;
    }

    public static void setProtocol(String aProtocol) {
        protocol = aProtocol;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String aIp) {
        ip = aIp;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String aPort) {
        port = aPort;
    }

    public static String getDbName() {
        return dbName;
    }

    public static void setDbName(String aDbName) {
        dbName = aDbName;
    }

    public static String getUrlString() {
        return urlString;
    }

    public static void setUrlString(String aUrlString) {
        urlString = aUrlString;
    }

    public static String getDriverName() {
        return driverName;
    }

    public static void setDriverName(String aDriverName) {
        driverName = aDriverName;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String aPassword) {
        password = aPassword;
    }

}
