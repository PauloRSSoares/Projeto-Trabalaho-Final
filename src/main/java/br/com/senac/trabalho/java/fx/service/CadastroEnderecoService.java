package br.com.senac.trabalho.java.fx.service;

import br.com.senac.trabalho.java.fx.db.ConexaoDataBase;
import br.com.senac.trabalho.java.fx.model.CadastroEndereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroEnderecoService {

    private static ConexaoDataBase conexao = new ConexaoDataBase();

    public static List<CadastroEndereco> carregarEndereco() throws SQLException, ClassNotFoundException {
        List<CadastroEndereco> out = new ArrayList<>();

        Connection conn = conexao.getConexao();

        Statement sta = conn.createStatement();
        ResultSet resultSet = sta.executeQuery("select * from endereco;");

        while (resultSet.next()) {
            CadastroEndereco end = new CadastroEndereco();
            end.setIdEnd(resultSet.getInt(1));
            end.setCodigoCliente(resultSet.getInt(2));
            end.setCep(resultSet.getInt(3));
            end.setRua(resultSet.getString(4));
            end.setNumero(resultSet.getInt(5));
            end.setBairro(resultSet.getString(6));
            end.setCidade(resultSet.getString(7));
            end.setEstado(resultSet.getString(8));
            out.add(end);
        }

        return out;
    }

    public static void salvarEndereco(CadastroEndereco endereco) {
        try{
            Connection conn = conexao.getConexao();
            String sqlInsert = "insert into endereco (idEnd, codigoCliente, cep, rua, numero, bairro, cidade, estado) values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pre = conn.prepareStatement(sqlInsert);
            pre.setInt(1, endereco.getIdEnd());
            pre.setInt(2, endereco.getCodigoCliente());
            pre.setInt(3, endereco.getCep());
            pre.setString(4, endereco.getRua());
            pre.setInt(5, endereco.getNumero());
            pre.setString(6, endereco.getBairro());
            pre.setString(7, endereco.getCidade());
            pre.setString(8, endereco.getEstado());

            pre.execute();

            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deletarEndereco(int idEnd){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from endereco where idEnd";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idEnd);
            return ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean atualizarEndereco(int idEnd, CadastroEndereco end) {
        try {
            Connection conn = conexao.getConexao();

            String updateSql = "update endereco set idEnd = ?, codigoCliente = ?, cep = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?";
            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setInt(1, end.getIdEnd());
            ps.setInt(2, end.getCodigoCliente());
            ps.setInt(3, end.getCep());
            ps.setString(4, end.getRua());
            ps.setInt(5, end.getNumero());
            ps.setString(6, end.getBairro());
            ps.setString(7, end.getCidade());
            ps.setString(8, end.getEstado());

            return ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public static boolean buscarEnderecoByDocumento(String codigoCliente){
        try{
            Connection conn = conexao.getConexao();

            String selectSql = "select id from endereco where codigoCliente = '" + codigoCliente + "'";
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static void inseriEndereco(CadastroEndereco end) {
    }
}