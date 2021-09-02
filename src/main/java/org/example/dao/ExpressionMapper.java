package org.example.dao;

import org.example.model.Expression;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpressionMapper implements RowMapper<Expression> {

    @Override
    public Expression mapRow(ResultSet resultSet, int i) throws SQLException {
        Expression expression = new Expression();

        expression.setId(resultSet.getInt("id"));
        expression.setExpressionList(resultSet.getString("expressionlist"));
        expression.setResult(resultSet.getString("result"));
        expression.setDate(resultSet.getTimestamp("date"));


        return expression;
    }
}
