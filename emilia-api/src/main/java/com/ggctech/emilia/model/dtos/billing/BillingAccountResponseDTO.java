package com.ggctech.emilia.model.dtos.billing;

import com.ggctech.emilia.model.dtos.BaseResponseDTO;
import com.ggctech.emilia.model.enums.BillingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BillingAccountResponseDTO extends BaseResponseDTO {
    private String referenceMonth;
    private BigDecimal amount;
    private BillingStatus billingStatus;
    private LocalDate dueDate;
    private LocalDateTime paidAt;
}
