package com.example.demo8.service;

import com.example.demo8.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    Connection connection = null;
    {
        try {
            connection = GetConnect.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Employee employee,String department_id) {
       try {
           PreparedStatement ps = connection.prepareStatement("INSERT INTO employee (name,address,email,phonenumber,salary,department_id) values(?,?,?,?,?,?)");
           ps.setString(1,employee.getName());
           ps.setString(2, employee.getAddress());
           ps.setString(3,employee.getEmail());
           ps.setString(4,employee.getPhonenumber());
           ps.setString(5,String.valueOf(employee.getSalary()));
           ps.setString(6,department_id);
           ps.executeUpdate();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
    public List<Employee> getAll() throws Exception{
        List<Employee> employees = new ArrayList<Employee>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT e.id,e.name,e.address,e.email,e.phonenumber,e.salary,d.name as department from employee e join department d on d.id = e.department_id");
        while (rs.next()) {
            employees.add(new Employee(rs.getInt("id"),rs.getString("name"), rs.getString("address"), rs.getString("email"),rs.getString("phonenumber"), rs.getDouble("salary"), rs.getString("department")));
        }
        return employees;
    }
    public Employee getEmploy(String id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT e.id,e.name,e.address,e.email,e.phonenumber,e.salary,d.name as department from employee e join department d on d.id = e.department_id where e.id = ?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery(); rs.next();
        return new Employee(rs.getInt("id")+1,rs.getString("name"), rs.getString("address"), rs.getString("email"),rs.getString("phonenumber"), rs.getDouble("salary"), rs.getString(6));
    }
    public void edit(Employee employee,String id) {
            try {
                System.out.println(employee.toString());
                PreparedStatement ps = connection.prepareStatement("UPDATE employee SET name = ? , address = ? , email = ? ,phonenumber = ? , salary = ? ,department_id = ? WHERE id = ?");
                ps.setString(1, employee.getName());
                ps.setString(2, employee.getAddress());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getPhonenumber());
                ps.setString(5, String.valueOf(employee.getSalary()));
                ps.setString(6,id);
                ps.setString(7,String.valueOf(employee.getId()));
                ps.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
    public void delete(String id) throws Exception{
        PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
        ps.setString(1, id);
        ps.executeUpdate();
    }
    public List<Employee> search(String name) throws SQLException{
        List<Employee> employees = new ArrayList<Employee>();
        PreparedStatement ps = connection.prepareStatement("SELECT * from employee WHERE name like ?");
        ps.setString(1,"%"+name+"%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            employees.add(new Employee(rs.getInt("id"),rs.getString("name"), rs.getString("address"), rs.getString("email"),rs.getString("phonenumber"), rs.getDouble("salary"), rs.getString("department")));
        }
        return employees;
    }
}
