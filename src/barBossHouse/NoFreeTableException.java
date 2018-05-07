package barBossHouse;

public class NoFreeTableException extends IllegalArgumentException {
    public NoFreeTableException (String ex) {
        super(ex);
    }
}
