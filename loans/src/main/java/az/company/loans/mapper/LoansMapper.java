package az.company.loans.mapper;

import az.company.loans.dao.entity.LoansEntity;
import az.company.loans.model.request.LoansRequest;
import az.company.loans.model.response.LoansResponse;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoansMapper {
    LoansMapper MAP = Mappers.getMapper(LoansMapper.class);

    default LoansEntity requestToEntity(LoansRequest request) {
        return LoansEntity
                .builder()
                .startDt(request.getStartDt())
                .total(request.getTotal())
                .type(request.getType())
                .outstandingAmount(request.getTotal().subtract(request.getAmountPaid()))
                .amountPaid(request.getAmountPaid())
                .customerId(request.getCustomerId())
                .build();
    }

    default LoansResponse entityToResponse(LoansEntity loan) {
        return LoansResponse
                .builder()
                .createdAt(loan.getCreatedAt())
                .type(loan.getType())
                .total(loan.getTotal())
                .amountPaid(loan.getAmountPaid())
                .customerId(loan.getCustomerId())
                .startDt(loan.getStartDt())
                .outstandingAmount(loan.getOutstandingAmount())
                .build();
    }

    default void updateDesiredEntityField(LoansEntity loan, LoansRequest request) {
        var loanType = request.getType();
        var amountPaid = request.getAmountPaid();
        var totalLoan = request.getTotal();
        var customerId = request.getCustomerId();
        var startDt = request.getStartDt();

        if (Strings.isNotEmpty(loanType) && Strings.isNotBlank(loanType))
            loan.setType(loanType);
        if (amountPaid != null)
            loan.setAmountPaid(amountPaid);
        if (totalLoan != null)
            loan.setTotal(totalLoan);
        if (customerId != null)
            loan.setCustomerId(customerId);
        if (startDt != null)
            loan.setStartDt(startDt);
    }
}
