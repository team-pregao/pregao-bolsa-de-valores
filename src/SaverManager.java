import java.io.*;

public class SaverManager {
    private final int type;
    private final String storagePath = "database.txt"; // Defina o caminho do arquivo de armazenamento.

    public void insert(String value) {
        String[] strings = read().split("\n");
        String[] strings1 = new String[4];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath, false))) {
            for (int i = 0; i < 4; i++) {
                strings1[i] = (i >= strings.length)?"":strings[i];
            }
            strings1[type] += value;
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

    public String readInvestidores() {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readEmpresas() {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            br.readLine();
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readAcoes() {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            br.readLine();
            br.readLine();
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readInvestidores(int id) {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            string.append(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return string.substring(id * 65, id * 65 + 65);
    }

    public String readEmpresas(int id) {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            br.readLine();
            string.append(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return string.substring(id * 53, id * 53 + 53);
    }

    public String readAcoes(int id) {
        StringBuilder string = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(storagePath))) {
            br.readLine();
            br.readLine();
            string.append(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return string.substring(id * 24, id * 24 + 24);
    }

    public void update(String key, String newValue) {
        try (BufferedReader reader = new BufferedReader(new FileReader(storagePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath ))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(key)) {
                    writer.write(key + ":" + newValue);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Renomeia o arquivo temporário para substituir o original.
        File originalFile = new File(storagePath);
        File tempFile = new File(storagePath + ".tmp");
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Atualização concluída com sucesso.");
        } else {
            System.out.println("Falha na atualização.");
        }
    }

    public void delete(String key) {
        // Lê todas as linhas, exceto aquela que corresponde à chave a ser excluída, e escreve de volta ao arquivo.
        try (BufferedReader reader = new BufferedReader(new FileReader(storagePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath + ".tmp"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (!(parts.length == 2 && parts[0].equals(key))) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Renomeia o arquivo temporário para substituir o original.
        File originalFile = new File(storagePath);
        File tempFile = new File(storagePath + ".tmp");
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Exclusão concluída com sucesso.");
        } else {
            System.out.println("Falha na exclusão.");
        }
    }

    public void clean() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath, false))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveData(){

    }

    public SaverManager(int type) {
        this.type = type;
        // Cria o arquivo de armazenamento se ele não existir.
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
