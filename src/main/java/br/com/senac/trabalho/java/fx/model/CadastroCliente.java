package br.com.senac.trabalho.java.fx.model;

public class CadastroCliente {

    private int idCli;

    private String documento;

    private String nome;

    private int rg;

    private String email;

    private String telefone;

    public CadastroCliente() {

    }

    public CadastroCliente(int idCli, String documento, String nome, int rg, String email, String telefone) {
        this.idCli = idCli;
        this.documento = documento;
        this.nome = nome;
        this.rg = rg;
        this.email = email;
        this.telefone = telefone;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
