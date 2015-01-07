package com.titova.insurance.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STAT")
public class Stat {

    public static final String DEFAULT_DESCRIPTION = "default description";

    private int id;
    private Date date;
    private String description;

    public Stat() {
        id = 0;
        date = new Date();
        description = DEFAULT_DESCRIPTION;
    }

    public Stat(String description) {
        id = 0;
        date = new Date();
        this.description = description;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}