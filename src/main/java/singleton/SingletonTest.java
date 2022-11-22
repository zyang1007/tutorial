package singleton;

import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    public void testWithLazyLoad() {
        LazyLoading_Singleton instance = LazyLoading_Singleton.getInstance();
        LazyLoading_Singleton anotherInstance = LazyLoading_Singleton.getInstance();

        System.out.println(instance == anotherInstance);
    }
}
