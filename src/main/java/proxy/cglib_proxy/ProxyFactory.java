package proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;  // add maven dependency
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {

    private final TrainStation trainStation = new TrainStation();

    public TrainStation getProxyObject() {
        Enhancer enhancer = new Enhancer();  // creates an Enhancer object
        enhancer.setSuperclass(TrainStation.class);  // sets the class of its parent's class
        enhancer.setCallback(this);  // sets the callback function

        return (TrainStation) enhancer.create();  // creates and then return the proxy object
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB dynamic proxy method!");
        return method.invoke(trainStation, objects);
    }
}
