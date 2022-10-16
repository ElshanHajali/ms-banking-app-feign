package az.company.accounts.service;

import az.company.accounts.client.LoansClient;
import az.company.accounts.dao.entity.AccountsEntity;
import az.company.accounts.dao.repository.AccountsRepository;
import az.company.accounts.model.request.AccountsRequest;
import az.company.accounts.model.response.AccountsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static az.company.accounts.mapper.AccountsMapper.MAP;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final LoansClient loansClient;

    @Override
    public List<AccountsResponse> getAccounts() {
        log.info("ActionLog.getAccount.start");
        var accounts = accountsRepository.findAll().stream().map(MAP::entityToResponse).collect(Collectors.toList());

        log.info("ActionLog.getAccount.success");
        return accounts;
    }

    @Override
    public AccountsResponse getAccount(long id) {
        log.info("ActionLog.getAccount.start accountId: {}", id);
        var account = fetchAccountById(id);

        log.info("ActionLog.getAccount.success account: {}", account);
        return MAP.entityToResponse(account);
    }

    @Override
    public AccountsResponse getAccountsByCustomer(long customerId) {
        log.info("ActionLog.getAccountByCustomerId.start customerId: {}", customerId);
        var responses = accountsRepository.findByCustomerIdAndAccountType(customerId);

        var accountsResponse = MAP.entityToResponse(responses);
        log.info("ActionLog.getAccountByCustomerId.success");
        return accountsResponse;
    }

    //////////////Management////////////////////

    @Transactional
    @Override
    public void saveAccount(AccountsRequest request) {
        log.info("ActionLog.saveAccount.start");

        var outstandingAmount = loansClient
                .getLoanByCustomerId(request.getCustomerId())
                .getOutstandingAmount();

        accountsRepository.save(
                MAP.requestToEntity(
                        request, outstandingAmount
                )
        );

        log.info("ActionLog.saveAccount.success");
    }

    @Override
    public void updateAccount(long accountId, AccountsRequest request) {
        log.info("ActionLog.updateAccount.start");
        var accountFromDB = fetchAccountById(accountId);

        MAP.updateDesiredEntityField(accountFromDB, request);

        accountsRepository.save(accountFromDB);
        log.info("ActionLog.updateAccount.success");
    }

    @Override
    public void updateBranchAddress(long accountId, String branchAddress) {
        log.info("ActionLog.updateBranchAddress.start");
        var accountFromDB = fetchAccountById(accountId);

        accountFromDB.setBranchAddress(branchAddress);

        accountsRepository.save(accountFromDB);
        log.info("ActionLog.updateBranchAddress.success");
    }

    @Override
    public void deleteAccount(long id) {
        log.info("ActionLog.deleteAccount.start");
        var accountById = fetchAccountById(id);

        accountsRepository.deleteById(id);

        log.info("ActionLog.deleteAccount.success account: {}", accountById);
    }

    private AccountsEntity fetchAccountById(Long id) {
        return accountsRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.accountById.error accountID: {}", id);
            return new RuntimeException();
        });
    }
}
