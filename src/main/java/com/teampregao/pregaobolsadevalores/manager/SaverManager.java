package com.teampregao.pregaobolsadevalores.manager;

import com.teampregao.pregaobolsadevalores.ed.*;
import com.teampregao.pregaobolsadevalores.entidades.*;

import java.io.*;

public class SaverManager {
    private final String storagePath = "database.txt"; // Defina o caminho do arquivo de armazenamento.
    private final int TAMANHO_CLASSES = 9;
    public final Fila<ListaEncadeada<Object>> insert;

    public void insert(String value, Type type) {
        String[] strings = read().split("\n");
        String[] strings1 = new String[TAMANHO_CLASSES];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath, false))) {
            for (int i = 0; i < TAMANHO_CLASSES; i++) {
                strings1[i] = (i >= strings.length)?"":strings[i];
            }
            strings1[(type.type - 1)] += value;
            clean();
            for (String s :
                    strings1) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                string.append(linha).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return string.toString();
    }

    public String read(Type type) {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            for (int i = 0; i < type.type - 1; i++) {
                br.readLine();
            }
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read(Type type, int id) {
        id = id - 1;
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            for (int i = 0; i < type.type - 1; i++) {
                br.readLine();
            }
            String s = br.readLine();
            if (s == null || s.length() == 0){
                return null;
            } else {
                return s.substring(id * type.maxLenght, id * type.maxLenght + type.maxLenght);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Id id, String newValue) {
        int idNew = id.getId() - 1;
        String line = read(id.getType());
        String newLine = "";
        System.out.println("linha: " + line);
        System.out.println("nova linha " + newValue);
        try {
            newLine = line.replace(line.substring(idNew * id.getType().maxLenght, idNew * id.getType().maxLenght + id.getType().maxLenght), newValue);
        }catch (Exception e){
            e.printStackTrace();
        }

        String[] strings = read().split("\n");
        String[] strings1 = new String[TAMANHO_CLASSES];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath, false))) {
            for (int i = 0; i < TAMANHO_CLASSES; i++) {
                strings1[i] = (i >= strings.length)?"":strings[i];
            }
            strings1[id.getType().type - 1] = newLine;
            clean();
            for (String s :
                    strings1) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void delete(Id id) {
        String line = read(id.getType());
        var newLine = line.replace(line.substring(id.getId() * id.getType().maxLenght, id.getId() * id.getType().maxLenght + id.getType().maxLenght), "");

        String[] strings = read().split("\n");
        String[] strings1 = new String[TAMANHO_CLASSES];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath, false))) {
            for (int i = 0; i < TAMANHO_CLASSES; i++) {
                strings1[i] = (i >= strings.length)?"":strings[i];
            }
            strings1[id.getType().type - 1] = newLine;
            clean();
            for (String s :
                    strings1) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clean() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath, false))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(){
        while (!insert.isEmpty()){
            ListaEncadeada<Object> obj = insert.dequeue();
            insert((String) obj.get(0), (Type) obj.get(1));
        }
    }

    public void addInsert(String s, Type t){
        ListaEncadeada<Object> obj = new ListaEncadeada<>();
        obj.add(s);
        obj.add(t);
        insert.enqueue(obj);
    }


    public SaverManager() {
        // Cria o arquivo de armazenamento se ele não existir.
        insert = new Fila<>();
        File file = new File(storagePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
