package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ilin.wallet.models.Operation;
import ru.ilin.wallet.models.User;
import ru.ilin.wallet.util.PeriodOperation;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
//    @Query(value = "SELECT * FROM history_of_operations u WHERE u.id = :id", nativeQuery = true)
//    Operation getOperationId(@Param("id") int id);//возвращает операцию по id
//
//    void addOperation(Operation operation);//добавляет операцию в базу
//
//    void removeOperation(int id);//удаляет операцию из базы
//
//    void updateOperation(Operation operation);//обновляет запись операции в базе
//    @Query(value = "SELECT * FROM history_of_operations", nativeQuery = true)
//    List<Operation> getAllPeriodOperation(String startPeriod, String endPeriod);//возвращает список всех операций из базы

}
