package jqk.learn.special.design;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:JQK
 * @Date:2020/12/23 16:36
 * @Package:jqk.learn.special.design
 * @ClassName:ProxyPattern 代理模式
 * 场景：买火车票，如果你没在12306的APP上购买了携程，去哪网的APP
 **/

public class ProxyPattern {
    public static void main(String[] args) {
        Station station = new Station();
 /*       Persion persion = new Persion();
        persion.action(station);*/

        //使用静态代理模式
  /*      GoWhereApp goWhereApp = new GoWhereApp();
        persion.action(goWhereApp);*/

        //使用动态代理  类加载器  放类实现的接口
        StationI stationI= (StationI) Proxy.newProxyInstance(ProxyPattern.class.getClassLoader(), new Class[]{StationI.class},
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               //注意：proxy和外层的stationI是一样的对象,很容易引发StackOverflowError
               Object invoke = method.invoke(station, null);
                return invoke;
            }
        });
        stationI.saleTicket();
    }
}



interface  StationI{
    void saleTicket();
}

class Station implements  StationI {
    private int ticketCount = 100;

    @Override
    public void saleTicket() {
        --ticketCount;
        System.out.println("sale one ticket,剩余：" + ticketCount);
    }
}

class Persion {
    public void action(Station station) {
        station.saleTicket();
        System.out.println("buy success");
    }
    public void action(GoWhereApp goWhereApp) {
        goWhereApp.saleTicket();
        System.out.println("buy success");
    }

}

/**
 * 静态代理
 */
class GoWhereApp {
    private Station station = new Station();

    public void saleTicket() {
        System.out.println("扣掉一元钱");
        //顺带记录日志
        station.saleTicket();
    }
}

/**
 * 动态代理：
 * JDK动态代理：需要被代理的类实现接口
 * CGLIB代理
 */

