package ru.ifmo.se.lab3.beans;

import lombok.Getter;
import lombok.Setter;
import ru.ifmo.se.lab3.domain.Check;
import ru.ifmo.se.lab3.repository.CheckRepository;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class HistoryBean implements Serializable {

    private CheckRepository repository;

    public List<Check> getHistory(){
        List<Check> results = repository.getAllResults();
        results.sort(Comparator.comparing(Check::getDate).reversed());
        return results;
    }
}
