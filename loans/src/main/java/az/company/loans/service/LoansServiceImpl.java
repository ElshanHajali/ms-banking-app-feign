package az.company.loans.service;

import az.company.loans.dao.entity.LoansEntity;
import az.company.loans.dao.repository.LoansRepository;
import az.company.loans.model.request.LoansRequest;
import az.company.loans.model.response.LoansResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static az.company.loans.mapper.LoansMapper.MAP;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoansServiceImpl implements LoansService {
    private final LoansRepository loansRepository;

    /////// fetch ///////
    @Override
    public List<LoansResponse> getLoans() {
        log.info("ActionLog.getLoans.start");
        return loansRepository
                .findAll().stream()
                .map(MAP::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoansResponse getLoan(Long id) {
        log.info("ActionLog.getLoan.start");
        var response = MAP.entityToResponse(fetchLoanById(id));

        log.info("ActionLog.getLoan.success loan: {}", response);
        return response;
    }

    @Override
    public LoansResponse getLoanByCustomerId(Long customerId) {
        log.info("ActionLog.getLoanByCustomerId.start");
        return loansRepository
                .findByCustomerIdOrderByStartDtDesc(customerId)
                .stream()
                .map(MAP::entityToResponse)
                .findFirst().orElseThrow(RuntimeException::new);
    }

    ////// Management //////
    @Override
    public void saveLoan(LoansRequest request) {
        log.info("ActionLog.saveLoan.start");
        var loan = MAP.requestToEntity(request);

        loansRepository.save(loan);
        log.info("ActionLog.saveLoan.success");
    }

    @Override
    public void updateLoan(LoansRequest request, Long loansId) {
        log.info("ActionLog.updateLoan.start");
        var loan = fetchLoanById(loansId);

        MAP.updateDesiredEntityField(loan, request);

        loansRepository.save(loan);
        log.info("ActionLog.updateLoan.success");
    }

    @Override
    public void updateOutstandingAmount(Long id, BigDecimal outstandingAmount) {
        log.info("ActionLog.updateOutstandingAmount.start");
        var loan = fetchLoanById(id);
        loan.setOutstandingAmount(outstandingAmount);
        loansRepository.save(loan);
        log.info("ActionLog.updateOutstandingAmount.success");
    }

    @Override
    public void deleteLoan(Long id) {
        log.info("ActionLog.deleteLoan.start");
        loansRepository.deleteById(id);
        log.info("ActionLog.deleteLoan.success");
    }

    ////////// private methods /////////
    private LoansEntity fetchLoanById(Long id) {
        return loansRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.fetchLoanById.error");
                    return new RuntimeException();
                });
    }
}
