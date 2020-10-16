package ru.ilin.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.repository.OperationRepository;

@Service

public class WalletServiceImpl implements WalletService {
    private OperationRepository operationRepository;
    @Autowired
    public WalletServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }



    @Override
    public double getAccountBalance() {
        double sum = operationRepository.findAll().stream().mapToDouble(Operation::getTransactionAmount).sum();
        return sum;
    }
}
