# 疯狂 Java 讲义笔记

## 1. Java 语言描述

所有程序部分必须放在类定义里；
如果某个类能被解释器直接解释执行，则这个类必须包含 main 方法

### 1.1 Java 源文件命名规则
- Java 源代码里定义了一个 public 类， 则该源文件名必须与该 public 类名相同
- 一个 Java 源文件最多只能定义一个 public 类名相同
- Java 中的关键字全部是小写
- 路径不要包含空格

## 2. 理解面向对象
面向对象的程序单位是类（面向过程的程序单位是函数）<br>
面向对象基本特征：封装、继承、多态<br>

### 2.1 类之间的三种基本关系
- 关联（包括聚合、组合）
- 泛化（与继承同一个概念）
- 依赖（一个类的改动会导致另一个类的改动）

## 3. 数据类型分类
- 基本类型
- 引用类型：包括类、接口和数组类型

> 空引用(null)只能被转换成引用类型，不能转换成基本类型，不要把一个 null 值赋给基本数据类型的变量

## 4. 流程控制
保留 if, for 等执行体的花括弧，增加可读性，减少犯错

多层循环中，break 语句不仅可以结束其所在的循环，还可以直接结束外层循环。<br>
此时要使用标签，Java 中的标签是一个紧跟着英文冒号(:)的标识符, 放在循环语句前有作用
<pre><code>
public static void main(String[] args) {
    //外层循环
    outer:
    for(int i = 0; i < 5; i++) {
        //内层循环
        for(int j = 0; j < 3; j++) {
            System.out.println("========");
            if(j == 1) {
                break outer;
            }
        }
    }
}
</code></pre>

continue 后也可以紧跟一个标签，用于直接跳过标签所标识循环的当次循环的剩下语句，重新开始下一次循环：
<pre><code>
public static void main(String[] args) {
    outer:
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 3; j++) {
            System.out.println("i:" + i + " j:" + j);
        }
    }
}
</code></pre>

return 的功能是结束一个方法。一旦在循环体内执行到 return 语句时，就会结束该方法
<pre><code>
public class ReturnTest {
    public static void main(String[] args) {
        for(int i = 0; i < 3; i++) {
            System.out.println("i: " + i);
            if(i == 1) {
                return ;
            }
            System.out.println("return 后的语句");
        }
    }
}
</code></pre>

## 5. 数组
Arrays 类处于 java.util 包下<br>
一个数组中只能存储一种数据类型的数据（可以是子类）<br>
数组也是一种数据类型，本身是一种引用类型<br>
<pre><code>
type[] arrayName;
type arrayName[];
</pre></code>
都可以，推荐第一种方式

实际的数组对象被存储在堆(heap)内存中；<br>
如果引用该数组对象的数组引用变量是一个局部变量，那么它被存储在栈(stack)内存中;<br>
从数组底层的运行机制上来看，没有多维数组

### 注意 length, length(), size()
- length属性是针对数组说的
- length()方法是针对字符串String说的
- size()方法是针对泛型集合说的,如果想看这个泛型有多少个元素,就调用此方法来查看

### 5.2 foreach 循环
<pre><code>
for(type variableName : array | collection) {
    // variableName 自动迭代每个元素
}
</code></pre>

## 6. 面向对象
类定义 包含三种成员：构造器、Field 和方法<br>

Java 类名：每个单词首字母大写，单词之间无分隔符<br>
Field 名：第一个单词字母小写，后面每个单词首字母大写<br>
方法名：建议以动词开头，同 Field 名<br>

static 修饰的成员不能访问没有 static 修饰的成员<br>
构造器用于构造类的实例，通过 new 关键字来调用构造器<br>
修饰方法的修饰符：abstract 和 final 最多只能二选一，可与 static 组合

static 修饰的成员表明它属于这个类本身，而不属于该类的单个实例

### 6.1 引用
类也是一种引用数据类型<br>
栈内存里的引用变量并未对真正存储对象的 Field 数据，对象的 Field 数据实际存放在堆内存里；<br>
而引用变量只是指向该堆内存里的对象;<br>
当一个对象被创建成功以后，这个对象将保存在堆内存中<br>
- Java 不允许直接访问堆内存中的对象，只能通过该对象的引用操作该对象。
- 堆内存里的对象可以有多个引用，即多个引用变量指向同一个对象

### 6.2 this 引用
this 作为对象的默认引用有2种情形：
- 构造器种引用该构造器正在初始化的对象
- 在方法种引用调用该方法的对象

this 关键字最大的作用：让类种的一个方法访问该类里的另一个方法或 Field<br>
Java 允许对象的一个成员直接调用另一个成员，可以省略 this 前缀<br>
static 修饰的方法不能使用 this 引用

- 不要使用对象去调用 static 修饰的 Field、方法，而是应该使用类去调用 static 修饰的 Field、方法
- 如果方法里有个局部变量和 Field 同名，但程序又需要在该方法里访问这个被覆盖的 Field，则必须使用 this 前缀 

### 6.3 方法
方法不能独立存在，方法必须属于类或对象<br>
方法被 static 修饰，则这个方法属于这个类，否则属于这个类的实例<br>
Java 里方法的参数传递方式只有一种：值传递（将实际参数值的副本传入方法内，参数本身不受影响）<br>
Java 允许定义形参个数可变的参数，在最后一个形参后增加"..."，表明该形参可接收多个参数值，被当成数组传入<br>
一个方法最多只能有一个长度可变的形参

<pre><code>
public static void test(int a, String... books) {
    for(String tmp : books) {
        System.out.println(tmp);
    }
}
</code></pre>

Java 里不能使用方法返回值类型作为区分方法重载的依据<br>
不推荐重载形参长度可变的方法

### 6.4 成员变量和局部变量
<table>
    <tr>
        <th rowspan = 6>所有变量</th>
        <th rowspan = 2>成员变量</th>
        <th>实例 Field（不以 static 修饰）</th>
    </tr>
    <tr>
        <th>类 Field(以 static 修饰)</th>
    </tr>
    <tr>
        <th rowspan = 4>局部变量</th>
    </tr>
    <tr>
        <th>形参（方法签名中定义的变量）</th>
    </tr>
    <tr>
        <th>方法局部变量（在方法内定义）</th>
    </tr>
    <tr>
        <th>代码块局部变量（在代码块内定义）<th>
    </tr>
</table>

类 Field 的作用域比 实例 Field 更大<br>
如果通过一个实例修改类 Field 的值，会导致该类的其他实例来访问这个类 Field 时也获得被修改的值<br>

局部变量分三种：
- 形参：在定义方法签名时定义的变量，作用域在整个方法有效
- 方法局部变量： 在方法体内定义的局部变量，从定义该变量的地方生效，方法结束时消失
- 代码块局部变量：在代码块中定义，从定义地方生效，代码块结束失效

尽量避免局部变量和成员变量同名<br>
局部变量不属于任何类或实例，它总保存在所在方法的栈内存中
> 如果局部变量是基本类型，则直接把变量值保存在对应内存中；<br>
> 如果是引用类型，则变量里存放的是地址

变量的使用规则：
- 用于描述类或对象的固有信息，使用成员变量
- 某个类中需要以一个变量来保存该类或实例运行时的状态信息，使用成员变量
- 某个信息需要在某个类的多个方法之间共享，使用成员变量

### 6.5 隐藏和封装
控制级别由小到大：
<pre>privte -> default -> protected -> public</pre>


|-|private|default|protected|public|
|-|-|-|-|-|
|同一个类中|√|√|√|√|
|同一个包中||√|√|√|
|子类中|||√|√|
|全局范围内||||√|



控制符基本原则：
- 绝大部分 Field 都应该用 private 修饰，只有一些 static 修饰的、类似全局变量的 Field 才考虑 public。除此之外，工具方法（只用于辅助实现该类的其他方法）也应该使用 private 修饰
- 某个类用做其他类的父类，包含大部分方法仅希望被子类重写，应使用 protected 修饰
- 希望暴露给其他类自由调用的方法使用 public 修饰

### 6.6 package, import 和 import static
Java 允许将一组功能相关的类放在同一 package 下，组成逻辑上的类库单元<br>
一旦使用了 package 语句，意味着该文件定义的所有类都属于这个包<br>
同一个包中的类不必位于相同目录下<br>
建议把 Java 源文件放在与包名一致的目录下<br>
package 语句必须作为源文件的第一条非注释语句，一个源文件只能指定一个包<br>

import 可以向某个 Java 文件中导入指定包层次下某个类或全部类<br>
import 语句中的 "*" 只能代表类不能代表包

### 6.7 Java 常用包
- java.lang: 核心类，无需使用 import, 自动导入
- java.util: 大量工具和集合框架
- java.net: 网络编程
- java.collection: Java 输入/输出
- java.text: Java 格式化 
- java.sql: JDBC 数据库编程


### 6.8 构造器
使用 this 调用另一个重载的构造器只能在构造器中使用，而且必须作为构造器执行体第一行语句

### 6.9 继承
Java 的子类不能获得父类的构造器<br>
java.lang.Object 类是所有类的父类<br>
方法重写重写遵循"两同两小一大"：<br>
- 方法名、形参列表相同
- 子类方法返回值类型比父类更小或相等，子类方法声明抛出异常类应比父类更小或相等
- 子类方法返回权限应比父类更大或相等

可用 super 调用被覆盖的父类实例方法和 Field<br>
super 也不能出现在 static 修饰的方法中 <br>
在子类构造器中调用父类构造器使用 super 调用来完成(而同一个类中用 this 调用重载的构造器)<br>

overload 重载：同一类的多个同名方法之间<br>
override 重写：子类父类同名方法之间

### 6.10 多态
Java 引用变量有2个类型：编译时类型和运行时类型，如果他们不一致，就可能出现多态<br>
<pre><code>
    BasicClass x = new SubClass();
</code></pre>
Java 允许把一个子类对象直接赋值给一个父类引用变量，成为向上转型<br>
上例中，编译类型是 BaseClass，运行时类型是 SubClass<br>
而 Field 不具备多态性，调用时输出的是 BaseClass 中的 Field

引用变量强制类型转换：
- 基本类型间的转换只能在数值类型之间，数值和布尔型之间不能转换
- 引用类型之间的转换只能在具有继承关系的两个类型之间。如果把一个父类实例转换成子类类型，则这个对象必须实际上时子类实例

instanceof 作用：在进行强制类型转换之前，先判断前一个对象是否是后一个类的实例，是否成功转换

### 6.11 继承与组合
设计父类通常遵循：
- 尽量隐藏父类的内部数据
- 不要让子类随意访问、修改父类的方法
- 尽量不要在父类构造器中调用将被子类重写的方法

继承&组合：
- 继承：将一个较为抽象的类改造成嫩适用于某些特定需求的类 （is-a）
- 组合：如果两个类之间有明确的整体、部分关系(has-a)

### 初始化块
初始化块只在创建 Java 对象时隐式执行，而且在执行构造器之前执行<br>
初始化块是构造器的补充<br>
使用 static 修饰的初始化块是类初始化块

### 6.12 包装类
基本数据类型 -> 包装类<br>

![](pic6_1.png)
8个包装类除了 Character 外，可通过传入一个字符串参数来构建包装类对象
<pre><code>
Float fl = new Float("4.56");
</code></pre>

如果希望获得包装类对象中包装的基本类型变量，则可以使用包装类提供的 xxxValue()实例方法<br>
![](pic6_2.png)

### 6.13 处理对象

toString() 方法是 Object 类里的一个实例方法，所有 Java 对象都可以和字符串进行连接计算<br>
toString() 方法返回该对象实现类的 "类名 + @ + hashCode"<br>

- == 如果两个变量是基本类型，只要值相等，就返回 true
- == 如果两个引用类型变量，它们必须指向同一对象，才返回 true
- equals 是 Object 类提供的实例方法，与 == 没有区别
- String 重写了 equals() 方法：只要两个字符串包含字符序列相同，通过 equals() 比较将返回 true

正确重写 equals 方法应满足：
- 自反性：对任意 x, x.equals(x)一定返回 true
- 对称性：对任意 x 和 y, 如果 y.equals(x) 返回 true, 则 x.equals(y) 也返回 true
- 传递性：对任意x,y,z, 如果 x.equals(y) 返回ture, y.equals(z) 返回 true, 则 x.equals(z) 一定返回 true
- 一致性：对任意 x 和 y, 如果对象中用于等价比较的信息没有改变，那么返回结果保持一致

### 6.14 类成员
单例类： 一个类时钟只能创建一个实例<br>
- 把该类构造器使用 private 修饰<br>
- 一旦隐藏构造器，就需要提供一个 public 方法作为该类的访问点，用于创建类的对象，且该方法必须是 static 修饰<br>
- 该类还必须缓存已创建的对象

<pre><code>
class Singleton {
    private static Singleton instance;
    private Singleton() {

    }
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
</code></pre>

### 6.15 final 修饰符

final 关键字修饰的类、方法和变量不可改变<br>
final Field
- 类 Field：必须在镜头初始化过程中或声明该 Field 时指定初始值
- 实例 Field：必须在非静态初始化块、声明该 Field 或构造器中指定初始值
- final 成员变量必须显式初始化
- final 修饰局部变量可在定义时不指定默认值
- 使用 final 修饰引用类型变量不能被重新赋值，但可以改变引用类型变量所引用对象的内容

final 方法：
- 不可被重写，如果不希望子类重写父类方法，则可用 final
- Object 类中的 getClass() 方法就是 final 方法

final 类：
- 不能有子类

不可变类：创建实例后，实例的 Field 关键字修饰的类、方法和变量不可改变<br>
<pre><code>
Double d = new Double(6.5);
</code></pre>

创建不可变类的规则：
- 使用 private 和 final 修饰符来修饰该类的 Field
- 提供带参数构造器，用于根据传入参数来初始化类里的 Field
- 仅为该类的 Field 提供 getter 方法，不提供 setter方法
- 若有必要，重写 Object 类的 hashCode 和 equals 方法


### 6.16 抽象类
抽象方法 & 抽象类 规则：
- 必须使用 abstract 修饰，抽象方法不能有方法体
- 抽象类不能被实例化
- 抽象类可包含 Field, 方法（普通和抽象均可），构造器，初始化块，内部类，枚举类
- 含抽象方法的类只能被定义为抽象类

<pre><code>
public abstract void test(); //抽象方法(记得带分号)
public void test() {} //是普通方法
</code></pre>

- abstract 类只能被集成
- abstract 方法只能被重写
- final 和 abstract 永远不能同时使用
- static 和 abstract 不能同时修饰某个方法
- private 和 abstract 不能同时修饰某个方法

使用模板模式的简单规则：
- 抽象父类可只定义需要使用的某些方法，把不能实现的部分抽象成抽象方法，留给子类去实现
- 父类可能包含需要调用的其他系列方法的方法，这些方法既可以父类实现，也可以子类实现

### 6.17 接口
接口里通常定义一组公用方法<br>
interface 语法：
- 修饰符可 public 可省略，省略后只有相同包下才可访问
- 接口名应与类名采用相同的命名规则，每个单词首字母大写
- 一个接口可以有多个直接父接口，但接口只能继承接口，不能继承类
- interface 里不能包含构造器和初始化块
- interface 内的 Field 只能是常量，方法只能是抽象方法
- interface 内的所有成员均是 public 权限
- interface 内没有静态方法
- 一个 Java 文件内最多只能有一个 public 接口

接口的实现：
- 一个类实现了接口后，必须完全实现这个接口里定义的全部抽象方法
- 实现接口方法时，必须使用 public 修饰符

接口和抽象类：
- 都不能被实例化
- 都可以包含抽象方法，实现接口或继承抽象类都必须实现这些抽象方法
- 接口里只能包含抽象方法，抽象类可包含普通方法
- 接口里不能自定义静态方法，抽象类可定义静态方法
- 接口只能定义静态 Field， 抽象类可定义普通 Field 和 静态 Field
- 接口不包含构造器；抽象类可包含构造器
- 接口不包含初始化块，抽象类可以
- 一个类最多一个直接父类，但可直接实现多个接口

### 6.18 内部类
内部类作用：
- 更好的封装，不允许同一个包中其他类访问
- 内部成员可直接访问外部类私有数据
- 匿名内部类适合创建仅需要使用一次的类

内部类可用 private 修饰符来修饰该类<br>
成员内部类编译后的 class 文件形式：OuterClass$InnerClass.class<br>
如果外部类成员变量、内部类成员变量与内部类方法局部变量同名，可通过 this、 OuterLass.this 作为区分<br>
外部类的静态方法、静态代码块不能访问非静态内部类<br>
Java 不允许在非静态内部类里定义静态成员<br>
静态内部类是外部类的一个静态成员，因此外部类的静态方法、静态初始化块中可使用静态内部类来定义变量、创建对象<br>
外部类不能直接访问静态内部类的成员，但可使用静态内部类类名作为调用者来访问静态内部类成员<br>
 > 接口内部类只能是静态内部类（但意义不大），默认使用 public static 修饰

#### 内部类的用法
1. 在外部类中使用内部类：和使用普通类没有太大区别
2. 在外部类以外使用非晶态内部类：内部类不能用 private 修饰；创建内部类对象前必须先创建外部类对象

#### 匿名内部类
适合创建只需要使用一次的类<br>
定义：
<pre><code>
new 父类构造器(实参列表)|实现接口() {
    //类体部分
}
</code></pre>

匿名内部类规则：
- 不能是抽象类
- 不能定义构造器，因为没有类名

#### 闭包和回调
闭包：一种被调用的对象，保存了创建它的作用域信息<br>
把非静态内部类当成面向对象领域的闭包<br>

回调：某个方法一旦获得了内部类对象的引用后，就可以在合适的时候反过来去调用外部类实例的方法

例：
<pre><code>
interface Teachable {
    void work();
}
public class Programmer {
    private String name;
    public Programmer() {

    }
    public Programmer(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println(name + "coding...");
    }
}

public class TeachableProgrammer extends Programmer {
    public TeachableProgrammer() {

    }
    public TeachableProgrammer(String name) {
        super(name);
    }
    private void teach() {
        System.out.println("Teacher's teaching on the stage..");
    }
    private class Closure implements Teachable {
        public void work() {
            teach();
        }
    }
    //返回一个非静态内部类引用，允许外部类通过该非静态内部类引用来回调外部类的方法
    public Teachable getCallbackReference() {
        return new Closure();
    }

}
</code></pre>

### 枚举类
枚举类： 实例有限而且固定的类<br>
enum 关键字定义枚举类

- 一个 Java 源文件最多只能定义一个 public 的枚举类
- enum 定义的枚举类继承 java.lang.Enum，而非 Object
- 枚举类构造器只能使用 private 修饰符
- 枚举类所有实例必须在枚举类第一行显示列出
- 枚举类也可以定义 Field、方法
- 一旦枚举类显示定义了构造器，列出枚举值就必须传入参数
- 枚举类实现接口，可以让每个枚举类分别来实现方法，提供不同的实现方式
- 当创建枚举值时，实际上创建了枚举类的匿名子类实例

<pre><code>
public enum SeasonEnum {
    SPRING,SUMMER,FALL,WINTER;
}
</code></pre>

java.lang.Enum 包含的方法
- int compareTo(E o): 指定枚举对象比较顺序，若该枚举对象位于指定枚举对象之后，返回正整数；在之前返回负整数；否则返回零
- int ordinal(): 返回枚举值在枚举类中的索引值（在枚举声明中的位置）
- String toString(): 返回枚举常量名称

### 对象与垃圾回收
- 只负责回收堆内存中的对象，不会回收任何物理资源
- 程序无法精确控制垃圾回收运行

#### 对象在内存中的状态
- 可达状态：对象被创建后，有一个以上引用
- 可恢复状态：某个对象不再有任何引用变量引用它
- 不可达状态：对象与所有引用变量的关联被切断，且系统调用finalize()方法后依然没有使该对象变成可达状态

#### 强制垃圾回收
- System.gc()
- Runtime.getRuntime().gc()
只是通知系统进行垃圾回收，但系统是否进行垃圾回收依然不确定

#### finalize()方法4个特点：
- 永远不要主动调用
- 该方法何时被调用，是否被调用具有不确定性
- JVM 执行客恢复对象的finalize()方法时，可能使其重变成可达
- 不会报告异常

#### 对象的强、软、弱、虚引用
1. 强引用：最常见，通过引用变量来操作实际对象，处于可达时不被回收
2. 软引用：通过SoftReference实现，当对象只有软引用时有可能被强制回收；当内存空间足够，不会被回收，空间不足时系统可能回收它
3. 弱引用：和软引用很像，但垃圾回收机制运行时，总会被回收
4. 虚引用：完全类似于没有引用，用于跟踪对象被垃圾回收的状态，必须和引用队列(ReferenceQueue)联合使用

### JAR 包
全称 Java Archive File Java 档案文件


## 7. 与运行环境交互
### 7.1 与用户互动
main 方法由 JVM 调用，args形参由 JVM 赋值<br>

Scanner 类是一个基于正则表达式的文本扫描器，可以从文件、输入流、字符串中解析出基于基本类型值的字符串值
- hasNextXxx():是否还有下一个输入项，其中Xxx 可以是Int\Long等基本数据类型
- nxtXxx():获取下一个输入项
- boolean hasNextLine():返回输入源中是否还有下一行
- String nextLine():返回输入源中下一行的字符串
- 创建Scanner 对象时传入一个File对象作为参数，就可以让Scanner读取该文件内容
<pre><code>
Scanner sc = new Scanner(new File("ScannerFileTest.java"));
while(sc.hasNextLine()) {
    System.out.println(sc.nextLine());
}
</code></pre>

### 7.2 系统相关
#### System 类
- 程序不能创建System 类对象
- 提供标准输入输出和错误输出的类 Field, 并提供静态方法访问环境变量、系统属性
- 提供 identityHashCode(Object x) 方法，返回指定对象的精确 hashCode 值（根据对象的地址计算得到）

#### Runtime 类
- 代表Java 程序的运行时环境，程序不能自己创建 Runtime 实例
- 可访问 JVM 相关信息

### 7.3 常用类
#### Object 类
- 是所有类的父类，所有类默认继承 Object
- boolean equals(Obkect obj) 和"=="基本没有区别，存在的意义在于被重写
- protected void finalize() 垃圾回收调用
- Class<?> getClass() 返回运行时类
- int hashCode() 返回对象hashCode值
- String toString() 返回 “运行时类名@十六进制hashCode值”
- protected clone()方法,帮助其他对象实现自我克隆，即获得当前对象副本，且二者完全隔离；是一种浅克隆，只克隆该对象的所有Field值，不会对引用类型的Field值所引用对象进行克隆

#### String、StringBuffer
- String 一旦一个 String 对象被创建以后字符序列不可变
- StringBuffer 对象代表一个字符序列可变的字符串，通过该类提供的append(),insert(),reverse(),setCharAt(),setLength()等方法可改变这个字符串
- StringBuilder基本类似于StringBuffer，但 StringBuffer 是线程安全的
- StringBuffer 和 StringBuilder 有两个属性：length 和 capacity，与 String 对象不同的是，length 可变，capacity 通常比 length 大，且无需关心

因为 String 是不可变的，在执行连接字符串操作时，会额外产生很多临时变量，使用 StringBuffer 或 StringBuilder 就可以避免

#### Math 类
- 两个静态 Field:PI, E
- 工具类，无法创建 Math 对象

#### ThreadLocalRandom 与 Random
- Random 类生成伪随机数，如果这个类的两个实例用同一个种子创建，对它们同样顺序调用，会产生相同数字序列
- ThreadLocalRandom 类可保证系统更好的线程安全性，提供静态 current() 方法获取 ThreadLocalRandom 对象，之后可调用各种 nextXxx() 方法
- 为避免产生相同数字序列，通常已当前时间作为 Random 对象的种子
<pre><code>
Random rand = new Random(System.currentTimeMills());
</code></pre>

#### BigDecimal 类
为了精确表示、计算浮点数<br>
通常建议有限使用基于 String 的构造器<br>
若必须使用 double 作为 BigDecimal 构造器参数，应通过 BigDecimal.valueOf(double value) 静态方法来创建 BigDecimal 对象

### 7.4 处理日期的类
#### Date 类
不推荐使用

#### Calendar 类
抽象类，用于表示日历<br>
GregorianCalendar：公历，Calendar 类的子类

### 7.5 正则表达式
正则表达式是一个用于匹配字符串的模板<br>
通配符是可以匹配多个字符的特殊字符<br>
Pattern 对象是正则表达式编译后在内存中的表示形式，然后利用该 Pattern 对象创建对应的 Matcher对象<br>
Matcher 类的 find() 和 group()方法可从目标字符串中依次取出特点子串<br>

> String 类的 equals(), startsWith()都与字符串比较，而 Matcher 的 matches() 和 lookingAt() 是与正则表达式匹配

## 8. Java 集合
### 8.1 集合概述
所有集合类都位于 java.util 包下<br>
集合李只能保存对象（引用）而不能保存基本数据类型<br>
主要由两个接口派生出来：Collection 和 Map<br>
![](pic8_1.png)

- Set 和 List 接口分别代表了无序集合和有序集合
- Queue 是 Java 提供的队列实现，有点类似 List
- 所有的 Map 实现类用于保存具有映射关系的数据
- Set 里的元素不能重复
- List可以记住每次添加元素的顺序，长度可变
- Map 的每项数据都由两个值组成

### 8.2 Collection 和 Iterator 接口
Collection 接口：List, Set 和 Queue 的父接口<br>
所有的 Collection 都重写了 toString()方法，可以一次性输出集合中的所有元素<br>
Iterator 用于遍历 Collection 集合中的元素<br>
Iterator 进行迭代时，是把集合元素的值传给了迭代变量，修改迭代变量的值对集合元素本身无影响<br>
foreach 循环迭代集合元素时，该集合也不能被改变

### 8.3 Set 集合
Set 集合不允许包含相同的元素<br>
判断两个元素相同是根据 equals 方法<br>
#### HashSet
HashSet 按 Hash 算法来存储集合中的元素
- 不能保证元素排列顺序
- 不同步，多线程同时修改HashSet时，必须通过代码来保证同步
- 元素值可以是 null
- 把一个对象放入 HashSet 中，如需重写 equals() 方法，也要重写 hashCode()方法，其规则是：如果两个对象通过 equals() 方法返回 true，这两个对象的 hashCode 值也应相同

> Hash 算法的功能：保证通过一个对象快速查找到另一个对象，价值在于速度<br>
> hash 算法可以直接根据元素值计算出该元素的存储位置<br>
> 与数组不同的是，数组定长且索引连续；hash根据元素 hashcode 值来计算索引

重写 hashCode() 的基本规则：
- 同一对象多次调用 hashCode()应返回相同值
- 两对象通过 equals()返回true，则 hashCode()返回相同
- 对象中用作 equals() 比较标准的 Field，都应计算 hashCode()值
一般规则
1. 把对象内每个有意义的 Field计算出一个 int 类型的 hashCode值
2. 第1步多个 hashCode值组合计算出一个 hashCode 值返回

#### LinkedHashSet 类
使用链表维护元素的次序，遍历时按元素插入顺序访问<br>
性能略低于 HashSet<br>
依然不允许集合元素重复

#### TreeSet 类
- 是 SortedSet 接口实现类，可确保集合元素处于排序状态
- 所以增加了访问第一个、前一个、后一个、最后一个元素的方法
- 根据元素实际值的大小来排序
- 采用红黑树数据结构来存储集合元素
- 支持自然排序（默认）和定制排序

1. 自然排序<br>
TreeSet 调用集合元素的 compareTo(Object obj) 方法比较两元素大小，将集合元素按升序排列<br>
Java 中的 Comparable 接口提供了一个 compareTo(Object obj)方法，实现该接口的类必须实现此方法<br>
向 TreeSet 中添加的应该是同一个类的对象，否则引发异常<br>
TreeSet 判断两元素相等的唯一标准：compareTo方法返回值是否是0<br>
> 当可变对象的Field被修改时，TreeSet 处理这些对象将非常复杂，且容易出错,推荐 HashSet 和 TreeSet 只放入不可变对象

2. 定制排序<br>
如需实现定制排序，需要在创建 TreeSet 集合时通过 Comparable 接口帮助

#### EnumSet 类
- 专为枚举类设计的集合类
- 所有元素都必须是指定枚举类型的枚举值
- 元素有序，以枚举值排序
- 以向量形式存储，内存占用小，运行效率高
- 不允许加入 null 元素
- 无可调用的构造器，需通过 static 方法创建 EnumSet 对象

#### 各 Set 性能分析
- HashSet 性能总比 TreeSet 好
- 只有当需要保持排序的 Set 时，才使用 TreeSet
- HashSet 的子类 LinkedHashSet 比 HashSet 略慢，但遍历更快
- EnumSet 性能最好，但只能保存同一个枚举类的枚举值作为集合元素
- 线程都是不安全的

### 8.4 List 集合
List 代表一个元素有序、可重复的集合

#### List 接口 和 ListIterator 接口
相比于 Set， List 增加了根据索引来插入、替换和删除集合元素的方法<br>
List 额外提供了一个 liseIterator()方法，返回 ListIterator 对象，提供前向迭代，还可通过add方法向List集合中添加元素<br>

#### ArrayList 和 Vector
ArrayList 和 Vector 都基于数组实现 List 类，所以 ArrayList 和 Vector 封装了一个动态的、允许再分配的 Object[] 数组<br>
ArrayList 或 Vector 对象使用 initialCapacity 设置数组长度，当元素超出长度时，initialCapacity 自动增加
- ArrayList 和 Vector 显著区别： ArrayList 线程不安全，Vector 是线程安全的，Vector性能差
- Vetor 提供了一个 Stack 子类， 用于模拟“栈”这种数据结构

### 8.5 Queue 集合
Queue 用于模拟队列，通常队列不允许随机访问<br>

#### PriorityQueue 实现类
PriorityQueue 是一个比较标准的队列实现类，但是按队列元素大小进行重排，而非按加入顺序<br>
取出队列元素时先取出最小的元素<br>
不允许插入 null 元素<br>
采用自然排序和定制排序<br>

#### Deque 接口与 ArrayDeque 实现类
Deque 是 Queue 的子接口，代表一个双端队列，允许从两端操作队列元素<br>
Deque 提供了一个经典实现类： ArrayDeque，基于数组实现，创建对象时可指定一 numElements 参数，用于指定Object[] 数组长度<br>

#### LinkedList 实现类
LinkedList 可以根据索引来随机访问集合中的元素<br>
LinkedList 可作为双端队列、栈使用

#### 各种线性表的性能分析

|  | 实现机制 | 随机访问排名 | 迭代操作排名 | 插入操作排名 | 删除操作排名 |
| ------------- |:-------------:| :-----:| :-----:| :-----:| :-----:|
| 数组 | 连续内存区保存元素 | 1 | 不支持 | 不支持 | 不支持 |
| ArrayList/ArrayDeque | 以数组保存元素 | 2 | 2 | 2 | 2 |
| Vector | 以数组保存元素 | 3 | 3 | 3 | 3 |
| LinkedList | 以链表保存元素 | 4 | 1 | 1 | 1 |

### 8.6 Map
- Map 用于保存具有映射关系的数据
- 一组值保存key，另一组保存 value，都可以是引用变量
- key 不允许重复
- key 和 value 间存在单向一对一关系
- Map 里的所有 key 构成 set 集合

> map 与 set 关系密切，Map 提供一个 Entry 内部类来封装 key-value 对，计算 Entry 存储时只考虑 Entry 封装的 key

#### HashMap 和 Hasgtable 实现类
- 都是 map 接口的经典实现，关系类比 ArrayList 与 Vector
- 两点区别：Hashtable 是线程安全的，HashMap 性能高；Hashtable 不允许使用 null 作为 key 和 value，HashMap 可以
- HashMap 和 Hashtable 均不保证元素顺序
- 尽量不要在程序中修改 key，或不要使用可变对象作为 key




