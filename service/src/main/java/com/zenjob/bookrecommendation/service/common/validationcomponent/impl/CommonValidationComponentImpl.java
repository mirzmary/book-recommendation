package com.zenjob.bookrecommendation.service.common.validationcomponent.impl;

import com.zenjob.bookrecommendation.service.common.validationcomponent.CommonValidationComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Optional;

@Component
@Qualifier("commonValidationComponentImpl")
public class CommonValidationComponentImpl implements CommonValidationComponent {

    @Override
    public boolean validateString(final String str) {
        return Optional.ofNullable(str)
                .filter(s -> s.trim().length() > 0)
                .isPresent();
    }

    @Override
    public boolean validateAmount(final BigDecimal amount) {
        return Optional.ofNullable(amount)
                .filter(a -> a.compareTo(BigDecimal.ZERO) > 0)
                .isPresent();
    }

    @Override
    public boolean validateDateAfter(final LocalDate firstDate, final LocalDate lastDate) {
        return lastDate != null && Optional.ofNullable(firstDate)
                .filter(d -> d.compareTo(lastDate) <= 0)
                .isPresent();
    }

    @Override
    public boolean validateDateBetween(final LocalDate dateToCheck, final LocalDate firstDate, final LocalDate lastDate) {
        return firstDate != null && lastDate != null &&
                Optional.ofNullable(dateToCheck)
                        .filter(d -> d.compareTo(firstDate) >= 0)
                        .filter(d -> d.compareTo(lastDate) <= 0)
                        .isPresent();
    }

    @Override
    public boolean validateWeekday(final LocalDate date) {
        return Optional.ofNullable(date)
                .filter(d -> !EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
                        .contains(d.getDayOfWeek()))
                .isPresent();
    }
}
