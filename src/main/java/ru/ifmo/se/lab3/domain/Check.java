package ru.ifmo.se.lab3.domain;

import lombok.Data;
import lombok.extern.java.Log;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@Data
@Log
public class Check {

    private Double valueOfX;

    private Double valueOfY;

    private Double valueOfR;

    public void test(){
        log.info(valueOfX + " " + valueOfY + " " + valueOfR );
    }

}
