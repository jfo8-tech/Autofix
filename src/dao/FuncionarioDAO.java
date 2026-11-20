package dao;

import model.Funcionario;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // 1. CADASTRAR FUNCIONÁRIO
    public boolean cadastrar(Funcionario funcionario)
            throws SQLException {

        String sql = "INSERT INTO funcionarios "
                + "(nome, cpf, telefone, email, cargo, salario, data_admissao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getCargo());
            stmt.setBigDecimal(6, funcionario.getSalario());
            stmt.setDate(7, Date.valueOf(funcionario.getDataAdmissao()));

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        funcionario.setIdFuncionario(rs.getInt(1));
                    }
                }
                return true;
            }

            return false;
        }
    }

    // 2. LISTAR FUNCIONÁRIOS
    public List<Funcionario> listar() throws SQLException {

        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT * FROM funcionarios ORDER BY nome";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(mapearFuncionario(rs));
            }
        }

        return funcionarios;
    }

    // 3. BUSCAR POR ID
    public Funcionario buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM funcionarios "
                + "WHERE id_funcionario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFuncionario(rs);
                }
            }
        }

        return null;
    }

    // 4. BUSCAR POR CPF
    public Funcionario buscarPorCpf(String cpf) throws SQLException {

        String sql = "SELECT * FROM funcionarios WHERE cpf = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFuncionario(rs);
                }
            }
        }

        return null;
    }

    // 5. ATUALIZAR FUNCIONÁRIO
    public boolean atualizar(Funcionario funcionario)
            throws SQLException {

        String sql = "UPDATE funcionarios SET "
                + "nome = ?, cpf = ?, telefone = ?, email = ?, "
                + "cargo = ?, salario = ?, data_admissao = ? "
                + "WHERE id_funcionario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getCargo());
            stmt.setBigDecimal(6, funcionario.getSalario());
            stmt.setDate(7, Date.valueOf(funcionario.getDataAdmissao()));
            stmt.setInt(8, funcionario.getIdFuncionario());

            return stmt.executeUpdate() > 0;
        }
    }

    // 6. EXCLUIR FUNCIONÁRIO
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM funcionarios "
                + "WHERE id_funcionario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    // 7. MAPEAR RESULTADO DO MYSQL
    private Funcionario mapearFuncionario(ResultSet rs)
            throws SQLException {

        Funcionario funcionario = new Funcionario();

        funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setEmail(rs.getString("email"));
        funcionario.setCargo(rs.getString("cargo"));
        funcionario.setSalario(rs.getBigDecimal("salario"));

        Date data = rs.getDate("data_admissao");

        if (data != null) {
            funcionario.setDataAdmissao(data.toLocalDate());
        }

        return funcionario;
    }
}