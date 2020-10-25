package ru.ilin.wallet.services;

import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.util.PeriodOperation;

import java.util.List;

public interface OperationService {
    Operation getOperationId(int id);//возвращает операцию по id

    void addOperation(Operation operation);//добавляет операцию в базу

    void removeOperation(int id);//удаляет операцию из базы

    void updateOperation(Operation operation);//обновляет запись операции в базе

    List<Operation> getAllPeriodOperation(PeriodOperation periodOperation);//возвращает список всех операций из базы

}
