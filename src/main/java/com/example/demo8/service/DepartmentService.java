package com.example.demo8.service;

import com.example.demo8.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    Connection connection = null;
    {
        try {
            connection = GetConnect.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Department> getAll() throws SQLException {
        List<Department> departmentList = new ArrayList<Department>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM department");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            departmentList.add(new Department(rs.getInt("id"),rs.getString("name")));
        }
        return departmentList;
    }
    public String getName(String id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM department where id = ?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        return rs.getString("name");
    }
}
