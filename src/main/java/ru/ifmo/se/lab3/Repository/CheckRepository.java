package ru.ifmo.se.lab3.Repository;

import ru.ifmo.se.lab3.domain.Check;

import java.util.List;

public interface CheckRepository {
    List <Check> getHistory();
}
