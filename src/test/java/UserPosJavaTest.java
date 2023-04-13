import dao.UserPosDAO;
import model.UserPosJava;
import org.junit.Test;

import java.util.List;

public class UserPosJavaTest {

    @Test
    public void realizarTestes() {
        //insertTest();
        obterTodosTest();
        obterPorIdTest();
        //atualizarTest();
        //excluirTest();
    }

    public void insertTest() {
        UserPosDAO userPosDAO = new UserPosDAO();
        UserPosJava userPosJava = new UserPosJava("Pedro", "pedro@gmail.com");
        userPosDAO.salvar(userPosJava);
    }

    public void obterTodosTest() {
        UserPosDAO userPosDAO = new UserPosDAO();
        List<UserPosJava> users = userPosDAO.obterTodos();
        for (UserPosJava user : users) {
            System.out.println(user);
        }
    }

    public void obterPorIdTest() {
        UserPosDAO userPosDAO = new UserPosDAO();
        UserPosJava userPosJava = userPosDAO.obterPorId(1L);
        System.out.println("Obter por id: " + userPosJava);
    }

    public void atualizarTest() {
        UserPosDAO userPosDAO = new UserPosDAO();
        UserPosJava user = userPosDAO.obterPorId(2L);
        user.setNome("Pedro Alterado");
        user.setEmail("pedro@alterado.com");
        userPosDAO.atualizar(user);
    }

    @Test
    public void excluirTest() {
        UserPosDAO userPosDAO = new UserPosDAO();
        userPosDAO.excluir(userPosDAO.obterPorId(3L).getId());
    }
}

