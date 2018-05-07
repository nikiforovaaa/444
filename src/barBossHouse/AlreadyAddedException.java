package barBossHouse;

public class AlreadyAddedException extends IllegalArgumentException {
    public AlreadyAddedException (String ex) {
        super(ex);
    }
}
