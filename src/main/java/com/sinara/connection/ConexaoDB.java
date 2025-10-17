package com.sinara.connection;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    // Método para retornar uma conexão com o banco principal e lidar com as exceções necessárias
    public Connection conectar() {
        Dotenv envVars = Dotenv.load();
        String host = envVars.get("HOST");
        String port = envVars.get("PORT");
        String database = envVars.get("DATABASE");
        String user = envVars.get("USER");
        String password = envVars.get("PASSWORD");
        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database,
                    user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }

    // Método para desconectar dada conexão, lidando com as exceções necessárias
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
