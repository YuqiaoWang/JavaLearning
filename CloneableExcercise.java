package main.java;

import java.io.ObjectOutputStream;

/**
 * Created by yuqia on 2016/7/13.
 */
class Computer implements Cloneable{
    public String info="";
    public Object clone(){
        Computer myComputer = null;
        try {
            myComputer = (Computer) super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Clone Abort_ :"+e);
        }
        return myComputer;
    }
}

class CloneComputer {
    public static void main(String[] args) {
        Computer myComputer = new Computer();
        myComputer.info = "Clone succeed...";
        Computer anotherComputer = (Computer) myComputer.clone();
        System.out.println(anotherComputer.info);
        //如果打印出“Clone succedd..”证明克隆成功
    }
}
