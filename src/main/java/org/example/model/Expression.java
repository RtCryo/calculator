package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "expressionstable")
public class Expression implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "expressionlist", nullable = false, updatable = false)
    private String expressionList;
    private String result;
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Expression{" +
                "id=" + id +
                ", expressionList='" + expressionList + '\'' +
                ", result='" + result + '\'' +
                ", date=" + date +
                '}';
    }
}
