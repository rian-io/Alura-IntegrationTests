package br.com.alura.leilao.teste;

import br.com.alura.leilao.dao.UsuarioDao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsarioDaoTest {

    private EntityManager em;

    private UsuarioDao dao;

    @BeforeEach
    void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);

        this.em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        this.em.getTransaction().rollback();
        this.em.clear();
        this.em.close();
    }

    @Test
    void testarBuscaPorUsernameValido() {
        Usuario usuario = createUsuario();
        Usuario encontrado = dao.buscarPorUsername(usuario.getNome());
        Assertions.assertNotNull(encontrado);
    }

    @Test
    void testarBuscaPorUsernameInvalido() {
        Assertions.assertThrows(NoResultException.class, () -> dao.buscarPorUsername("Fulano"));
    }

    @Test
    void testarDeletarUsuarioValido() {
        Usuario usuario = createUsuario();
        dao.deletar(usuario);

        Assertions.assertThrows(NoResultException.class, () -> dao.buscarPorUsername(usuario.getNome()));
    }

    private Usuario createUsuario() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
        em.persist(usuario);
        return usuario;
    }
}
