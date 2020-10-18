package ru.ilin.wallet.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter

public class PeriodOperation {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startPeriod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endPeriod;


    public PeriodOperation() {
        this.startPeriod = getStartPeriodOperation();
        this.endPeriod = getEndPeriodOperation();
    }

    private static LocalDate getStartPeriodOperation(){
        return LocalDate.now().minusMonths(1);
    }

    private static LocalDate getEndPeriodOperation(){
        return LocalDate.now();
    }

}
