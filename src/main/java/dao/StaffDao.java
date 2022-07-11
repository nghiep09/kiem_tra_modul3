package dao;

import Connect_MySQL.Connect_MySQL;
import model.Department;
import model.Staff;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffDao implements CRUD<Staff> {
    DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public List<Staff> getAll() {
        String sql = "select * from Staff";
        List<Staff> staffs = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date date = resultSet.getDate("dateOfBirth");
                LocalDate dateOfBirth = date.toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                Department department = departmentDao.findById(resultSet.getInt("idDepartment"));
                staffs.add(new Staff(id, name, dateOfBirth, address, phoneNumber, email, department));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffs;
    }


    public List<Staff> getAllByName(String name) {
        String sql = "select * from Staff where name like concat('%',?,'%')";
        List<Staff> staffs = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameS = resultSet.getString("name");
                Date date = resultSet.getDate("dateOfBirth");
                LocalDate dateOfBirth = date.toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");

                Department department = departmentDao.findById(resultSet.getInt("iddepartment"));
                staffs.add(new Staff(id, nameS, dateOfBirth, address,phoneNumber, email,department));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffs;
    }




    @Override
    public boolean create(Staff staff) {
        String sql = "insert into Staff value (?,?,?,?,?,?,?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, String.valueOf(staff.getDateOfBirth()));
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getPhoneNumber());
            preparedStatement.setString(6, staff.getEmail());
            preparedStatement.setInt(7, staff.getDepartment().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(int id, Staff staff) {
        String sql = "UPDATE Staff SET name = ?,dateOfBirth = ?, " +
                "address = ?, phoneNumber = ?,email = ?, iddepartment=? WHERE (id = ?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(7, staff.getId());
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, String.valueOf(staff.getDateOfBirth()));
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getPhoneNumber());
            preparedStatement.setString(5, staff.getEmail());

            preparedStatement.setInt(6, staff.getDepartment().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from Staff WHERE id = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Staff findById(int id) {
        String sql = "select * from Staff where id = " + id;
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            int idS = resultSet.getInt("id");
            String name = resultSet.getString("name");
            LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateOfBirth"));
            String address = resultSet.getString("address");
            String phoneNumber = resultSet.getString("phoneNumber");
            String email = resultSet.getString("email");
            Department department = departmentDao.findById(resultSet.getInt("iddepartment"));

            return new Staff(idS, name, dateOfBirth, address, phoneNumber, email, department);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

