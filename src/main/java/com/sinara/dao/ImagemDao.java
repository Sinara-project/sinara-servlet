package com.sinara.dao;

import com.sinara.connection.ConexaoDB;
import com.sinara.model.Imagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImagemDao {

    public String buscarImagem(int adminId) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String imagem = "";
        ResultSet rs = null;
        try {
            conn = conexao.conectar();

            pstmt = conn.prepareStatement("select * from imagem where admin_id = ?");
            pstmt.setInt(1, adminId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                imagem = rs.getString("url");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conexao.desconectar(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return imagem;
    }
}
