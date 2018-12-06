package ru.ifmo.se.lab3.Repository;

import lombok.Getter;
import lombok.Setter;
import ru.ifmo.se.lab3.domain.Check;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "oracleRepository")
@ApplicationScoped
@Getter
@Setter
public class OracleRepository implements CheckRepository {

    @Override
    public List<Check> getHistory() {
        return null;
    }
}
