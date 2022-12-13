package br.com.alura.leilao.teste;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.LeilaoBuilder;
import br.com.alura.leilao.util.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoDaoTest {

    private EntityManager em;

    private LeilaoDao dao;

    @BeforeEach
    void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);

        this.em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        this.em.getTransaction().rollback();
        this.em.clear();
        this.em.close();
    }

    @Test
    void testarInserirLeilao() {
        Leilao leilao = createLeilao();

        Leilao salvo = dao.buscarPorId(leilao.getId());
        Assertions.assertNotNull(salvo);
    }

    @Test
    void testarAtualizarLeilao() {
        Leilao leilao = createLeilao();

        leilao.setNome("celular");
        leilao.setValorInicial(new BigDecimal("400"));

        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());

        Assertions.assertEquals("celular", salvo.getNome());
        Assertions.assertEquals(new BigDecimal("400"), salvo.getValorInicial());
    }

    private Usuario createUsuario() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);
        return usuario;
    }

    private Leilao createLeilao() {
        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500")
                .comData(LocalDate.now())
                .comUsuario(createUsuario())
                .criar();

        leilao = dao.salvar(leilao);
        return leilao;
    }
}
