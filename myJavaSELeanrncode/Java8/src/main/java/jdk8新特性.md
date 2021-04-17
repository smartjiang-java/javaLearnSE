                                       jdk8新特性

1:速度更快
数据结构的改变: HashMap 1,8之前,key经过hash后,可能会产生碰撞,再去比较key,不同的话,通过链表连接.  扩容因子,0.75,太小了浪费空间,
                      存满了也不太现实,因为可能有一个位置就是没有元素进去.扩容的话,会把元素重新进行hash,再去排位置.
                      1.8之后,当链表数量>8,并且hashMap大于64,会把链表变成红黑树,除了添加以外,其它的比如搜索都变快了,
                      扩容后,无需重新hash值,只需移动到原来hash表长度+当前位置即可.
              HashSet 也变了,底层也是HashMap.
              ConCurrentHashMap 也变了,1.7之前的并发级别默认是16,在这个1.7时,采用锁分段机制,默认16个段,每个段也是一个小hashmap
                      1.8变成了CAS算法.
底层内存结构的改变: 1.7之前,方法区是堆中永久区的一部分,永久区存的是类信息,几乎不会被垃圾回收机制回收,条件比较苛刻.,当方法区快会满的时候
                  1.8之后,永久区被干掉了,有一块新的空间,MetaSpace元空间,使用直接内存,而不是JVM分配的,几乎不会被GC回收,降低了方法区OOM


2:代码更少(Lambda表达式:接口的实现类的对象)
()->{}
Lambda表达式需要函数时接口的支持, 函数式接口有且只有一个抽象方法
(ps:重写了超类Object类中任意一个public方法的方法并不算接口中的抽象方法,例如Comparator接口)
可以用@FunctionalInterface修饰,检查是不是函数式接口

四大内置函数式接口:
Consumer<T>        #      void accept(T t)             :消费型接口
Supplier<T>        #      T get()                      :供给型接口
Function<T, R>     #      R apply(T t);                :函数型接口,用于对象的转换
Predicate<T>       #      boolean test(T t)            :断言型接口,用于做一些判断操作

方法引用:Lambda 体中的内容已经有方法实现了,可以使用方法引用
主要有三种语法格式:
对象 :: 实例方法名
类 :: 静态方法名
类 :: 实例方法名  (x,y)->x.equals(y)   第一个参数是方法的调用者,第二个参数是实例方法的参数
BiPredicate <String ,String > biPredicate=(x,y)->x.equals(y);
BiPredicate <String ,String > bp=String::equals;

构造器引用
ClssName :: new    根据函数式接口的抽象方法的参数列表自动匹配ClassName的构造器

数组引用
Type :: new    ,例如 String [] :: new


3:强大的Stream API 
stream是Java8中处理集合,处理数组.过程中,可以进行一系列流水线的中间操作.数据源是不会被改变的,会产生一个新流.
stream的操作:创建stream,中间操作,终止操作(终端操作)

4:便于并行



5:最大化减少空指针异常 Optional 