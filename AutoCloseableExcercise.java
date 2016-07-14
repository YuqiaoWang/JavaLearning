package main.java;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yuqia on 2016/7/12.
 */
public class Main {
    public static void main(String[] args) {
        try (Resource res = new Resource();
        ResourceOther resOther = new ResourceOther();) {
            res.doSome();
            resOther.doSome();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//JDK 1.7 后的写法，利用 AutoCloseable 接口
    static String readFistFromFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
}

class Resource implements AutoCloseable {
    void doSome() {
        System.out.println("do something");
    }
    @Override
    public void close() throws Exception {
        System.out.println("resource closed");
    }
}

class ResourceOther implements AutoCloseable {
    void doSome() {
        System.out.println("do somthing other");
    }
    @Override
    public void close() throws Exception{
        System.out.println("other resource closed");
    }

}