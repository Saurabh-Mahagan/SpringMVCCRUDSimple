package com.mahagan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.mahagan.beans.Emp;

public class EmpDao {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Emp p) {
        String sql = "insert into employee(id,name,salary,designation) values(" + p.getId() + ",'" + p.getName() + "'," + p.getSalary() + ",'" + p.getDesignation() + "')";
        return template.update(sql);
    }

    public int update(Emp p) {
        String sql = "update employee set name='" + p.getName() + "', salary=" + p.getSalary() + ",designation='" + p.getDesignation() + "' where id=" + p.getId() + "";
        return template.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from employee where id=" + id + "";
        return template.update(sql);
    }

    public Emp getEmpById(int id) {
        String sql = "select * from employee where id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Emp>(Emp.class));
    }

    public List<Emp> getEmployees() {
        return template.query("select * from employee", new RowMapper<Emp>() {
            public Emp mapRow(ResultSet rs, int row) throws SQLException {
                Emp emp = new Emp();
                emp.setId(rs.getInt(1));
                emp.setName(rs.getString(2));
                emp.setSalary(rs.getFloat(3));
                emp.setDesignation(rs.getString(4));
                return emp;
            }
        });
    }
}  