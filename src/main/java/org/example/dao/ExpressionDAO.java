package org.example.dao;

import org.example.model.Expression;
import org.example.service.SqlService;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component
public class ExpressionDAO {
    public ExpressionDAO() { }

    public List<Expression> getListExpressions() {
        List<Expression> listResult = new LinkedList<>();
        try (PreparedStatement preparedStatement = SqlService.getConnection().prepareStatement("SELECT * FROM expressionstable")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listResult.add(new Expression(
                                resultSet.getInt("id"),
                                resultSet.getString("expressionlist"),
                                resultSet.getString("result")));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public void putExpression(Expression a) {
        try (PreparedStatement preparedStatement = SqlService.getConnection().prepareStatement(
                "insert into expressionstable (expressionlist, result) values ('" + a.getExpressionList() + "','" + a.getResult() + "')")){
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteExpression(Expression a) {
        try (PreparedStatement preparedStatement = SqlService.getConnection().prepareStatement(
                "delete from expressionstable where id = '" + a.getId() + "'")){
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
