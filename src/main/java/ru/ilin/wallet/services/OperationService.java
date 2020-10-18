package ru.ilin.wallet.services;

import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.util.PeriodOperation;

import java.util.List;

public interface OperationService {
    Operation getOperationId(int id);

    void addOperation(Operation operation);

    void removeOperation(int id);

    void updateOperation(Operation operation);

    List<Operation> getAllPeriodOperation(PeriodOperation periodOperation);

}
