package ru.ifmo.se.lab3.repository;

import ru.ifmo.se.lab3.domain.Check;

import java.util.List;

public interface CheckRepository {
    List <Check> getAllResults();

    boolean save(Check check);
}
