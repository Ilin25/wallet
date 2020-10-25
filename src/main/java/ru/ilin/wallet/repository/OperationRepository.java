package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.models.TypeOperation;
import ru.ilin.wallet.models.User;
import ru.ilin.wallet.util.PeriodOperation;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    //возвращает операцию по id
    @Query(value = "SELECT * FROM history_of_operations o WHERE o.id_operation = :id", nativeQuery = true)
    Operation getOperationById(@Param("id") int id);

    //добавляет операцию в базу
    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO history_of_operations(date,transaction_amount,type_of_operation,description_of_operations,user_id)" +
            " VALUES (:dateOperation,:transactionAmount,:typeOperations,:descriptionOperations,:userId)", nativeQuery = true)
    void addOperation(@Param("dateOperation") String date,
                      @Param("transactionAmount") double amount,
                      @Param("typeOperations") String typeOperation,
                      @Param("descriptionOperations") String descriptionOperation,
                      @Param("userId") int id);
//удаляет операцию из базы
//    void removeOperation(int id);
    //обновляет запись операции в базе
    @Modifying
    @Transactional
    @Query(value = "update Operation u set u.date = ?2,u.transactionAmount = ?3,u.typeOperation = ?4,u.descriptionOfOperation =?5, u.user.id = ?6 WHERE u.id = ?1")
    void updateOperation(int id, LocalDate date, double amount, TypeOperation typeOperation, String descriptionOperation, int userId);

//    @Query(value = "SELECT * FROM history_of_operations", nativeQuery = true)
//    List<Operation> getAllPeriodOperation(String startPeriod, String endPeriod);//возвращает список всех операций из базы

}
