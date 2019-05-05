/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pg.app.dao;

import br.edu.utfpr.pg.app.entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vinicius
 */
public class CustomerDAO extends TemplateDAO<Customer> {

    @Override
    public String getSQLStringInsert() {
        return "INSERT INTO customer(name, phone, age, country) VALUES(?, ?, ?, ?)";
    }

    @Override
    public String getSQLStringRead() {
        return "SELECT * FROM customer";
    }

    @Override
    public String getSQLStringUpdate() {
        return "UPDATE customer SET name = ?, phone = ?, age = ?, country = ? WHERE id = ?";
    }

    @Override
    public String getSQLStringDelete() {
        return "DELETE FROM customer WHERE id = ?";
    }

    @Override
    public void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, Customer entity) {

        try {

            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getPhone());
            stmt.setInt(3, entity.getAge());
            stmt.setInt(4, entity.getCountry().getId());

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void popularRegistroComObjetoOperacaoUpdate(PreparedStatement stmt, Customer entity) {

        try {

            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getPhone());
            stmt.setInt(3, entity.getAge());
            stmt.setInt(4, entity.getCountry().getId());
            stmt.setInt(5, entity.getId());

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void popularRegistroComObjetoOperacaoDelete(PreparedStatement stmt, int id) {

        try {

            stmt.setInt(1, id);

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList popularArrayList(ResultSet rs) {

        ArrayList<Customer> customers = new ArrayList<>();
        
        try {
        
            while (rs.next()) {
            
                customers.add(this.carregarObjeto(rs));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer carregarObjeto(ResultSet rs) throws SQLException {

        Customer customer = new Customer();

        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setPhone(rs.getString("phone"));
        customer.setAge(rs.getInt("age"));
        customer.getCountry().setId(rs.getInt("country"));

        return customer;
    }
}
