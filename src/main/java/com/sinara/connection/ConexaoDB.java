package com.sinara.connection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    public Connection conectar() {
        Dotenv envVars = Dotenv.load();
        String host = envVars.get("HOST");
        String port = envVars.get("PORT");
        String database = envVars.get("DATABASE");
        String user = envVars.get("USER");
        String password = envVars.get("PASSWORD");
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database,
                    user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void desconectar(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
