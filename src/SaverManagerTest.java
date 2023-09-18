import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaverManagerTest {
    @Test
    public void insertTest(){
        SaverManager saverManager = new SaverManager();
        saverManager.clean();
        Corretora corretora = new Corretora("Corretora A");
        saverManager.insert(EntityManager.lineCorretora(corretora), Type.CORRETORA);
        Corretora corretoraB = new Corretora("Corretora B");
        saverManager.insert(EntityManager.lineCorretora(corretoraB), Type.CORRETORA);
    }

}