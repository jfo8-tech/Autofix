package dao;

import model.Cliente;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // 1. CADASTRAR CLIENTE
    public boolean cadastrar(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO clientes "
                + "(nome, cpf, telefone, email, endereco, data_cadastro) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setDate(6, Date.valueOf(cliente.getDataCadastro()));

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {
                        cliente.setIdCliente(rs.getInt(1));
                    }
                }

                return true;
            }

            return false;
        }
    }

    // 2. LISTAR TODOS OS CLIENTES
    public List<Cliente> listar() throws SQLException {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes ORDER BY nome";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        }

        return clientes;
    }

    // 3. BUSCAR CLIENTE PELO ID
    public Cliente buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM clientes WHERE id_clientes = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        }

        return null;
    }

    // 4. BUSCAR CLIENTE PELO CPF
    public Cliente buscarPorCpf(String cpf) throws SQLException {

        String sql = "SELECT * FROM clientes WHERE cpf = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        }

        return null;
    }

    // 5. ATUALIZAR CLIENTE
    public boolean atualizar(Cliente cliente) throws SQLException {

        String sql = "UPDATE clientes SET "
                + "nome = ?, cpf = ?, telefone = ?, "
                + "email = ?, endereco = ?, data_cadastro = ? "
                + "WHERE id_clientes = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setDate(6, Date.valueOf(cliente.getDataCadastro()));
            stmt.setInt(7, cliente.getIdCliente());

            return stmt.executeUpdate() > 0;
        }
    }

    // 6. EXCLUIR CLIENTE
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM clientes WHERE id_clientes = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    // 7. CONVERTER RESULTADO DO MYSQL EM OBJETO CLIENTE
    private Cliente mapearCliente(ResultSet rs) throws SQLException {

        Cliente cliente = new Cliente();

        cliente.setIdCliente(rs.getInt("id_clientes"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        cliente.setEndereco(rs.getString("endereco"));

        Date data = rs.getDate("data_cadastro");

        if (data != null) {
            cliente.setDataCadastro(data.toLocalDate());
        }

        return cliente;
    }
}
