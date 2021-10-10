package org.example.dao;

import org.example.model.Expression;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExpressionRepository extends PagingAndSortingRepository<Expression, Long> {

    @Query(value = "SELECT * FROM expressionstable ORDER BY date DESC LIMIT ?1", nativeQuery = true)
    @NonNull Iterable<Expression> findLast(int count);

    List<Expression> findAllByOrderByDateAsc();
}
