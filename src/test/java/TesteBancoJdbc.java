import dao.UserPosDAO;
import model.UserPosJava;
import org.junit.Test;

import java.util.List;

public class TesteBancoJdbc {

    @Test
    public void initBanco() {
        //insertTest();
        obterTodosTest();
        obterPorIdTest();
    }

    public void insertTest() {
        UserPosDAO userPosDAO = new UserPosDAO();
        UserPosJava userPosJava = new UserPosJava(2L, "Pedro", "pedro@gmail.com");
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
}
