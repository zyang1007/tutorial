package singleton;

public class LazyLoading_Singleton {
    // uses volatile causes the modified value to be immediately updated to the main program
    // so that to avoid NULL Pointer Exception.
    private static volatile LazyLoading_Singleton instance;

    // private constructor prevents creation with keyword new
    private LazyLoading_Singleton() {}

    public static LazyLoading_Singleton getInstance() {  // synchronized
        if (instance == null) {
            synchronized (LazyLoading_Singleton.class) {  // ensure thread safe
                if (instance == null) {
                    instance = new LazyLoading_Singleton();
                }
            }
        }

        return instance;
    }
}
