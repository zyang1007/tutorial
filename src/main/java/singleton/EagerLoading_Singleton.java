package singleton;

public class EagerLoading_Singleton {
    private static EagerLoading_Singleton instance = new EagerLoading_Singleton();

    public static EagerLoading_Singleton getInstance() {
        return instance;
    }
}
