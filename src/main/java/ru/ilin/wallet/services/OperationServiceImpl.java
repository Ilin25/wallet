package ru.ilin.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.models.TypeOperation;
import ru.ilin.wallet.repository.OperationRepository;
import ru.ilin.wallet.util.PeriodOperation;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public Operation getOperationId(int id) {
        return operationRepository.getOne(id);
    }

    @Override
    public void addOperation(Operation operation) {
       checkNumberPositivity(operation);
        operationRepository.save(operation);
    }

    @Override
    public void removeOperation(int id) {
        operationRepository.deleteById(id);
    }

    @Override
    public void updateOperation(Operation operation) {//обновление операции
        checkNumberPositivity(operation);
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> getAllPeriodOperation(PeriodOperation periodOperation) {
        List<Operation> allOperationsList = operationRepository.findAll()
                .stream()
                .filter(operation -> operation.getDate().isAfter(periodOperation.getStartPeriod().minusDays(1)) && operation.getDate().isBefore(periodOperation.getEndPeriod().plusDays(1))).collect(Collectors.toList());
                allOperationsList.forEach(operation -> {
                    if (operation.getTypeOperation().equals(TypeOperation.PURCHASE)) {//если тип операции покупка
                        operation.setTransactionAmount(-operation.getTransactionAmount());//то присваивается отриц значение сумме операции
                    } else operation.setTransactionAmount(Math.abs(operation.getTransactionAmount()));//иначе положительное
                });
        return allOperationsList.stream().sorted(Comparator.comparing(Operation::getDate)).collect(Collectors.toList());
    }


    private void checkNumberPositivity(Operation operation){//гарантия сохранения в базе только положительных значений сумм операций
        if (operation.getTransactionAmount() < 0)
        {
            operation.setTransactionAmount(Math.abs(operation.getTransactionAmount()));
        }
    }

}
