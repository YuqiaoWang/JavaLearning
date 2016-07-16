/**
 * Created by yuqia on 2016/7/16.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class AutoCloseableClass {
    public static void main(String[] args) {
        Resource res = new Resource();
        ResourceOther resOther = new ResourceOther();
        try {
            res.dosome();
            resOther.dosome();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

class Resource implements AutoCloseable {
    void dosome() {
        System.out.println("do something");
    }
    public void close() throws Exception {
        System.out.println("resource closed");
    }
}

class ResourceOther implements AutoCloseable {
    void dosome() {
        System.out.println("do something other");
    }
    public void close() throws Exception {
        System.out.println("other resource closed");
    }
}
