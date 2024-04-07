package com.example.demo8.controller;

import com.example.demo8.model.Department;
import com.example.demo8.model.Employee;
import com.example.demo8.service.DepartmentService;
import com.example.demo8.service.EmployeeService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "employeecontroller",value = "/home")
public class EmployeeController extends HttpServlet {

    EmployeeService employeeService = new EmployeeService();

    DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              String action = req.getParameter("action");
              switch (action) {
                  case "showAdd":
                      showAdd(req, resp);
                      break;
                      case "showEdit":
                          showEdit(req, resp);
                          break;
                          case "showHome":
                              showHome(req, resp);
                              break;
              }
    }
    public void showHome(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
          try {
              List<Employee> employees = employeeService.getAll();
              System.out.println(employees);
              req.setAttribute("listEmployee", employees);
              RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/home.jsp");
              requestDispatcher.forward(req, resp);
          }catch (Exception e) {

          }
    }
    public void showAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/add.jsp");
            List<Department> departments = departmentService.getAll();
            req.setAttribute("departments", departments);
            requestDispatcher.forward(req, resp);
        }catch (SQLException e){

        }
    }
    public void showEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/edit.jsp");
        try {
            String id = req.getParameter("EmployId");
            Employee employee = employeeService.getEmploy(id);
            req.setAttribute("em", employee);
            List<Department> departments = departmentService.getAll();
            req.setAttribute("departments", departments);
            requestDispatcher.forward(req, resp);
        }catch (SQLException e){
               e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addEm":
                addEmployee(req, resp);
                break;
                case "delete":
                    delete(req,resp);
                    break;
                    case "update":
                        editEmployee(req, resp);
                        break;
        }
    }
    public void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phonenumber = req.getParameter("phonenumber");
        double salary = Double.parseDouble(req.getParameter("salary"));
        String department_id = req.getParameter("department");
        try {
        employeeService.add(new Employee(name,email,address,phonenumber,salary,"0"), department_id);
            resp.sendRedirect("home?action=showHome");
        }catch (Exception e) {}
    }
    public void editEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String id = req.getParameter("EmployId");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phonenumber = req.getParameter("phonenumber");
        double salary = Double.parseDouble(req.getParameter("salary"));
        String department_id = req.getParameter("department");
        try {
            employeeService.edit(new Employee(Integer.valueOf(id),name,email,address,phonenumber,salary,""),department_id);
        }catch (Exception e) {
                e.printStackTrace();
        }
        resp.sendRedirect("home?action=showHome");*
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String id = req.getParameter("EmployId");
        try {
            employeeService.delete(id);
            resp.sendRedirect("home?action=showHome");
        }catch (Exception e){

        }
    }
}
