package dao;

import model.Usuario;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Cadastrar usuário
    public void cadastrar(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuarios "
                + "(id_funcionario, usuario, senha, tipo) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getIdFuncionario());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());

            stmt.executeUpdate();
        }
    }

    // Buscar usuário pelo nome de acesso
    public Usuario buscarPorUsuario(String nomeUsuario)
            throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeUsuario);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    // Buscar usuário pelo ID
    public Usuario buscarPorId(int idUsuario) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    // Listar usuários
    public List<Usuario> listar() throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }

        return usuarios;
    }

    // Atualizar usuário
    public void atualizar(Usuario usuario) throws SQLException {

        String sql = "UPDATE usuarios SET "
                + "id_funcionario = ?, usuario = ?, "
                + "senha = ?, tipo = ? "
                + "WHERE id_usuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getIdFuncionario());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setInt(5, usuario.getIdUsuario());

            stmt.executeUpdate();
        }
    }

    // Excluir usuário
    public void excluir(int idUsuario) throws SQLException {

        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            stmt.executeUpdate();
        }
    }

    // Converter resultado do banco em objeto Usuario
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setIdFuncionario(rs.getInt("id_funcionario"));
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipo(rs.getString("tipo"));

        return usuario;
    }
}
