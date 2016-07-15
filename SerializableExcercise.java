package main.java;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yuqia on 2016/7/13.
 */
class Student implements Serializable {
    private int sno;
    private String sname;

    public Student(int sno,String sname) {
        this.sno = sno;
        this.sname = sname;
    }
    public int getSno() {
        return sno;
    }
    public String getSname() {
        return sname;
    }
    public void setSno(int sno) {
        this.sno = sno;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    @Override
    public String  toString() {
        return "学号：" + sno + "姓名：" + sname;
    }
}
//(Object)反序列化过程
class MyClient extends Thread {
    public void run() {
        try {
            Socket s= new Socket("localhost",9999);
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Student stu = (Student) ois.readObject();
            System.out.println("客户端程序收到服务器程序传输过来的学生对象>>"+stu);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
//(object)的序列化过程
class MyServer extends Thread {
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9999);
            Socket s = ss.accept();
            ObjectOutputStream ops = new ObjectOutputStream(s.getOutputStream());
            Student stu = new Student(1,"Tom");
            ops.writeObject(stu);
            ops.close();
            s.close();
            ss.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class TestTransfer {
    public static void main(String[] args) {
        new MyServer().start();
        new MyClient().start();
    }

}