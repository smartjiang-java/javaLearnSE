package jqk.learn.special.design;

/**
 * @Author:JQK
 * @Date:2020/12/23 15:20
 * @Package:jqk.learn.special.design
 * @ClassName:AdapterMode 适配器模式
 **/

public class AdapterMode {
    public static void main(String[] args) {

        Second second = new Second() {
            @Override
            public void secondMethod() {
                System.out.println("Second.secondMethod");
            }
        };
        First first = new FirstAndSecondAdapter(second);
        first.firstMethod();
    }

}

interface First {
    void firstMethod();
}

interface Second {
    void secondMethod();
}

class FirstAndSecondAdapter implements First, Second {
    private Second second;

    public FirstAndSecondAdapter(Second second) {
        this.second = second;
    }

    @Override
    public void firstMethod() {
        second.secondMethod();
    }

    @Override
    public void secondMethod() {

    }
}