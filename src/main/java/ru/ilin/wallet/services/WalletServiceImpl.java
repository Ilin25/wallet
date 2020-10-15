package ru.ilin.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilin.wallet.models.Operation;

@Service

public class WalletServiceImpl implements WalletService {
    private OperationService operationService;

    @Autowired
    public WalletServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public double getAccountBalance() {
        double sum = operationService.getAllOperation().stream().mapToDouble(Operation::getTransactionAmount).sum();
        return sum;
    }
}
