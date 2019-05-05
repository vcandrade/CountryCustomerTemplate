/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pg.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vinicius
 * @param <T>
 */
public abstract class TemplateDAO<T> {

    public abstract String getSQLStringInsert();
    public abstract String getSQLStringRead();
    public abstract String getSQLStringUpdate();
    public abstract String getSQLStringDelete();

    public abstract ArrayList<T> popularArrayList(ResultSet rs);
    public abstract T carregarObjeto(ResultSet rs) throws SQLException;

    public abstract void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, T entity);
    public abstract void popularRegistroComObjetoOperacaoUpdate(PreparedStatement stmt, T entity);
    public abstract void popularRegistroComObjetoOperacaoDelete(PreparedStatement stmt, int id);

    public void create(T entity) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = ConnectionDAO.getInstance().getConnection();

            stmt = conn.prepareStatement(this.getSQLStringInsert());
            this.popularRegistroComObjetoOperacaoInsert(stmt, entity);

            stmt.execute();

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public ArrayList<T> read() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectionDAO.getInstance().getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(this.getSQLStringRead());

            return this.popularArrayList(rs);

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

    public void update(T entity) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = ConnectionDAO.getInstance().getConnection();

            stmt = conn.prepareStatement(this.getSQLStringUpdate());
            this.popularRegistroComObjetoOperacaoUpdate(stmt, entity);

            stmt.execute();

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public void delete(int id) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = ConnectionDAO.getInstance().getConnection();

            stmt = conn.prepareStatement(this.getSQLStringDelete());
            this.popularRegistroComObjetoOperacaoDelete(stmt, id);

            stmt.execute();

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}
