package main.java;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yuqia on 2016/7/11.
 */
public class EmployeeSortTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Harry Hacker",35000);
        staff[1] = new Employee("Carl Cracke",75000);
        staff[2] = new Employee("Tony Tester",38000);
        Arrays.sort(staff);
        //sort方法可以实现对对象数组排序，但是必须实现 Comparable 接口
        for(Employee e:staff)
            System.out.println("id="+e.getId()+" name："+e.getName()+
                    " salary="+e.getSalary());
    }

}

/*
* 因为要实现对Employee对象的排序，所以在Employee类中要实现Comparable接口，
* 也就是要实现comepareTo()方法
*/


class Employee implements Comparable<Employee>{
    private int id;
    private String name;
    private double salary;
    public Employee(String n,double s) {
        name = n;
        salary = s;
        Random ID = new Random();
        id = ID.nextInt(10000000);
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public void raiseSalary(double byPercent) {
        double raise = salary*byPercent/100;
        salary+=raise;
    }
    public int compareTo(Employee other) {
        //按id排序
        /*if(id<other.id)
            return -1;
        if(id>other.id)
            return 1;
        else
            return 0;*/
        //按salary排序
        if(salary<other.salary)
            return -1;
        if(salary>other.salary)
            return 1;
        else
            return 0;
    }

}