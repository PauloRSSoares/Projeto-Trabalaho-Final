package br.com.senac.trabalho.java.fx.service;

import br.com.senac.trabalho.java.fx.db.ConexaoDataBase;
import br.com.senac.trabalho.java.fx.model.CadastroCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroClienteService {

    private static ConexaoDataBase conexao = new ConexaoDataBase();

    public static List<CadastroCliente> carregarClientes() throws SQLException, ClassNotFoundException {
        List<CadastroCliente> out = new ArrayList<>();

        Connection conn = conexao.getConexao();

        Statement sta = conn.createStatement();
        ResultSet resultSet = sta.executeQuery("SELECT * FROM clientes;");

        while(resultSet.next()){
            CadastroCliente cli = new CadastroCliente();
            cli.setIdCli(resultSet.getInt(1));
            cli.setNome(resultSet.getString(2));
            cli.setDocumento(resultSet.getString(3));
            cli.setRg(resultSet.getInt(4));
            cli.setEmail(resultSet.getString(5));
            cli.setTelefone(resultSet.getString(6));
            out.add(cli);
        }

        return out;
    }

    public static void salvarCliente(CadastroCliente cli){
        try{
            Connection conn = conexao.getConexao();
            String sqlInsert = "insert into clientes (documento, nome, rg, email, telefone) values (?, ?, ?, ?, ?)";

            PreparedStatement pre = conn.prepareStatement(sqlInsert);
            pre.setInt(1, cli.getIdCli());
            pre.setString(1, cli.getDocumento());
            pre.setString(2, cli.getNome());
            pre.setInt(3, cli.getRg());
            pre.setString(4, cli.getEmail());
            pre.setString(5, cli.getTelefone());

            pre.execute();

            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deletarCliente(int idCli){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from clientes where idCli";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idCli);
            return ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean atualizarCliente(int idCli, CadastroCliente cli) {
        try {
            Connection conn = conexao.getConexao();

            String updateSql = "update clientes set idCli = ?, documento = ?, nome = ?, rg = ?, email = ?, telefone = ?";
            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setInt(1, cli.getIdCli());
            ps.setString(2, cli.getDocumento());
            ps.setString(3, cli.getNome());
            ps.setInt(4, cli.getRg());
            ps.setString(5, cli.getEmail());
            ps.setString(6, cli.getTelefone());

            return ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public static boolean buscarClienteByDocumento(String documento){
        try{
            Connection conn = conexao.getConexao();

            String selectSql = "select id from clientes where documento = '" + documento + "'";
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static void inseriCliente(CadastroCliente cli) {
    }
}
