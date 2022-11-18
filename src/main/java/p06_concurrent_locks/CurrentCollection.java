package p06_concurrent_locks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Interface        Non-Thread-Safe            Thread-Safe
 * 1. List          ArrayList                  CopyOnWriteArrayList
 * 2. Map           HashMap                    ConcurrentHashMap
 * 3. Set           HashSet/TreeSet            CopyOnWriteArraySet
 * 4. Queue         ArrayQueue/LinkedList      ArrayBlockingQueue/LinkedBlockingQueue
 * 5. Deque         ArrayDeque/LinkedList      LinkedBlockingDeque
 */
public class CurrentCollection {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>(10);

        map.put("A", "1");
        map.put("B", "2");

        String a = map.get("A");

        /**
         * This actually wraps a non-thread-safe map with a wrapper class, and then locks all read and write methods
         * with synchronized.
         * The performance of thread-safe collections obtained in this way is much lower than that of
         * java.util.concurrent collections, so it is not recommended to use.
         */
        Map<Object, Object> unsafeMap = new HashMap<>(10);
        Map<Object, Object> threadSafeMap = Collections.synchronizedMap(unsafeMap);
    }
}
