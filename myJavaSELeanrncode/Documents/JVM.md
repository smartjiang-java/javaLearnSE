#  目录

## 一：JVM基础知识

### 1：什么是JVM？
JVM是一种规范,人们常说Java是跨平台的语言,而JVM帮助屏蔽了不同操作系统的底层，是跨语言的平台．  
它不仅仅应用于Java语言，它是可以认识所有能编译成.class格式的文件（二进制字节码文件）．

### 2：好处
* 一次编写，到处运行的基石：java通过JVM 跨平台，JVM 屏蔽了字节码与底层操作系统之间的差异，对外提供了一个一致的运行环境。JVM解释执行字节码
* 自动内存的管理机制，垃圾回收功能：虽然现在很多语言都有这个功能，但java出现较早，竞争对手是C，C++，无需手动释放内存
* 数组下标越界的检查：抛出异常，C语言没有数组下标越界检查，一旦数组越界，可能覆盖其他代码的内存。
* 多态：面向对象编程的基石：java使用虚方法表的方式来实现多态

### 3：JVM和JDK,JRE的关系(面试)

![binaryTree](tmp/jvm-image/JVM和JDK,JRE的关系.png.jpg "binaryTree")

### 4：常见的JVM

![binaryTree](tmp/jvm-image/常见的JVM.png "binaryTree")

其中，我们目前安装JDk使用的是HotSpot,这个是oracle官方那个是用的,有很多实力公司不依赖oracle公司的这个,他们会根据JVM规范自己实现一些虚拟机,  
比如淘宝的是TaobaoVM(免费,Java虚拟机收费大家可以可以使用这个),还有商业版本收费，垃圾回收最快的azul zing(有钱公司使用). 所以说:一流的公司制造规范,二流的公司实现规范,三流的公司使用规范.

### 5: JVM的学习步骤
1: 类加载器  
2：JVM内存模型  
3：执行引擎：解释器，即时编译器（JIT编译热点代码），GC垃圾回收    
4：操作系统：本地方法接口


## 二：JVM 内存模型

### 1：程序计数器：Program Counter Register

![binaryTree](tmp/jvm-image/程序计数器.png "binaryTree")
* 作用：通过寄存器来实现，记录下一条JVM指令的执行地址。方便线程上下文切换,记录线程执行到那条指令
* 特点：线程私有的。JVM中，唯一一个不会出现内存溢出的区。

### 2：虚拟机栈：线程运行需要的内存空间，线程私有  参数-Xss控制,在1MB左右
栈：先进后出  
可以通过参数-Xss控制，windows系统受虚拟内存影响，其他系统默认1MB。  
一个栈由多个栈帧组成。一个战帧对应一次方法的调用，栈帧是方法运行时需要的内存。  
注意：每个线程只能有一个活动栈帧，对应着正在执行的那个方法。  
栈帧(主要四个部分组成)：
*  Local Variable Table:本地变量(局部变量)表,方法内部使用的,参数也算在内，以变量槽为最小单位，每个槽32位的内存空间。  
   局部变量表主要用于存储方法参数和定义在方法体内的局部变量，这些数据类型包括各类原始数据类型、对象引用(reference)，以及returnAddress类型。  
   局部变量表所需的容量大小在编译期就可以被完全确定下来，并保存在方法的Code属性中。
*  Operand Stack:操作数栈(表达式栈)
   对于long的处理（store and load），多数虚拟机的实现都是原子的  
   局部变量，没必要加volatile，线程私有的。  
   操作数栈所需的容量大小在编译期就可以被完全确定下来，并保存在方法的Code属性中。通过标准的出入栈完成数据访问  
   32位数据类型所占的栈容量为1，64位数据类型所占的栈容量为2。
* Dynamic Linking:动态链接,指向常量池的符号链接,如果没有解析,就去动态解析
  https://blog.csdn.net/qq_41813060/article/details/88379473
  jvms 2.6.3
* return address:返回值地址
  a() -> b()，方法a调用了方法b, b方法的返回值放在什么地方

问题：
--垃圾回收是否涉及栈内存？ 不需要，方法执行完会弹栈
--栈内存分配越大越好吗？  栈越大，会导致线程数变少;大了可以更多次方法的调用
--方法内的局部变量是否线程安全？ 如果方法内局部变量没有逃离方法的作用范围，那么就是线程安全；如果变量引用了对象，并逃离范围，需要考虑安全问题。

栈内存溢出： Java.lang.StackOverflowError
* 栈帧过多，比如多次的递归调用，这个比较常见;比如两个类循环依赖,json解析时可能也会出现
* 栈帧过大，这个很少发生。

线程运行诊断:
案例1:CPU占用过多
linux中 top命令 可以查看进程pid
ps H -eo pid,tid,%cpu | grep pid :会把linux中那个进程的线程的进程号(10进制)和CPU显示出来
jstack pid :显示这个进程下所有进程的运行情况,根据上面的10进制线程号换算到16进制,找到对应线程nid,查看出现问题的代码行数
案例2:程序运行很长时间没有结果：有可能是线程死锁导致   
jstack pid：显示运行信息，观察最下面的 java-level deadlock

### 3:本地方法桟:给本地方法提供的内存空间,线程私有的
本地方法 native method:C或C++编写的与操作系统交互的方法.

### 4:堆 heap:线程共享, -Xmx 参数控制堆空间最大值
通过new关键字创建对象都会使用堆内存.

特点:
--线程共享,堆中对象都需要考虑线程安全问题
--有垃圾回收机制

堆内存溢出:java.lang.OutOfMemoryError: Java heap space

堆内存诊断:
--jps工具:查看当前系统中有哪些java程序   
命令行 jps ,找到进程 id  
--jmap工具:查看某个时刻堆内存占用情况  
命令行 jmap -heap pid
--jconsole工具:图形界面的,多功能的监测工具,可以连续监测  
命令行 jconsole ,选择连接进程
--jvisualvm 工具:可视化工具  
命令行 jvisualvm,选择连接的项目,堆 dump,

**案例:
垃圾回收后,内存占用依然很高.
大对象,不停的添加对象

### 5:方法区 Method Area  线程共享 -XX:MaxMetaspaceSize控制,默认无上限
存储类相关信息 和运行时常量池,串池(1.8之前),逻辑上方法区是堆的组成部分,但实际上根据实现公司的不同可能会不一样
jdk 1.8 之前,方法区叫做永久代(PermGen space),使用的就是堆的一部分. 字符串常量位于PermSpace,FGC不会清理
jdk 1.8之后,移除了永久代,使用了元空间(Metaspace),不再是堆空间的一部分,使用的本地内存,默认不设置上限,  
字符串常量位于堆,会触发FGC清理 不设定的话，最大就是物理内存

![binaryTree](tmp/jvm-image/堆和方法区.png "binaryTree")

方法区内存溢出:  
1.8之后: java.lang.OutOfMemoryError: Metaspace  
1.8之前: java.lang.OutOfMemoryError: PermGen space

** 案例
spring或者mybatis使用cglib动态代理,动态生成类的字节码,动态的类加载.在运行期间会加载大量的类,在1.8之前很可能会溢出;1.8之后容错率增大.

运行时常量池  
二进制字节码(类基本信息,常量池,类方法定义(包含了虚拟机指令))  
常量池:就是一张表,虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量信息  
运行时常量池:常量池是.class文件中的，当该类被加载以后，它的常量池信息就会放入运行时常量池，并把里面的符号地址变为真实地址

StringTable  
串池,数据结构是HashTable,一开始是固定的,并且是不能是扩容的.  
1.8之前位于永久代,full GC才会触发垃圾回收,导致StringTable的回收效率并不高,内存不足就是永久代内存不足  
java.lang.OutOfMemoryError: PermGen space  
1.8之后在堆中,Minor GC就可以触发垃圾回收,内存不足就是堆内存不足 (符号表也在堆中)  
java.lang.OutOfMemoryError: GC overhead limit exceeded  ,  
需要把此参数关闭  -XX:-UseGCOverheadLimit,会显示 java.lang.OutOfMemoryError: Java heap space  
ldc,将一个符号引用变成字符串对象后,会到 StringTable里面查找,如果没有,就把刚才的字符串对象放入串池,否则就增加一个引用.

例子1:
```java
        // 懒惰的.用到了才会到StringTable里面创建,用不到不会提前创建的 ,s1,s2,s3放在串池中
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        // new StringBuilder().append("a").append("b").toString()  new String("ab")  放在堆中,两个变量拼接
        String s4 = s1 + s2;
        // javac 在编译期间的优化，结果已经在编译期确定为ab,两个常量拼接,先去串池中寻找,没有的话就添加到串池中.
        String s5 = "a" + "b";
```
例子2:
```java
        String x = "ab";

        // 堆  new String("a")   new String("b")    new String("ab"),仅存在堆中,动态拼接的,s是变量
        String s = new String("a") + new String("b");
        
        // 将s字符串对象尝试放入串池，如果有则并不会放入，如果没有则放入串池，会把串池中的对象返回
        String s2 = s.intern();

        System.out.println( s2 == x);  
        System.out.println( s == x );
```
常量池中的字符串仅是符号，只有在被用到时才会转化为对象  
利用串池的机制，来避免重复创建字符串对象  
字符串变量拼接的原理是StringBuilder,放在堆中  
字符串常量拼接的原理是编译器优化,放在串池中  
可以使用intern方法，主动将串池中还没有的字符串对象放入串池中  
1.8之后,将这个字符串对象尝试放入串池,如果有则不会放入,没有则把对象放入串池,会把串池中的对象返  
1.8之前,,将这个字符串对象尝试放入串池,如果有则不会放入,没有则把此对象复制一份放入串池,(不相等,是一个副本),会把串池中的对象返回  
注意：无论是串池还是堆里面的字符串，都是对象

垃圾回收:
HashTable 默认大小 60013,内存紧张时，会发生垃圾回收

性能调优:
* 增加StringTable的桶的个数，来减少字符串放入串池所需要的时间: -XX:StringTableSize=xxxx
* 考虑将字符串对象是否入池 ：如果有大量的字符串，且重复率很高，可以采用 s.intern() 入池，减少堆内存的使用，平常写代码可使用。

### 6:直接内存
常用于NIO操作时，用于数据缓冲区。   
分配回收成本较高，单读写性能高。  
不受JVM内存回收管理。  
直接内存是操作系统和Java代码都可以访问的一块区域，无需将代码从系统内存复制到Java堆内存，从而提高了效率  
Direct Memory:直接内存:用户空间可以访问os内存
> JVM可以直接访问的内核空间的内存 (OS 管理的内存)  
> NIO ， 提高效率，实现zero copy,以前需要将网络传到内存中的东西拷贝到JVM内存,现在省去拷贝时间,直接去访问

1.7之前java的读写流程

![binaryTree](tmp/jvm-image/java文件读取流程.png "binaryTree")

1.8之后使用直接内存的读写流程

![binaryTree](tmp/jvm-image/使用直接内存读取文件.png "binaryTree")

内存溢出:直接内存也是可能会溢出的

释放原理:
--使用了Unsafe类的allocateMemory方法来完成直接内存的分配回收，回收需要主动调用freeMemory方法,并非是由GC回收的  
--ByteBuffer的实现类内部使用了Cleaner（虚引用）来检测ByteBuffer对象。一旦ByteBuffer被垃圾回收，  
那么会由ReferenceHandler线程(守护线程)来调用Cleaner的clean方法调用freeMemory来释放内存  
-- 注意:-XX:+DisableExplicitGC  禁用显示的垃圾回收,让代码中的 System.gc()回收不起作用,则直接内存不会释放,必须等到真正GC的时候  
才会释放,在代码中,也不推荐使用 System.gc(),是Full GC,程序暂停时间比较长,最好管理直接内存的方式就是手动 Unsafe类释放.

源码分析:
```java
        //这里通过 ByteBuffer分配了 1GB 的直接诶内存
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1Gb);
        System.out.println("分配完毕...");
        System.in.read();
        System.out.println("开始释放...");
        //将 byteBuffer 引用置为null
        byteBuffer = null;
        System.in.read();
```
```java
/**
 分配一个新的直接字节缓冲区。
 新缓冲区的位置将为零，其极限将是其容量，其标记将是未定义的，并且其每个元素都将初始化为零。 不确定是否具有backing array 。
 参数：容量–新缓冲区的容量，以字节为单位
 返回值：新的字节缓冲区
 抛出：IllegalArgumentException如果容量为负整数
 */
    public static ByteBuffer allocateDirect(int capacity) {
        return new DirectByteBuffer(capacity);
    }
```
```java
/**
 * 构造器 
 * unsafe.allocateMemory(size);
 * unsafe.setMemory(base, size, (byte) 0);
 * 通过上面分配直接内存
 */
    DirectByteBuffer(int cap) {                   // package-private
    
        super(-1, 0, cap, cap);
        boolean pa = VM.isDirectMemoryPageAligned();
        int ps = Bits.pageSize();
        long size = Math.max(1L, (long)cap + (pa ? ps : 0));
        Bits.reserveMemory(size, cap);

        long base = 0;
        try {
            // 通过unsafe.allocateMemory() 分配内存
            base = unsafe.allocateMemory(size);
        } catch (OutOfMemoryError x) {
            Bits.unreserveMemory(size, cap);
            throw x;
        }
        unsafe.setMemory(base, size, (byte) 0);
        if (pa && (base % ps != 0)) {
            // Round up to page boundary
            address = base + ps - (base & (ps - 1));
        } else {
            address = base;
        }
        //Cleaner是虚引用类型，来实现直接内存的释放，this为虚引用的实际对象,如果 this为null,就会触发虚引用的clean方法
        //这里的回调方法是 new Deallocator(base, size, cap)    
        cleaner = Cleaner.create(this, new Deallocator(base, size, cap));
        att = null;
        
    }
```
```java
//Cleaner是虚引用类型的 clean()方法,调用回调方法的 run()方法
    public void clean() {
        if (remove(this)) {
            try {
                this.thunk.run();
            } catch (final Throwable var2) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        if (System.err != null) {
                            (new Error("Cleaner terminated abnormally", var2)).printStackTrace();
                        }

                        System.exit(1);
                        return null;
                    }
                });
            }

        }
    }
```
```java
//回调任务的 run()方法
  public void run() {
            if (address == 0) {
                // Paranoia
                return;
            }
            unsafe.freeMemory(address);
            address = 0;
            Bits.unreserveMemory(size, capacity);
        }
```

### 7:字节码常用指令
注意:如果方法是非static,局部变量表的0号位都是this,如果是静态方法,那么0号位一般都是参数  
bipush 压栈  
istore_1:将栈顶的值pop出来,放到局部变量表的1号位置  
iload_1:将局部变量表1号位的值拿出来压栈  
iinc 1 by 1:把局部变量表1号位置值加1  
iadd 在栈里拿出两条int值相加,结果放在栈顶  
dup:复制一份,一般执行构造方法的时候会消耗一个5  
invokespecial:执行特殊的方法,一般是init,构造方法,private 方法---可以直接定位，不需要多态的方法  
invokevirtual:调用方法,执行另一个栈帧,另一个栈帧执行结束,有返回值,会在栈顶放入这个值  
invokeStatic:调用静态方法  
invokeInterface:new对象时上转型,通过接口去调用方法  
InvokeDynamic:JVM最难的指令,1.7之后java支持动态语言后加入lambda表达式或者反射或者其他动态语言scala kotlin，或者CGLib ASM，动态产生的class，会用到的指令  
iconst_1 :把1这个立即数压栈  
imul:相乘  
clinit:类在初始化阶段调用的方法,将static变量赋定义值  
init::类构造方法  
sub:减法  
getstatic :获取一个静态变量  
ldc:加载一个引用地址,将一个符号引用变成字符串对象  
astore_1    把new出来的引用放进1号串池中  
aload_1     从1号串池中取出,放在栈顶  
checkcast  用来检验向下转型  
ACC_SUPER   :支持父类调用

### 8:相关JVM参数
虚拟机栈 可以通过参数-Xss控制，windows系统受虚拟内存影响，其他系统默认1MB。  
heap:线程共享, -Xmx 参数控制堆空间最大值  
-XX:MaxMetaspaceSize=8m  设置元空间大小默认使用直接内存,没有上限  
-XX:StringTableSize=xxxx  设置StringTable的桶的个数  
-XX:+DisableExplicitGC  禁用显示的垃圾回收,让代码中的 System.gc()回收


## 三：垃圾回收 GC
### 3.1：如何判断对象可以回收
3.1.1:什么是垃圾
C语言申请内存：malloc free C++： new delete c/C++ 手动回收,开发效率低,执行效率高.内存容易出两种类型的问题：   
忘记回收:内存泄露   
多次回收:非法访问   
Java: new ？ GC自动内存回收，编程上简单，系统不容易出错.开发效率高,执行效率低
  
没有任何引用指向的一个对象或者多个对象（循环引用）称为垃圾

3.1.2:如何定位垃圾
引用计数（ReferenceCount） 为0时进行回收 缺点：不能解决循环引用的问题（ABC互相引用，但这个整体却没有引用指向，合起来一堆垃圾，却无法回收）   
根可达算法(RootSearching) :扫描堆中的对象，看能否沿着GC Root对象为起点的引用链找到该对象，如果找不到，则表示可以回收   
注意:什么是根对象(GC roots)(重点)？   
JVM stack(桟帧中的局部变量表中引用的对象,方法中的变量)   
Runtime constant pool(方法区中运行时常量池的一些对象)   
Static references in method area(方法区内的静态引用对象和常量对象)   
Clazz:系统类对象,.由启动类加载器加载的,不会被回收掉,object,String,Hashmap类等   
Native Method stack(调用 native方法的对象) monitor:锁住的一些对象

```java
public class Demo2_8 {

    //1:静态对象和常量对象会被加载到方法区中,作为根对象
    static int i = 0;
    final int k = 2;

    public static void main(String[] args) {
        //2:方法中的常量会被放在桟帧中的局部变量中,作为根对象
        final int j = 1;
    }

    //3:调用native方法的对象
    //4:启动类加载器加载的系统类,如String,Object,Hashmap等类对象
    //5:运行时常量池中一些对象
    //6:synchroniced锁住的一些对象
}
```
3.1.3:强软弱虚 四种引用

![binaryTree](tmp/jvm-image/五种引用.png "binaryTree")

强引用 ：只有GC Root都不引用该对象时，才会回收强引用对象   
如上图B、C对象都不引用A1对象时，A1对象才会被回收

软引用(SoftReference)：当GC Root指向软引用对象时，在内存不足时，会回收软引用所引用的对象   
如上图如果B对象不再引用A2对象且内存不足时，软引用所引用的A2对象就会被回收   
注意：在回收软引用所指向的对象时，软引用本身不会被清理。如果想要清理软引用，需要使用引用队列。可以配合引用队列进行回收引用本身  
例子：软引用的使用
```java
public class Demo1 {
	public static void main(String[] args) {
		final int _4M = 4*1024*1024;
		//使用软引用对象 list和SoftReference是强引用，而SoftReference和byte数组则是软引用
		List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB]);
            list.add(ref);
        }
        //注意：这里遍历，如果内存不足，软引用对应的对象会被回收，这里遍历到的可能是null
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference.get());
        }
	}
}
```  
例子：软引用配合引用队列使用，目的是软引用指向的对象为null,把软引用本身也清除
```java
public class Demo1 {
	public static void main(String[] args) {
		final int _4M = 4*1024*1024;
		//使用引用队列，用于移除引用为空的软引用对象
		ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
		//使用软引用对象 list和SoftReference是强引用，而SoftReference和byte数组则是软引用
		List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 关联了引用队列， 当软引用所关联的 byte[]被回收时，软引用自己会加入到 queue 中去
            SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB], queue);
            list.add(ref);
        }
		//遍历引用队列，如果有元素，则移除
		Reference<? extends byte[]> poll = queue.poll();
		while(poll != null) {
			//引用队列不为空，则从集合中移除该元素
			list.remove(poll);
			//移动到引用队列中的下一个元素
			poll = queue.poll();
		}
	}
}
```

弱引用(WeakReference)：只有弱引用引用该对象时，在垃圾回收时，无论内存是否充足，都会回收弱引用所引用的对象  
如上图如果B对象不再引用A3对象，则A3对象会被回收。  
弱引用的使用和软引用类似，可以配合引用队列进行回收引用本身

虚引用(PhanotomReference)：必须配合引用队列使用。  
例子：配合直接内存使用，创建ByteBuffer实现对象时，分配一块直接内存，同时会创建一个Cleaner的虚引用对象，把直接内存的地址传给虚引用对象。  
    一旦ByteBuffer没有强引用了，会被回收，但是分配的直接内存没有被回收。这时候需要虚引用，进去引用队列，ReferenceHandler线程定时的  
    来引用队列里查看，如果有新入队的，调用Cleaner的clean方法调用freeMemory来释放内存（根据前面记录的直接内存地址）

终结器引用（FinalReference）：必须配合引用队列使用.第一次回收还不能回收掉，那个访问线程德优先级很低，并不能得到及时释放，这也是finalize效率低的原因  
所有的类都继承自Object类，Object类有一个finalize方法。当某个对象重写了finalize()方法，这个时候就有了一个终结器引用对象指向。如果这个对象没有  
没其他强引用指向时，会先将终结器引用对象加入到一个引用队列中，此时被引用对象没有被回收，由finalizeHandler低线程定时访问引用队列，  
根据终结器引用找到指向的对象，然后调用该对象的finalize()方法，调用以后，该对象就可以被回收了。

### 3.2：垃圾回收算法

1. 标记清除(mark sweep) - 适用于存活对象比较高的情况下   
   第一遍扫描是找到可回收的,进行标记,第二遍扫描是清除垃圾。  
   这里的腾出内存空间并不是将内存空间的字节清0，而是记录下这段内存的起始结束地址(放在地址列表里)，下次分配内存的时候，会直接覆盖这段内存  
   缺点：内存不连续，容易产生大量的内存碎片，可能无法满足大对象的内存分配，一旦导致无法分配对象，那就会导致jvm启动gc，  
   一旦启动gc，我们的应用程序就会暂停，这就导致应用的响应速度变慢。效率偏低(两边扫描)。  
   优点：算法简单，回收速度相对较快。

2. 标记整理(mark compact) 标记压缩-
   第一遍扫描是找到可回收的,进行标记,第二遍扫描是清除垃圾，并将存活对象移动。  
   优点：没有内存碎片  
   缺点：效率偏低（两遍扫描，指针需要调整）,需要移动对象，速度较慢

3. 拷贝算法 (copying) - 适用于存活对象较少的情况适合edon区  
   把内存区域分成了大小相等的两块区域，from和to区，每次只使用一个区，首先将一个区内的垃圾进行标记，再把存活的对象移动到另一个区，  
   再把第一个区的垃圾全部清除，完成后交换位置。  
   优点：没有碎片  
   缺点：浪费空间,只能用一半,移动复制对象,需要调整对象引用
   
### 3.3:分代垃圾回收

![binaryTree](tmp/jvm-image/jvm分代模型.png "binaryTree")

流程：  
新创建的对象都被放在了新生代的伊甸园中，  
伊甸园内存不足时，会触发Minor GC，将伊甸园中存活对象放到from区，并让寿命加1，伊甸园清空；  
伊甸园再次内存不足时，会触发Minor GC，伊甸园和幸存区FROM存活的对象先复制到 幸存区 TO中， 并让其寿命加1，再交换两个幸存区。  
......  
如果幸存区中的对象的寿命超过某个阈值（最大为15，4bit），就会被放入老年代中；from和to存不下了，也会放入老年代。  
如果新生代老年代中的内存都满了，就会先触发Minor GC，再触发Full GC，扫描新生代和老年代中所有不再使用的对象并回收  

注意：  
* 分代模型：除Epsilon ZGC Shenandoah之外的GC都是使用逻辑分代模型，ZGC之前所有的垃圾回收器都分代。  
  G1是逻辑分代，物理不分代；除此之外不仅逻辑分代，而且物理分代
* 新生代 = Eden + 2个suvivor区，总共一份,默认比例为8:1:1   拷贝算法  
* 老年代:总共二份,标记清除或者标记压缩算法  
* GC Tuning (Generation)
    1. 尽量减少FGC
    2. MinorGC = YGC:年轻代空间耗尽 -Xmn
    3. MajorGC = FGC:老年代无法继续分配内存空间,新生代老年代同时进行回收 -Xms-Xmx  ,full GC前先触发一次YGC
* 动态年龄：对象何时进入老年代:通过参数:--XX:MaxTenuingThreshold 指定次数(YGC)  
  -Parallel Scavenage  15
  -CMS 6
  -G1 15
* edon+s1>s2的百分之五十,或者edon+s2>s1的百分之五十 ,年龄大的部分直接放入老年区
* GC:会触发 stop the world，暂停其他用户线程，只让垃圾回收线程工作
* 线程内存溢出:某个子线程的内存溢出了而抛异常（out of memory error），不会让其他的线程结束运行  
  这是因为当一个线程抛出OOM异常后，它所占据的内存资源会全部被释放掉，从而不会影响其他线程的运行，进程依然正常
* 对象分配过程：（Thread-Local allocation buffer）  
  对象会尝试现在所在线程的线程栈上分配，分配不下会尝试使用TLAB，即每个线程在eden里自己的空间，默认1%,线程专属，访问不存在竞争，不需要加锁。  
  如果对象够大，直接进入老年代，最后通过FGC回收。  
  如果不是TLAB分配，那么多个线程共享一块Eden内存，这个时候当多个线程同时访问Eden区是，需要加锁同步，效率会降低。
  
### 3.4：垃圾回收器

#### 1：串行 
* 单线程
* 堆内存较小，适合个人电脑
* 安全点：让其他线程都在这个点停下来，以免垃圾回收时移动对象地址，使得其他线程找不到被移动的对象   
        因为是串行的，所以只有一个垃圾回收线程。且在该线程执行回收工作时，其他线程进入阻塞状态
  
-XX:+UseSerialGC = Serial(DefNew) + Serial Old(Tenured)   
-XX:+UseParNewGC = ParNew + SerialOld

Serial 收集器（复制）
Serial收集器是最基本的、发展历史最悠久的收集器
特点：单线程、简单高效（与其他收集器的单线程相比），采用复制算法。对于限定单个CPU的环境来说，Serial收集器由于没有线程交互的开销，
专心做垃圾收集自然可以获得最高的单线程手机效率。收集器进行垃圾回收时，必须暂停其他所有的工作线程，直到它结束（Stop The World）

ParNew 收集器（复制）
是Serial收集器的多线程版本
特点：多线程、ParNew收集器默认开启的收集线程数与CPU的数量相同，在CPU非常多的环境中，可以使用-XX:ParallelGCThreads参数来限制垃圾收集的线程数。和Serial收集器一样存在Stop The World问题

Serial Old 收集器（标记整理）
是Serial收集器的老年代版本
特点：同样是单线程收集器，采用标记-整理算法


#### 2：吞吐量优先
* 多线程
* 堆内存较大，多核CPU支持
* 目标：减少GC次数，让单次STW时间变长，少餐多食
* 安全点：多核cpu,跑着多个线程，当发生GC的时候，用户线程跑到一个安全点停下来，多个回收线程进行垃圾回收；回收完成之后，恢复其他的工作线程。 
         垃圾回收的时候CPU会很高。

-XX:+UseParallelOldGC = Parallel Scavenge(PSYoungGen) + Parallel Old(ParOldGen) (1.8 默认)
-XX:+UseParallelGC = Parallel Scavenge + Serial Old(PS MarkSweep)
注意：从JDK7u4开始，就对 “-XX:+UseParallelGC” 默认的老年代收集器进行了改进，改进使得HotSpot VM在选择使用   
“-XX:+UseParallelGC” 时，会默认开启"-XX:+UseParallelOldGC “，也就是说默认的老年代收集器是 Parallel Old。

Parallel Scavenge 收集器（复制）
与吞吐量关系密切，故也称为吞吐量优先收集器
特点：属于新生代收集器也是采用复制算法的收集器（用到了新生代的幸存区），又是并行的多线程收集器（与ParNew收集器类似）
该收集器的目标是达到一个可控制的吞吐量。还有一个值得关注的点是：GC自适应调节策略（与ParNew收集器最重要的一个区别）

GC自适应调节策略：Parallel Scavenge收集器可设置-XX:+UseAdptiveSizePolicy参数。当开关打开时不需要手动指定新生代的大小（-Xmn）、  
Eden与Survivor区的比例（-XX:SurvivorRation）、晋升老年代的对象年龄（-XX:PretenureSizeThreshold）等，  
虚拟机会根据系统的运行状况收集性能监控信息，动态设置这些参数以提供最优的停顿时间和最高的吞吐量，这种调节方式称为GC的自适应调节策略。

Parallel Scavenge收集器使用两个参数控制吞吐量（这两个目的是冲突的）：
* XX:MaxGCPauseMillis=ms（默认200ms） 控制最大的垃圾收集停顿时间，缩小的话一般是缩小堆空间
* XX:GCTimeRatio=ratio(默认99) 直接设置吞吐量的大小 1/1+ratio，gc时间占所有时间的比例，尽量小，jvm一般是调大堆空间来实现


Parallel Old 收集器（标记-整理）
是Parallel Scavenge收集器的老年代版本
特点：多线程，采用标记-整理算法


#### 3：响应时间优先
* 多线程
* 堆内存较大，多核CPU支持
* 目标：尽可能让单次STW时间最短，多次GC，多餐少食

-XX:+UseConcMarkSweepGC = ParNew + CMS + Serial Old
-XX:+UseG1GC = G1(garbage-first heap) (1.9默认)

CMS 收集器（标记-清除）
Concurrent Mark Sweep，一种以获取最短回收停顿时间为目标的老年代收集器
特点：基于标记-清除算法实现。并发收集、低停顿，但是会产生内存碎片
应用场景：适用于注重服务的响应速度，希望系统停顿时间最短，给用户带来更好的体验等场景下。如web程序、b/s服务

CMS收集器回收过程：
init mark初始标记:找到根对象,并标记,STW,时间很短,根可达算法
concurrent mark 并发标记:百分之八十GC的时间都浪费都这里,和应用程序一起运行,多数垃圾在此标记,在此过程,也有可能垃圾变成不是垃圾,也可以产生新的垃圾   
remark 重新标记:STW,将应用程序暂停,将新产生的垃圾进行标记(将修改的或者增加的进行重新标记)时间也很短
       并发标记算法：三色标记 + Incremental Update(增量更新)
concurrent sweep 并发清理:此过程也会产生垃圾,成为浮动垃圾，等待下一次CMS运行过程进行清理

CMS缺点：
1:CMS既然是MarkSweep，就一定会有碎片化的问题
 -XX:+UseCMSCompactAtFullCollection   开启内存整理
 -XX:CMSFullGCsBeforeCompaction 默认为0 指的是经过多少次FGC才进行压缩
2:产生浮动垃圾 Floating Garbage
   等待下一次CMS运行过程进行清理 
3：碎片到达一定程度，CMS的老年代分配对象分配不下的时候，使用SerialOld 进行老年代回收,单线程,Memory Fragmentation，GC时间增大 
解决方案：降低触发CMS的阈值,保持老年代有足够的空间
–XX:CMSInitiatingOccupancyFraction 92% 可以降低这个值，让CMS保持老年代足够的空间(cms GC时的老年代内存占比)


G1:Garbage First
JDK 9以后默认使用，而且替代了CMS 收集器，同时注重吞吐量和低延迟，默认的暂停目标是200ms.  
超大堆内存，会将堆划分为多个大小相等的Regin.整体上是标记+整理算法，两个区域之间是复制算法。  
2004年，论文发布；  
2006 jdk 6u14体验
2012 jdk 7u4官方支持
2017 jdk 9默认

G1的回收过程：

![binaryTree](tmp/jvm-image/G1回收阶段.png "binaryTree")

新生代伊甸园垃圾回收—–>内存不足，新生代回收+并发标记—–>回收新生代伊甸园、幸存区、老年代内存——>新生代伊甸园垃圾回收(重新开始)

Young Collection(分区，物理上没有分代，采用了分区) 
分代是按对象的生命周期划分，分区则是将堆空间划分连续几个不同小区间，每一个小区间独立回收，可以控制一次回收多少个小区间，方便控制 GC 产生的停顿时间
当新生代分区即将被占满，发生YGC，会STW，采用复制算法，将幸存者从Eden区复制到Survier区;
经过一段时间，eden区满了，会再次YGC，将Eden区和survier区的幸存者复制到Order区或者Servier区；

Young Collection + CM
进行YGC的时候并进行并发标记。在YGC的时候进行GC Root标记，当老年代占用空间比例达到阈值时，进行并发标记(不会STW),由
InitiatingHeapOccupancyPercent=precent 参数决定：启动G1并发标记时老年代占用堆空间的比例,默认45%。

Mixed Collection
会对E S O 进行全面的回收。复制算法。G1根据暂停时间,有选择的进行回收。 参数 -XX:MaxGCPauseMillis=time ，默认200ms
最终标记和拷贝存活两个过程都会STW

注意：CMS和G1的老年代回收不是Full GC，只有回收速度跟不上垃圾产生速度的时候，采用串行垃圾回收器回收的时候，才称之为Full GC (尽量避免)。


G1的跨代引用：

![binaryTree](tmp/jvm-image/G1跨代引用.png "binaryTree")

将老年代再次分区，分成一个个的card，每一个约是512K,如果老年代对象引用了新生代的对象，就把对象的card标记为脏卡。   
目的是为了在标记 Gc Root的时候，较少扫描范围。
Eden中有Remembered Set，用于保存新生代对象对应的脏卡。  
在引用变更时通过post-write barried（写屏障）去更新dirty card queue（脏卡），异步操作。  
会把更新的指令放到一个队列之中，由一个线程去读取执行指令，完成脏卡的更新。


重新标记（并发标记）过程：三色算法+增量更新（写屏障）
黑色：已经处理，强引用指向
灰色：等待处理
白色：垃圾，等待回收
如果一个对象被强引用指向，对象被标记成黑色;灰色的是等待处理的;白色的会被清除
如果有一个对象在并发过程中，对象引用发生了改变，对象会变成灰色，会被添加一个写屏障，并放在一个等待队列中，等待最终标记过程处理。


G1的一些优化：

jdk 8u20:字符串去重
-XX：UseStringDeduplication （默认开启）
优点：节省大量内存
缺点：略微多占用了cpu时间，新生代回收时间略微增加
```java
//底层是将字符串abc，放入到一个字符数组中char【】={‘a’,'b','c'} ,以前会有两个这样的字符数组
String s1=new String("abc");
String s2=new String("abc");
```
但是 jdk 8u20以后，当新生代回收时，G1并发检查是否有重复的字符串。如果字符串的值一样，就让他们引用同一个字符数组
注意：其与String.intern的区别
intern关注的是字符串对象;字符串去重关注的是char[]
在JVM内部，使用了不同的字符串标

jdk 8u40:并发标记类卸载
-XX：ClassUnloadingWithConcurrentMark 默认开启
在并发标记阶段结束以后，就能知道哪些类不再被使用。如果一个类加载器的所有类都不在使用，则卸载它所加载的所有类

jdk 8u60:回收巨型对象
一个对象可能占用G1的好几个region区，一个对象大于region的一半时，就称为巨型对象
G1不会对巨型对象进行拷贝
回收时被优先考虑
G1会跟踪老年代所有incoming引用，如果老年代incoming引用为0的巨型对象就可以在新生代垃圾回收时处理 （卡表引用）

jdk 9:并发标记起始时间的优化
并发标记必须在堆空间占满前完成，否则退化为Full GC 
jdk9之前通过InitiatingHeapOccupancyPercent=precent 参数设置并发标记的老年代阈值，一旦设置不能改变
jdk9之后，通过参数设置的只是初始值，G1进行数据采样并动态调整，总会添加一个安全的空挡空间。

jdk 9:更高效的回收
250+的增强，180+bug修复。


ZGC： 内存4T - 16T（JDK13）;  不分代   ,这个分块更加的人性化,内存块有大的有小的,不是同样大小的了
   ZGC (1ms) PK C++   ，ZGC现在只支持linux
   算法：ColoredPointers(颜色指针) + LoadBarrier

Shenandoah
   算法：ColoredPointers(颜色指针) + WriteBarrier


### 3.：垃圾回收调优
明白一点：调优跟应用、环境有关

查看虚拟机参数命令
```
//在控制台输入即可，可以根据条件查询
"jdk的bin目录路径\java" -XX:+PrintFlagsFinal -version | findstr "GC"
```

调优领域：
内存
锁竞争
CPU占用
IO
GC：可见，GC调优知识其中的一小块

确定GC调优目标：
低延迟/高吞吐量？ 选择合适的GC
CMS G1 ZGC  响应时间优先
ParallelGC  高吞吐量
Zing（非hosPot虚拟机）：号称零STW，管理超大内存

最快的GC是不发生GC:首先排除减少因为自身编写的代码而引发的内存问题
查看Full GC前后的内存占用，考虑以下几个问题
数据是不是太多？ 加载大量的sql数据，大表可以加limit
数据表示是否太臃肿
对象图
对象大小：减少对象本身的大小
是否存在内存泄漏: static Hashmap等，可以考虑一下第三方的缓存实现，或者使用软弱引用等。

新生代调优：
a:新生代的特点
所有的new操作分配内存都是非常廉价的：TLAB
死亡对象回收零代价
大部分对象用过即死（朝生夕死）
MInor GC 所用时间远小于Full GC

b:新生代内存越大越好么？
不是(25%-50%,oracle建议)
新生代内存太小：频繁触发Minor GC，会STW，会使得吞吐量下降
新生代内存太大：老年代内存占比有所降低，会更频繁地触发Full GC。而且触发Minor GC时，清理新生代所花费的时间会更长
新生代内存设置为能容纳[并发量*(请求-响应)]的数据为宜
幸存区需要能够保存【当前活跃对象+需要晋升的对象】，且晋升阈值要设置得当，让长时间存活对象尽快晋升。

老年代调优：
cms为例，将老年代内存调大，发生Full GC,将老年代预设变大1/4 -1/3
设置老年代垃圾回收的阈值，一般预留25%的空间

案例：
案例1：Full Gc 和 monitor GC 频繁
堆区空间太小，先调整新生代的大小

案例2：
请求高峰期发生Full GC，单次暂停时间特别长（CMS）
开启-XX:CMSScavengeBeforeRemork：在CMS重新标记之前先对新生代做一次GC，减轻新生代引用老年代的压力
STW时间从2s降低到300ms

案例3:老年代充裕的情况下，发生Full Gc（CMS,jdk7）
1.7采用了永久代，永久代空间不足也会导致Full GC 。增大了元空间的初始值和最大值


## 四：类加载与字节码技术












