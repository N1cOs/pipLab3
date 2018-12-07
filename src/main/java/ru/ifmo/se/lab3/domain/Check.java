package ru.ifmo.se.lab3.domain;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.util.Date;

@ManagedBean
@RequestScoped
@Data
@Entity
@Table(name = "results")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_generator")
    @SequenceGenerator(name = "results_generator", sequenceName = "results_seq", allocationSize = 10)
    private int id;

    @Column(name = "x_value")
    private Double valueOfX;

    @Column(name = "y_value")
    private Double valueOfY;

    @Column(name = "r_value")
    private Double valueOfR;

    @Column(name = "result_date")
    private Date date;
}
