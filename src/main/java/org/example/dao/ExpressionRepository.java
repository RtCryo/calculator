package org.example.dao;

import org.example.model.Expression;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public interface ExpressionRepository extends PagingAndSortingRepository<Expression, Long> {
    @NonNull Iterable<Expression> findAll();

    @NonNull <S extends Expression> S save(@NonNull S var1);

    void deleteAll(@NonNull Iterable<? extends Expression> list);

    void deleteById(@NonNull Long id);

    @Query(value = "SELECT * FROM expressionstable ORDER BY date DESC LIMIT ?1", nativeQuery = true)
    @NonNull Iterable<Expression> findLast(int count);
}
