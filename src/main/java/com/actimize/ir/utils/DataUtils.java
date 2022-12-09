package com.actimize.ir.utils;

import com.actimize.ir.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<Employee> getListOfEmpployee(){
        List<Employee> employeeList = new ArrayList<Employee>();
        Employee e1 = new Employee("Sainath","Shinde","ss@gmail.com");
        Employee e2 = new Employee("Vijay","Shinde","vs@gmail.com");
        Employee e3 = new Employee("Amit","Shinde","as@gmail.com");
        Employee e4 = new Employee("Akash","Kale","ak@gmail.com");
        Employee e5 = new Employee("Sachin","Pawar","sk@gmail.com");
        Employee e6 = new Employee("Vinod","Sardar","vs@gmail.com");
        Employee e7 = new Employee("Shailesh","Mozar","sm@gmail.com");
        Employee e8 = new Employee("vivek","Deshmukh","vd@gmail.com");
        Employee e9 = new Employee("Arun","Chavan","ss@gmail.com");
        Employee e10 = new Employee("Nilesh","Patil","ss@gmail.com");
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);
        employeeList.add(e6);
        employeeList.add(e7);
        employeeList.add(e8);
        employeeList.add(e9);
        employeeList.add(e10);
        return employeeList;
    }
}
