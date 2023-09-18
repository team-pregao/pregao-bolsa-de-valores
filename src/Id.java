public class Id {
    private final int id;
    private final Type type;

    public Id(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    public Id(Type type) {
        this.id = getIdAutoIncremente(type);
        this.type = type;
    }

    private int getIdAutoIncremente(Type type) {
        SaverManager saverManager = new SaverManager();
        int count = 0;
        String currenteLine = saverManager.read(type);
        if (currenteLine == null){
            return 1;
        }
        return currenteLine.length() / type.maxLenght + 2;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }
}