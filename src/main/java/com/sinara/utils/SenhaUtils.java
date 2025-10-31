package com.sinara.utils;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaUtils {

    // Gera o hash seguro com salt embutido
    public static String hashearSenha(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    // Verifica se a senha pura corresponde ao hash
    public static boolean verificarSenha(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
