package com.poseidon.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "rulename")
public class RuleName {
    // DONE: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String name;
    String description;
    String json;
    String template;
    String sqlStr;
    String sqlPart;

    public RuleName(String name, String description, String json, String template, String sql, String sqlPart) {
         this.name = name;
         this.description = description;
         this.json = json;
         this.template = template;
         this.sqlStr = sql;
         this.sqlPart = sqlPart;
    }

}
