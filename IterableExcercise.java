/**
 * Created by yuqia on 2016/7/14.
 */
import java.util.Iterator;

public class ForEachAPIDemo {
    public static void main(String[] args) throws Exception {
        Students students = new Students(10);
        for (Student student : students) {
            System.out.println(student.getSid() + ":" + student.getName());
        }
    }
}

// 支持for each迭代循环的学生集合类
class Students implements Iterable<Student> {
    // 存储所有学生类的数组
    private Student[] students;

    // 该构造函数可以生成指定大小的学生类变量数组，并初始化该学生类变量数组
    public Students(int size) {
        students = new Student[size];
        for (int i = 0; i < size; i++) {
            students[i] = new Student(String.valueOf(i), "学生" + String.valueOf(i));
        }
    }

    @Override
    public Iterator<Student> iterator() {
        return new StudentIterator();
    }

    // 实现Iterator接口的私有内部类，外界无法直接访问
    private class StudentIterator implements Iterator<Student> {
        // 当前迭代元素的下标
        private int index = 0;

        // 判断是否还有下一个元素，如果迭代到最后一个元素就返回false
        public boolean hasNext() {
            return index != students.length;
        }

        @Override
        public Student next() {
            return students[index++];
        }

        // 这里不支持，抛出不支持操作异常
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

class Student {
    private String sid;
    private String name;

    public Student(String sid, String name) {
        setSid(sid);
        setName(name);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
