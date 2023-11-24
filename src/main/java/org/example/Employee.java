package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Employee {

    private String name;
    private Integer salary;
    private Date workStart;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Employee(String name, Integer salary, Date workStart) {
        this.name = name;
        this.salary = salary;
        this.workStart = workStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    public static ArrayList<Employee> loadStaffFromFile(String path) {
        ArrayList<Employee> employeeList= new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String stringOfEmployee;

            while((stringOfEmployee = bufferedReader.readLine()) != null){
                String[] data = stringOfEmployee.split("\t");
                if(data.length != 3) {
                    throw new java.lang.NullPointerException();
                }
                Employee employee = new Employee(data[0], Integer.parseInt(data[1]), dateFormat.parse(data[2]));
                employeeList.add(employee);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    public String toString() {

        return this.name + " — " + this.salary + " — " + dateFormat.format(this.workStart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(salary, employee.salary) && Objects.equals(workStart, employee.workStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, workStart);
    }
}
