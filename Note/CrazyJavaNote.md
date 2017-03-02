# 疯狂 Java 讲义笔记

## Java 语言描述

所有程序部分必须放在类定义里；
如果某个类能被解释器直接解释执行，则这个类必须包含 main 方法

### Java 源文件命名规则
- Java 源代码里定义了一个 public 类， 则该源文件名必须与该 public 类名相同
- 一个 Java 源文件最多只能定义一个 public 类名相同
- Java 中的关键字全部是小写
- 路径不要包含空格

## 理解面向对象
面向对象的程序单位是类（面向过程的程序单位是函数）<br>
面向对象基本特征：封装、继承、多态<br>

###类之间的三种基本关系
- 关联（包括聚合、组合）
- 泛化（与继承同一个概念）
- 依赖（一个类的改动会导致另一个类的改动）

## 数据类型分类
- 基本类型
- 引用类型：包括类、接口和数组类型

> 空引用(null)只能被转换成引用类型，不能转换成基本类型，不要把一个 null 值赋给基本数据类型的变量

## 流程控制
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

## 数组
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

### foreach 循环
<pre><code>
for(type variableName : array | collection) {
    // variableName 自动迭代每个元素
}
</code></pre>

## 面向对象
类定义 包含三种成员：构造器、Field 和方法<br>

Java 类名：每个单词首字母大写，单词之间无分隔符<br>
Field 名：第一个单词字母小写，后面每个单词首字母大写<br>
方法名：建议以动词开头，同 Field 名<br>

static 修饰的成员不能访问没有 static 修饰的成员<br>
构造器用于构造类的实例，通过 new 关键字来调用构造器<br>
修饰方法的修饰符：abstract 和 final 最多只能二选一，可与 static 组合

static 修饰的成员表明它属于这个类本身，而不属于该类的单个实例

### 引用
类也是一种引用数据类型<br>
栈内存里的引用变量并未对真正存储对象的 Field 数据，对象的 Field 数据实际存放在堆内存里；<br>
而引用变量只是指向该堆内存里的对象;<br>
当一个对象被创建成功以后，这个对象将保存在堆内存中<br>
- Java 不允许直接访问堆内存中的对象，只能通过该对象的引用操作该对象。
- 堆内存里的对象可以有多个引用，即多个引用变量指向同一个对象

### this 引用
this 作为对象的默认引用有2种情形：
- 构造器种引用该构造器正在初始化的对象
- 在方法种引用调用该方法的对象

this 关键字最大的作用：让类种的一个方法访问该类里的另一个方法或 Field<br>
Java 允许对象的一个成员直接调用另一个成员，可以省略 this 前缀<br>
static 修饰的方法不能使用 this 引用

- 不要使用对象去调用 static 修饰的 Field、方法，而是应该使用类去调用 static 修饰的 Field、方法
- 如果方法里有个局部变量和 Field 同名，但程序又需要在该方法里访问这个被覆盖的 Field，则必须使用 this 前缀 

### 方法
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

### 成员变量和局部变量
<table>
    <tr>
        <th rowspan = 7>所有变量</th>
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

### 隐藏和封装
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

### package, import 和 import static
Java 允许将一组功能相关的类放在同一 package 下，组成逻辑上的类库单元<br>
一旦使用了 package 语句，意味着该文件定义的所有类都属于这个包<br>
同一个包中的类不必位于相同目录下<br>
建议把 Java 源文件放在与包名一致的目录下<br>
package 语句必须作为源文件的第一条非注释语句，一个源文件只能指定一个包<br>

import 可以向某个 Java 文件中导入指定包层次下某个类或全部类<br>
import 语句中的 "*" 只能代表类不能代表包

### Java 常用包
- java.lang: 核心类，无需使用 import, 自动导入
- java.util: 大量工具和集合框架
- java.net: 网络编程
- java.collection: Java 输入/输出
- java.text: Java 格式化 
- java.sql: JDBC 数据库编程

