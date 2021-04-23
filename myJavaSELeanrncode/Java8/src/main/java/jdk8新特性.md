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
中间操作不会有任何的结果
/**
*  筛选和切片
*   filter() --接受Lambda表达式,从流中排除某些元素
*   limit(n) --截断流,使其元素不超过n
*   skip(n)  --跳过元素,返回一个跳过前n个元素的流;若流元素不足n个,则返回一个空,与limit()互补\
*   distinct()--通过元素的hashcode()和equals()方法,去除重复元素;若元素为对象类型,需要重写hashcode()和equasl()方法
*/
    /**
    *  映射
    *  map     --接收lamdba,将元素转换成其他形式或提取信息.接受一个函数作为参数,该函数会被应用到每个元素上,并将其映射称一个新的元素
    *          对函数的要求:入参是元素,要有返回值
    *  flatmap --接收一个函数作为参数,将流中的每个值都替换成另一个流,然后把所有流连接成一个流.
    */
       /**
      *  排序
      *  sorted()                 --自然排序   Comparable # int compareTo(T o);
      *  sorted(Comparator com)   --定制排序   int compare(T o1, T o2);
         */
         /**
        * 终止操作:查找与匹配
        *  allMatch     --检查是否匹配所有元素(所有的都要满足条件)6
        *  anyMatch     --检查是否至少匹配一个元素(有一个满足条件就行)
        *  noneMatch    --检查是否没有匹配所有元素(所有的都不满足条件)
        *  findFirst    --返回第一个元素
        *  findAny      --返回当前流中的任意元素
        *  count        --返回流中元素的总个数
        *  max          --返回流中最大值
        *  min          --返回流中最小值
           */
         /**
          *  规约:可以将流中元素反复结合起来,得到一个值 ,T t代表起始值,不传默认从第一个开始
          *  注意:map和reduce统称为map-reduce模式,因Google用它来进行网络搜索而出名
          *  reduce (T t,BinaryOperator bo)
          *  reduce (BinaryOperator bo)
             */
             /**
         *  收集
         *  collect  --将流转化为其他形式,接收一个Collector接口的实现,用于给Stream中元素做汇总
         *  Collectors实用类提供了很多静态方法,可以方便的创建收集器实例
            */
   
         
4:便于并行
注意： 并行流和顺序流
并行流就是将一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流，底层用的是fork/join
java 8 将并行就行了优化，可以很容易的对数据进行并行操作。Stream API 可以声明性的通过 parallel() 和 sequential() 在并行流和顺序流之间进行切换。


5:最大化减少空指针异常 Optional 
Optional<T> 是一个容器类，代表一个值存在还是不存在，原来用null表示一个值不存在，现在Optional可以个更好的表达这个概念，并且可以避免空指针异常。
常见方法：
Optional.of(T t)：根据传入的值创建一个Optional实例
Optional.empty():创建一个空的Optional实例
Optional.ofNullable(T t):若 t 不为null,创建Optional实例，否则创建空实例。
Optional.isPresent():判断是否包含值
Optional.get（）：获取Optional中的值
Optional.orElse(T t):如果调用对象包含值，返回该值，否则返回t
Optional.orElseGet(Supplier s):如果调用对象包含值，返回改值，否则返回s
Optional.map(Function f):如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
Optional.flatMap(Function mapper):与Optional.map(Function f)类似，要求返回值必须是Optional


6:接口中的默认方法和静态方法
 jdk 8 以前，接口中只有成员，一个是抽象方法，一个是全局静态常量
1：jdk 8,允许接口中有默认实现的方法，default 方法
注意：
     若一个接口中定义了一个默认方法，而另外的一个父类或接口中有定义了一个同名的方法时。
     会产生接口冲突，那么必须覆盖该方法来解决冲突
2：可以有静态方法，默认通过接口.方法名调用


7：全新的时间和日期 API 
java 8以前的时间和日期API不好用,存在线程安全问题;新的API 在time包下,是线程安全的.
* LocalDate、LocalTime、LocalDateTime类的实例示不可变的对象，分别表示使用ISO-8601日历系统的日期、时间、日期和时间。
  它们提供了简单的日期和时间，并不包含当前的时间信息，也不包含与时区相关的信息。
* TemporalAdjuster: 时间校正器。有时我们可能需要获取例如：将日期调整到 "下个周日" 等操作。
  TemporalAdjusters：该类通过静态方法提供了大量的常用 TemporalAdjuster 的实现。
* DateTimeFormatter: 格式化时间/日期


8:重复注解与类型注解
 java 8对注解也做了两点改进：可重复注解及可用于类型的注解 TYPE_PARAMETER(对类型进行注解)。

