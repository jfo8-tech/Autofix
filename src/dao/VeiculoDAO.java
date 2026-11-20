package dao;

import model.Veiculo;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    // Cadastrar veículo
    public void cadastrar(Veiculo veiculo) throws SQLException {

        String sql = "INSERT INTO veiculos "
                + "(marca, modelo, ano_fabricacao, cor, placa, "
                + "quilometragem, preco, categoria, combustivel, disponibilidade) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getPlaca());
            stmt.setInt(6, veiculo.getQuilometragem());
            stmt.setBigDecimal(7, veiculo.getPreco());
            stmt.setString(8, veiculo.getCategoria());
            stmt.setString(9, veiculo.getCombustivel());
            stmt.setString(10, veiculo.getDisponibilidade());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    veiculo.setIdVeiculo(rs.getInt(1));
                }
            }
        }
    }

    // Listar todos os veículos
    public List<Veiculo> listar() throws SQLException {

        List<Veiculo> veiculos = new ArrayList<>();

        String sql = "SELECT * FROM veiculos";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                veiculos.add(mapearVeiculo(rs));
            }
        }

        return veiculos;
    }

    // Buscar veículo pelo ID
    public Veiculo buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM veiculos WHERE id_veiculos = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearVeiculo(rs);
                }
            }
        }

        return null;
    }

    // Buscar veículo pela placa
    public Veiculo buscarPorPlaca(String placa) throws SQLException {

        String sql = "SELECT * FROM veiculos WHERE placa = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, placa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearVeiculo(rs);
                }
            }
        }

        return null;
    }

    // Atualizar veículo
    public void atualizar(Veiculo veiculo) throws SQLException {

        String sql = "UPDATE veiculos SET "
                + "marca = ?, modelo = ?, ano_fabricacao = ?, cor = ?, "
                + "placa = ?, quilometragem = ?, preco = ?, categoria = ?, "
                + "combustivel = ?, disponibilidade = ? "
                + "WHERE id_veiculos = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getPlaca());
            stmt.setInt(6, veiculo.getQuilometragem());
            stmt.setBigDecimal(7, veiculo.getPreco());
            stmt.setString(8, veiculo.getCategoria());
            stmt.setString(9, veiculo.getCombustivel());
            stmt.setString(10, veiculo.getDisponibilidade());
            stmt.setInt(11, veiculo.getIdVeiculo());

            stmt.executeUpdate();
        }
    }

    // Excluir veículo
    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM veiculos WHERE id_veiculos = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Converter resultado do MySQL em objeto Veiculo
    private Veiculo mapearVeiculo(ResultSet rs) throws SQLException {

        Veiculo veiculo = new Veiculo();

        veiculo.setIdVeiculo(rs.getInt("id_veiculos"));
        veiculo.setMarca(rs.getString("marca"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
        veiculo.setCor(rs.getString("cor"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setQuilometragem(rs.getInt("quilometragem"));
        veiculo.setPreco(rs.getBigDecimal("preco"));
        veiculo.setCategoria(rs.getString("categoria"));
        veiculo.setCombustivel(rs.getString("combustivel"));
        veiculo.setDisponibilidade(rs.getString("disponibilidade"));

        return veiculo;
    }
}
