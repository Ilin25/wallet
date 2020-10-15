package ru.ilin.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.models.TypeOperation;
import ru.ilin.wallet.repository.OperationRepository;

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
        if (operation.getTransactionAmount() < 0)//гарантия сохранения в базе только положительных значений сумм операций
        {
            operation.setTransactionAmount(Math.abs(operation.getTransactionAmount()));
        }
        operationRepository.save(operation);
    }

    @Override
    public void removeOperation(int id) {
        operationRepository.deleteById(id);
    }

    @Override
    public void updateOperation(Operation operation) {//обновление операции
        if (operation.getTransactionAmount() < 0)//гарантия сохранения в базе только положительных значений сумм операций
        {
            operation.setTransactionAmount(Math.abs(operation.getTransactionAmount()));
        }
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> getAllOperation() {
        List<Operation> operations = operationRepository.findAll();//при выводе списка покупок, они сортируются по дате
        Collections.sort(operations, Comparator.comparing(Operation::getDate));
        operations.stream().forEach(e -> {
            if (e.getTypeOperation().equals(TypeOperation.PURCHASE)) {//если тип операции покупка
                e.setTransactionAmount(-e.getTransactionAmount());//то присваивается отриц значение сумме операции
            } else e.setTransactionAmount(Math.abs(e.getTransactionAmount()));//иначе положительное
        });
        return operations;//возврат всех операций

    }

    @Override
    public List<Operation> getAllPeriodOperation(String startPeriod, String endPeriod) {
        LocalDate localDate = LocalDate.parse(startPeriod);
        LocalDate localDate1 = LocalDate.parse(endPeriod);
        List<Operation> operationList = operationRepository.findAll();
        return operationList.stream().filter(operation -> operation.getDate().isAfter(localDate) && operation.getDate().isBefore(localDate1)).sorted(Comparator.comparing(Operation::getDate)).collect(Collectors.toList());
    }


}
