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
    private List<Expression> listResult;

    public ExpressionDAO() {}

    public List<Expression> getListExpressions() {
        listResult = new LinkedList<>();
        try (PreparedStatement preparedStatement = SqlService.getConnection().prepareStatement("SELECT * FROM expressionstable")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listResult.add(new Expression(
                                resultSet.getInt("id"),
                                resultSet.getString("expressionlist"),
                                resultSet.getString("result"),
                                resultSet.getTimestamp("date")));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public List<Expression> getListExpressions (int num) {
        listResult = new LinkedList<>();
        try (PreparedStatement preparedStatement = SqlService.getConnection().prepareStatement("SELECT * FROM expressionstable ORDER BY date DESC LIMIT ?")){
            preparedStatement.setInt(1, num);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listResult.add(new Expression(
                        resultSet.getInt("id"),
                        resultSet.getString("expressionlist"),
                        resultSet.getString("result"),
                        resultSet.getTimestamp("date")));
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
                "insert into expressionstable (expressionlist, result, date) values (?,?, NOW())")){
            preparedStatement.setString(1,a.getExpressionList());
            preparedStatement.setString(2,a.getResult());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteExpression(Expression a) {
        try (PreparedStatement preparedStatement = SqlService.getConnection().prepareStatement(
                "delete from expressionstable where id = ?")){
            preparedStatement.setInt(1,a.getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
