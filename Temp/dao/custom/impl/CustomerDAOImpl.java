package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        String sql="INSERT INTO CUSTOMER VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());

    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE FROM CUSTOMER WHERE ID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        String sql="UPDATE CUSTOMER SET NAME=?,ADDRESS=?,SALARY=? WHERE ID =?";
        return CrudUtil.executeUpdate(sql,customer.getName(),customer.getAddress(),customer.getSalary(),customer.getId());
    }

    @Override
    public Customer search(String s) throws Exception {
        String sql="SELECT*FROM CUSTOMER WHERE ID=?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, s);
        if (resultSet.next()){
            Customer customer=new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
            return customer;
        }return null;

    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ArrayList<Customer>all=new ArrayList<>();
        String sql="SELECT*FROM CUSTOMER ";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        while (resultSet.next()){
            Customer customer=new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
            all.add(customer);

        }return all;

    }
}
