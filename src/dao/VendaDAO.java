package dao;

import model.Venda;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    // Cadastrar venda
    public void cadastrar(Venda venda) throws SQLException {

        String sql = "INSERT INTO vendas "
                + "(id_cliente, id_funcionario, id_veiculo, "
                + "data_venda, preco, pagamento, venda) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, venda.getIdCliente());
            stmt.setInt(2, venda.getIdFuncionario());
            stmt.setInt(3, venda.getIdVeiculo());
            stmt.setDate(4, java.sql.Date.valueOf(venda.getDataVenda()));
            stmt.setBigDecimal(5, venda.getPreco());
            stmt.setString(6, venda.getPagamento());
            stmt.setString(7, venda.getVenda());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    venda.setIdVenda(rs.getInt(1));
                }
            }
        }
    }

    // Listar todas as vendas
    public List<Venda> listar() throws SQLException {

        List<Venda> vendas = new ArrayList<>();

        String sql = "SELECT * FROM vendas";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vendas.add(mapearVenda(rs));
            }
        }

        return vendas;
    }

    // Buscar venda pelo ID
    public Venda buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM vendas WHERE id_venda = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearVenda(rs);
                }
            }
        }

        return null;
    }

    // Atualizar venda
    public void atualizar(Venda venda) throws SQLException {

        String sql = "UPDATE vendas SET "
                + "id_cliente = ?, "
                + "id_funcionario = ?, "
                + "id_veiculo = ?, "
                + "data_venda = ?, "
                + "preco = ?, "
                + "pagamento = ?, "
                + "venda = ? "
                + "WHERE id_venda = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, venda.getIdCliente());
            stmt.setInt(2, venda.getIdFuncionario());
            stmt.setInt(3, venda.getIdVeiculo());
            stmt.setDate(4, java.sql.Date.valueOf(venda.getDataVenda()));
            stmt.setBigDecimal(5, venda.getPreco());
            stmt.setString(6, venda.getPagamento());
            stmt.setString(7, venda.getVenda());
            stmt.setInt(8, venda.getIdVenda());

            stmt.executeUpdate();
        }
    }

    // Excluir venda
    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM vendas WHERE id_venda = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Mapear resultado do MySQL para objeto Venda
    private Venda mapearVenda(ResultSet rs) throws SQLException {

        Venda venda = new Venda();

        venda.setIdVenda(rs.getInt("id_venda"));
        venda.setIdCliente(rs.getInt("id_cliente"));
        venda.setIdFuncionario(rs.getInt("id_funcionario"));
        venda.setIdVeiculo(rs.getInt("id_veiculo"));
        venda.setDataVenda(rs.getDate("data_venda").toLocalDate());
        venda.setPreco(rs.getBigDecimal("preco"));
        venda.setPagamento(rs.getString("pagamento"));
        venda.setVenda(rs.getString("venda"));

        return venda;
    }
}
