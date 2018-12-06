package ru.ifmo.se.lab3.beans;

import lombok.Getter;
import lombok.Setter;
import ru.ifmo.se.lab3.Repository.CheckRepository;
import ru.ifmo.se.lab3.domain.Check;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean(eager = true)
@ApplicationScoped
@Getter
@Setter
public class HistoryBean implements Serializable {

    @ManagedProperty("#{oracleRepository}")
    private CheckRepository repository;

    public List<Check> getHistory(){
        return repository.getHistory();
    }
}
