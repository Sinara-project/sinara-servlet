package com.sinara.model;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Empresa {
    public static boolean nulo = false;

    // Expressões Regulares
    public static final String emailRegex = "[^@]*@.*\\..*";
    public static final String cnpjRegex = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}/0001-?[0-9]{2}";
    public static final String telefoneRegex = "\\(?[0-9]{2}\\)?[ ]*9[0-9]{4}[- ]?[0-9]{4}";
    public static final String planoRegex = "(MENSAL|ANUAL|GRÁTIS)";
    // Atributos
    private Integer id;
    private String cnpj;
    private String nome;
    private String email;
    private String ramo;
    private String telefone;
    private boolean status;
    private Date inicioPlano;
    private String plano;

    // Construtores

    // Construtor presume que dados estejam corretos; Usado para dados pegos do banco
    public Empresa(Integer id, String cnpj, String nome, String email, String ramo, String telefone, boolean status,
                   Date inicioPlano, String plano) {
        // Inicializa suas variáveis como null
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.ramo = ramo;
        this.telefone = telefone;
        this.status = status;
        this.inicioPlano = inicioPlano;
        this.plano = plano;
    }

    // Construtor analisa dados antes da entrada, e faz tratamento de erros; Usado para dados cedidos pelo usuário
    // Construtor para criar nova empresa
    public Empresa(String cnpj, String nome, String email, String ramo, String telefone, String status,
                   String inicioPlano, String plano, List<String> erros) {
        // Inicializa suas variáveis como null
        this.id = null;
        this.cnpj = null;
        this.nome = "";
        this.email = "";
        this.ramo = "";
        this.telefone = "";
        this.status = false;
        this.inicioPlano = null;
        this.plano = "";

        // Adiciona à pilha de erros conforme resultado do método 'tratarResultado' e altera seus valores
        tratarResultado(setCnpj(cnpj), erros, "cnpj");
        tratarResultado(setNome(nome), erros, "nome");
        tratarResultado(setEmail(email), erros, "email");
        tratarResultado(setRamo(ramo), erros, "ramo de atuação");
        tratarResultado(setTelefone(telefone), erros, "telefone");
        tratarResultado(setStatus(status), erros, "status de atividade");
        tratarResultado(setInicioPlano(inicioPlano), erros, "início do plano");
        tratarResultado(setPlano(plano), erros, "tipo do plano");
    }
    // Construtor usado para editar empresa existente
    public Empresa(Integer id, String nome, String email, String ramo, String telefone, String status,
                   List<String> erros) {
        // Inicializa suas variáveis como null
        this.id = null;
        this.cnpj = null;
        this.nome = "";
        this.email = "";
        this.ramo = "";
        this.telefone = "";
        this.status = false;
        this.inicioPlano = null;
        this.plano = "";

        // Adiciona à pilha de erros conforme resultado do método 'tratarResultado' e altera seus valores
        tratarResultado(setId(id), erros, "id");
        tratarResultado(setNome(nome), erros, "nome");
        tratarResultado(setEmail(email), erros, "email");
        tratarResultado(setRamo(ramo), erros, "ramo de atuação");
        tratarResultado(setTelefone(telefone), erros, "telefone");
        tratarResultado(setStatus(status), erros, "status de atividade");
    }

    // Getters
    public Integer getId() {
        return this.id;
    }
    public String getCnpj() {
        return this.cnpj;
    }

    public String getNome() {
        return this.nome;
    }
    public String getEmail() {
        return this.email;
    }
    public String getRamo() {
        return this.ramo;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public boolean isStatus() {
        return this.status;
    }
    public Date getInicioPlano() {
        return this.inicioPlano;
    }
    public String getPlano() {
        return plano;
    }

    /* Padrão de retorno
     *  -1 = Null
     *  0 = Não houve mudança no parâmetro
     *  1 = Código rodou normalmente
     *  2 = SQLException
     *  3 = Erro de sintaxe do parâmetro
     *  4 = Atributo final já definido
     * */

    // Setters especiais (Só podem ser setados uma vez, agem como finais)
    public int setId(Integer id) {
        if (this.id == null) {
            this.id = id;
        } else return 4;
        return 1;
    }
    public int setCnpj(String cnpj) {
        if (this.cnpj == null) {
            this.cnpj = cnpj;
            if (checarSintaxe(cnpj, cnpjRegex)) this.cnpj = extrairNumeros(cnpj);
            else return 3;
        } else return 4;
        return 1;
    }

    // Setters
    public int setNome(String nome) {
        try {
            if (nome == null || nome.isBlank()) return -1;
            else if (!this.nome.equals(nome)) this.nome = nome;
            else return 0;
        } catch (IllegalArgumentException exc) {
            exc.printStackTrace();
            return 3;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }
    public int setEmail(String email) {
        try {
            if (email == null || email.isBlank()) return -1;
            else if (!this.email.equals(email))
                if (checarSintaxe(email, emailRegex)) this.email = email;
                else return 3;
            else return 0;
        } catch (IllegalArgumentException exc) {
            exc.printStackTrace();
            return 3;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }
    public int setTelefone(String telefone) {
        try {
            if (telefone == null || telefone.isBlank()) return -1;
            else {
                if (!this.telefone.equals(telefone)) {
                    this.telefone = telefone;
                    if (checarSintaxe(telefone, telefoneRegex)) this.telefone = extrairNumeros(telefone);
                    else return 3;
                } else return 0;
            }
        } catch (IllegalArgumentException exc) {
            exc.printStackTrace();
            return 3;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }
    public int setRamo(String ramo) {
        try {
        if (ramo==null || ramo.isBlank()) return -1;
        else if (!this.ramo.equals(ramo)) this.ramo = ramo;
        else return 0;
        } catch (IllegalArgumentException exc) {
            exc.printStackTrace();
            return 3;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }
    public int setStatus(String statusStr) {
        try {
            boolean status = statusStr.equals("on");
            if (this.status != status) this.status = status;
            else return 0;
        } catch (IllegalArgumentException exc) {
            exc.printStackTrace();
            return 3;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }
    public int setInicioPlano(String inicioPlanoStr) {
        try {
            Date inicioPlano = Date.valueOf(inicioPlanoStr);
            if (inicioPlano == null) return -1 ;
            else {
                if (this.inicioPlano==null) this.inicioPlano = inicioPlano;
                if (!this.inicioPlano.equals(inicioPlano)) this.inicioPlano = inicioPlano;
                else return 0;
            }
        } catch (IllegalArgumentException exc) {
            exc.printStackTrace();
            return 3;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }
    public int setPlano(String plano) {
        try {
            if (plano == null || plano.isBlank()) return -1;
            else if (!this.plano.equals(plano))
                if (checarSintaxe(plano, planoRegex)) this.plano = plano;
                else return 3;
            else return 0;
        } catch (IllegalArgumentException exc) {
            exc.printStackTrace();
            return 3;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }

    // Métodos de apoio para métodos posteriores

    public static void tratarResultado(int resultado, List<String> erro, String campo) {
        // Inserir erros de acordo com padrão de resultados
        switch (resultado) {
            case -1 -> {
                if (!nulo) {
                    erro.add("Campos necessários não foram preenchidos!");
                    nulo = true;
                }
            }
            case 2 -> erro.add("Erro no banco de dados!");
            case 3 -> erro.add("Sintaxe de "+ campo +" incorreta!");
            case 4 -> erro.add("Atributo final "+ campo +" já possui um valor!");
            case 5 -> erro.add("Argumento ilegal fornecido para "+ campo);
        }
    }
    public static String extrairNumeros(String str) {
        Pattern telRegex = Pattern.compile("[0-9]*");
        Matcher matcher = telRegex.matcher(str);
        StringBuilder r = new StringBuilder();
        while (matcher.find()) {
            r.append(matcher.group());
        }
        return r.toString();
    }
    public static boolean checarSintaxe(String str, String regex) {
        if (str==null) return false;
        Pattern telRegex = Pattern.compile(regex);
        Matcher matcher = telRegex.matcher(str);
        return matcher.matches();
    }
}