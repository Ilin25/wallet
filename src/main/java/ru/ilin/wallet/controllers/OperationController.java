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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/operation")
@Getter
@Setter
public class OperationController {

    private OperationService operationService;
    private UserService userService;
    private WalletService walletService;

    @Autowired
    public OperationController(OperationService operationService, UserService userService, WalletService walletService) {
        this.operationService = operationService;
        this.userService = userService;
        this.walletService = walletService;
    }


    @GetMapping("/operationActionMenu")//возвращает меню действий с операциями
    public String operationActionMenuForm(Model model) {
        List<Operation> operationList = operationService.getAllOperation();
        model.addAttribute("operation", new Operation());
        model.addAttribute("operations", operationList);
        model.addAttribute("accountBalance", walletService.getAccountBalance());
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

    @GetMapping("/getAllPeriodOperation")
    public String getAllOperation(@RequestParam("startPeriod") String startPeriod,
                                  @RequestParam("endPeriod") String endPeriod, Model model) {
        List<Operation> operationList = operationService.getAllPeriodOperation(startPeriod, endPeriod);
        model.addAttribute("operation", new Operation());
        model.addAttribute("operations", operationList);
        model.addAttribute("accountBalance", walletService.getAccountBalance());
        return "/wallet/operations/operationActionMenu";

    }

}
