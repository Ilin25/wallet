package ru.ilin.wallet.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "history_of_operations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @Column(name = "id_operation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "transaction_amount")
    private double transactionAmount;
    @Column(name = "type_of_operation")
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @Column(name = "description_of_operations")
    private String descriptionOfOperation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
