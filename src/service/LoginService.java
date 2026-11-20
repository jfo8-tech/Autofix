package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class LoginService {

    private final UsuarioDAO usuarioDAO;

    private static final int ITERACOES = 210000;
    private static final int TAMANHO_HASH = 256;
    private static final int TAMANHO_SALT = 16;

    public LoginService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Autenticar usuário
    public Usuario autenticar(String nomeUsuario, char[] senha)
            throws Exception {

        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Informe o nome de usuário!");
        }

        if (senha == null || senha.length == 0) {
            throw new IllegalArgumentException(
                    "Informe a senha!");
        }

        Usuario usuario = usuarioDAO.buscarPorUsuario(
                nomeUsuario.trim()
        );

        if (usuario == null) {
            return null;
        }

        if (!verificarSenha(senha, usuario.getSenha())) {
            return null;
        }

        // Não manter o hash no objeto autenticado
        usuario.setSenha(null);

        return usuario;
    }

    // Gerar hash para cadastrar uma senha
    public static String gerarHashSenha(char[] senha)
            throws Exception {

        if (senha == null || senha.length == 0) {
            throw new IllegalArgumentException(
                    "A senha não pode estar vazia!");
        }

        byte[] salt = new byte[TAMANHO_SALT];
        new SecureRandom().nextBytes(salt);

        byte[] hash = calcularHash(senha, salt, ITERACOES);

        return "pbkdf2$" + ITERACOES + "$"
                + Base64.getEncoder().encodeToString(salt)
                + "$"
                + Base64.getEncoder().encodeToString(hash);
    }

    // Verificar senha
    private boolean verificarSenha(
            char[] senha, String senhaArmazenada)
            throws Exception {

        if (senhaArmazenada == null) {
            return false;
        }

        String[] partes = senhaArmazenada.split("\\$");

        if (partes.length != 4 || !partes[0].equals("pbkdf2")) {
            return false;
        }

        try {
            int iteracoes = Integer.parseInt(partes[1]);

            if (iteracoes < 100000 || iteracoes > 1000000) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(partes[2]);
            byte[] hashOriginal = Base64.getDecoder().decode(partes[3]);

            byte[] hashCalculado = calcularHash(
                    senha, salt, iteracoes
            );

            return MessageDigest.isEqual(
                    hashOriginal, hashCalculado
            );

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Calcular PBKDF2
    private static byte[] calcularHash(
            char[] senha, byte[] salt, int iteracoes)
            throws Exception {

        PBEKeySpec especificacao = new PBEKeySpec(
                senha, salt, iteracoes, TAMANHO_HASH
        );

        try {
            SecretKeyFactory fabrica =
                    SecretKeyFactory.getInstance(
                            "PBKDF2WithHmacSHA256"
                    );

            return fabrica.generateSecret(
                    especificacao
            ).getEncoded();

        } finally {
            especificacao.clearPassword();
        }
    }
}
