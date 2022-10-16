package az.company.accounts.mapper;

import az.company.accounts.dao.entity.AccountsEntity;
import az.company.accounts.model.request.AccountsRequest;
import az.company.accounts.model.response.AccountsResponse;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public class AccountsMapper {
    public static AccountsMapper MAP = Mappers.getMapper(AccountsMapper.class);

    public AccountsEntity requestToEntity(AccountsRequest request,
                                          BigDecimal outstandingAmount) {
        return AccountsEntity
                .builder()
                .branchAddress(request.getBranchAddress())
                .accountType(request.getAccountType())
                .customerId(request.getCustomerId())
                .outstandingLoanAmount(outstandingAmount)
                .build();
    }

    public AccountsResponse entityToResponse(AccountsEntity account) {
        return AccountsResponse
                .builder()
                .createdAt(account.getCreatedAt())
                .accountType(account.getAccountType())
                .customerId(account.getCustomerId())
                .outstandingLoanAmount(account.getOutstandingLoanAmount())
                .branchAddress(account.getBranchAddress())
                .build();
    }

    public void updateDesiredEntityField(AccountsEntity account, AccountsRequest request) {
        if (Strings.isNotEmpty(request.getAccountType()) &&
                Strings.isNotBlank(request.getAccountType()))
            account.setAccountType(request.getAccountType());

        if (Strings.isNotEmpty(request.getBranchAddress()) &&
                Strings.isNotBlank(request.getBranchAddress()))
            account.setBranchAddress(request.getBranchAddress());

        if (request.getCustomerId() != null)
            account.setCustomerId(request.getCustomerId());
    }
}
