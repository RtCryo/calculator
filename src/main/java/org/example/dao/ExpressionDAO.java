package org.example.dao;

import org.example.model.Expression;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ExpressionDAO {
    private final JdbcTemplate jdbcTemplate;

    public ExpressionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Expression> getListExpressions() {
        return jdbcTemplate.query("SELECT * FROM expressionstable", new BeanPropertyRowMapper<>(Expression.class));
    }

    public List<Expression> getListExpressions (int num) {
        return jdbcTemplate.query("SELECT * FROM expressionstable ORDER BY date DESC LIMIT ?", new Object[]{num}, new BeanPropertyRowMapper<>(Expression.class));
    }

    public void putExpression(Expression a) {
        jdbcTemplate.update("insert into expressionstable (expressionlist, result, date) values (?,?, NOW())", a.getExpressionList(), a.getResult());
    }

    public void deleteExpression(Expression a) {
        jdbcTemplate.update("delete from expressionstable where id = ?", a.getId());
    }

    public void deleteListExpressions(String sqlStatement) {
        jdbcTemplate.update(sqlStatement);
    }

}
