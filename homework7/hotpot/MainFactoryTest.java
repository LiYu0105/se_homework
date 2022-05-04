import static org.junit.jupiter.api.Assertions.*;

public class MainFactoryTest {
    public static void main(String[] args) {
        new OrderHotpot(new SimpleFactory());
    }
}