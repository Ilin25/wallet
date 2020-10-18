package ru.ilin.wallet.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.models.TypeOperation;
import ru.ilin.wallet.models.User;
import ru.ilin.wallet.services.OperationService;
import ru.ilin.wallet.services.UserService;
import ru.ilin.wallet.services.WalletService;
import ru.ilin.wallet.util.PeriodOperation;

@Controller
@RequestMapping("/operation")
@Getter
@Setter
public class OperationController {

    private final OperationService operationService;
    private final UserService userService;
    private final WalletService walletService;

    @Autowired
    public OperationController(OperationService operationService, UserService userService, WalletService walletService) {
        this.operationService = operationService;
        this.userService = userService;
        this.walletService = walletService;
    }


    @GetMapping("/operationActionMenu")//возвращает меню действий с операциями со списком операций за месяц по умолчанию
    public String operationActionMenuForm(Model model) {
        PeriodOperation periodOperation = new PeriodOperation();
        model.addAttribute("operations", operationService.getAllPeriodOperation(periodOperation));
        model.addAttribute("accountBalance", walletService.getAccountBalance());
        model.addAttribute("periodOperation",periodOperation);
        return "/wallet/operations/operationActionMenu";
    }

    @GetMapping("/addOperation")//Возврат формы добавления операции
    public String addOperationForm(Model model) {
        model.addAttribute("operation", new Operation());
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUser());//для реализации выпадающего списка
        model.addAttribute("typeOperations", TypeOperation.returnTypeOperations());//для реализации выпадающего списка
        return "/wallet/operations/addOperations";
    }

    @PostMapping("/addOperation")//добавление новой операции в базу
    public String addOperation(@RequestParam("user") User user,
                               Operation operation) {
        operation.getUser().setId(user.getId());//присвоение id (внешний ключ)
        operationService.addOperation(operation);
        return "redirect:/operation/operationActionMenu";
    }

    @GetMapping("/updateOperation/{id}")//возврат формы изменения операции
    public String updateOperationForm(@PathVariable("id") int id,
                                      Model model) {
        model.addAttribute("operation", operationService.getOperationId(id));
        model.addAttribute("users", userService.getAllUser());//для реализации выпадающего списка
        model.addAttribute("user", userService.getUserId(id));
        model.addAttribute("typeOperations", TypeOperation.returnTypeOperations());//для реализации выпадающего списка
        return "wallet/operations/updateOperation";
    }

    @PostMapping("/updateOperation")//изменение операции в базе
    public String updateOperation(Operation operation) {
        operationService.updateOperation(operation);
        return "redirect:/operation/operationActionMenu";
    }

    @GetMapping("/deleteOperation/{id}")//удаление операции
    public String deleteOperationById(@PathVariable int id) {
        operationService.removeOperation(id);
        return "redirect:/operation/operationActionMenu";
    }

    @GetMapping("/getAllPeriodOperation")//возврат меню операций за пределённый период
    public String getAllPeriodOperation(Model model, PeriodOperation periodOperation) {
        periodOperation.setStartPeriod(periodOperation.getStartPeriod());
        periodOperation.setEndPeriod(periodOperation.getEndPeriod());
        model.addAttribute("operation", new Operation());
        model.addAttribute("operations", operationService.getAllPeriodOperation(periodOperation));
        model.addAttribute("accountBalance", walletService.getAccountBalance());
        return "/wallet/operations/operationActionMenu";

    }

}
