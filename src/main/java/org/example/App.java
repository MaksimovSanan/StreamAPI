package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static ArrayList<Employee> staff;

    public static void main(String[] args) {
        /*
        TODO проинициализируйте переменную staff, в переменной path хранится путь до файла с staff.txt,
          где хранятся данные о сотрудниках в формате:
            Жанна Ковалёва	78000	20.02.2018
            Степан Богданов	140000	17.04.2016
            Виктор Рязанов	95000	20.03.2017
        */
        String path = "src/main/resources/employee.txt";
        staff = Employee.loadStaffFromFile(path);

        System.out.println(findEmployeeWithHighestSalary(2018));

        ArrayList<Employee> sortEmployee= sortEmployee("salary");
        for(Employee employee: sortEmployee) {
            System.out.println(employee);
        }
    }

    public static Employee findEmployeeWithHighestSalary(int year) {
        /*
        TODO реализуйте метод поиска сотрудника с максимальной заработной платой за указанный год — year,
         обработайте возможные ошибки ввода.
         используйте Stream API.
         */
        if(year < 1) {
            throw new IllegalArgumentException("year must be cant be negative");
        }
        Optional<Employee> res = staff.stream().filter(employee -> (employee.getWorkStart().getYear() + 1900) == year)
                .max(Comparator.comparing(Employee::getSalary));
        if(!res.isPresent()) {
            throw new IllegalArgumentException("Employee in this year bot found");
        }

        return res.get();
    }

    public static ArrayList<Employee> sortEmployee(String column) {
        /*
        TODO отсортируйте список сотрудников по указанной колонке — column, это может быть name, salary или date,
         и верните список сотрудников, обработайте возможные ошибки ввода.
         Используйте Stream API.
         */
        if(column == null) {
            throw new IllegalArgumentException("sort parameter must not be null");
        }
        Comparator<Employee> EmployeeComparator;
        switch(column) {
            case "name": EmployeeComparator = Comparator.comparing(Employee::getName); break;
            case "salary":  EmployeeComparator = Comparator.comparing(Employee::getSalary); break;
            case "date":    EmployeeComparator = Comparator.comparing(Employee::getWorkStart); break;
            default:
                throw new IllegalArgumentException("sort parameter must be employee field");
        }
        return staff.stream().sorted(EmployeeComparator).collect(Collectors.toCollection(ArrayList::new));
    }
}
