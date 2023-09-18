package ed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaEncadeadaTest {

    @Test
    void addTest() {
        ListaEncadeada<Integer> list = new ListaEncadeada<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(2, 9);
        list.add(3, 7);
        list.add(5, 6);
        System.out.println(list);
        assertEquals(list.toString(), "{ 2  3  9  7  4  6 }");
    }
    @Test
    void addStringTest() {
        ListaEncadeada<String> list = new ListaEncadeada<>();
        list.add("2");
        list.add("3");
        list.add("4");
        list.add(2, "9");
        list.add(3, "7");
        list.add(5, "6");
        System.out.println(list);
        assertEquals(list.toString(), "{ 2  3  9  7  4  6 }");
    }

    @Test
    void removeTest(){
        ListaEncadeada<Integer> list = new ListaEncadeada<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list); // {2 3 4 5 6 7};
        list.remove(2); // {2 3 5 6 7}
        list.remove(0); // {3 5 6 7}
        list.remove(3); // {3 5 6}
        assertEquals(list.toString(), "{ 3  5  6 }");
    }

    @Test
    void getTest(){
        ListaEncadeada<Integer> list = new ListaEncadeada<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list);

        assertEquals(list.get(3), 5);
        assertEquals(list.get(0), 2);
        assertEquals(list.get(5), 7);
        assertNull(list.get(16));
    }

}