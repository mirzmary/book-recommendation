package com.zenjob.bookrecommendation.service.common.validationcomponent;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CommonValidationComponent {

    boolean validateString(final String str);

    boolean validateAmount(final BigDecimal amount);

    boolean validateDateAfter(final LocalDate firstDate, final LocalDate lastDate);

    boolean validateDateBetween(final LocalDate dateToCheck, final LocalDate firstDate, final LocalDate lastDate);

    boolean validateWeekday(final LocalDate date);
}
