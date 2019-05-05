/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pg.app.dao;

import br.edu.utfpr.pg.app.entity.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vinicius
 */
public class CountryDAO extends TemplateDAO<Country> {

    @Override
    public String getSQLStringInsert() {

        return "INSERT INTO country(name, acronym, phone_digits) VALUES(?, ?, ?)";
    }

    @Override
    public String getSQLStringRead() {

        return "SELECT * FROM country";
    }

    @Override
    public String getSQLStringUpdate() {

        return "UPDATE country SET name = ?, acronym = ?, phone_digits = ? WHERE id = ?";
    }

    @Override
    public String getSQLStringDelete() {

        return "DELETE FROM country WHERE id = ?";
    }

    @Override
    public ArrayList<Country> popularArrayList(ResultSet rs) {

        ArrayList<Country> countries = new ArrayList<>();

        try {
            while (rs.next()) {

                countries.add(this.carregarObjeto(rs));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return countries;
    }

    @Override
    public Country carregarObjeto(ResultSet rs) throws SQLException {

        Country country = new Country();

        country.setId(rs.getInt("id"));
        country.setName(rs.getString("name"));
        country.setAcronym(rs.getString("acronym"));
        country.setPhoneDigits(rs.getInt("phone_digits"));

        return country;
    }

    @Override
    public void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, Country entity) {

        try {

            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getAcronym());
            stmt.setInt(3, entity.getPhoneDigits());

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void popularRegistroComObjetoOperacaoUpdate(PreparedStatement stmt, Country entity) {

        try {

            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getAcronym());
            stmt.setInt(3, entity.getPhoneDigits());
            stmt.setInt(4, entity.getId());

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

    public Country read(int id) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM country WHERE id = " + id;

        Country country = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectionDAO.getInstance().getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {

                country = new Country();

                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                country.setAcronym(rs.getString("acronym"));
                country.setPhoneDigits(rs.getInt("phone_digits"));
            }

            return country;

        } finally {

            if ((rs != null) && !rs.isClosed()) {
                rs.close();
            }
            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

}
