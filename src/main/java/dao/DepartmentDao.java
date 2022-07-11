package dao;

import Connect_MySQL.Connect_MySQL;
import model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements CRUD<Department> {

    @Override
    public List<Department> getAll() {
        String sql = "select * from department";
        List<Department> departments = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idDepartment = resultSet.getInt(1);
                String nameDepartment = resultSet.getString(2);
                departments.add(new Department(idDepartment, nameDepartment));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return departments;
    }

    @Override
    public boolean create(Department department) {
        return false;
    }

    @Override
    public boolean edit(int id, Department department) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Department findById(int id) {
        String sql = "select * from department where idDepartment = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int idDepartment = resultSet.getInt(1);
            String nameDepartment = resultSet.getString(2);
            Department department = new Department(idDepartment, nameDepartment);
            return department;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

