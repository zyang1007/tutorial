package factory.config_factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class CoffeeFactory {

    private static final HashMap<String, Coffee> map = new HashMap<>();

    static {
        Properties p = new Properties();
        InputStream inputStream = CoffeeFactory.class.getClassLoader().getResourceAsStream("bean.propertities");

        try {
            p.load(inputStream);
            Set<Object> keys = p.keySet();

            for (Object key : keys) {
                String className = p.getProperty((String) key);
                Class<?> clazz = Class.forName(className);
                Coffee coffee = (Coffee) clazz.getDeclaredConstructor().newInstance();
                map.put((String) key, coffee);
            }

        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Coffee createCoffee(String name) {
        return map.get(name);
    }
}
