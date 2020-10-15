package ru.ilin.wallet.services;

import ru.ilin.wallet.models.Operation;

import java.util.List;

public interface OperationService {
    Operation getOperationId(int id);

    void addOperation(Operation operation);

    void removeOperation(int id);

    void updateOperation(Operation operation);

    List<Operation> getAllOperation();

    List<Operation> getAllPeriodOperation(String startPeriod, String endPeriod);
}
