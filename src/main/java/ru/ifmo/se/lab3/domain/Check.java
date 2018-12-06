package ru.ifmo.se.lab3.domain;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@Data
public class Check {

    private Double valueOfX;

    private Double valueOfY;

    private Double valueOfR;
}
