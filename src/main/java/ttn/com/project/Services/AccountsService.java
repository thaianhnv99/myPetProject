package ttn.com.project.Services;

import ttn.com.project.dto.AccountsDto;

public interface AccountsService {
    AccountsDto loadUserDtoByUsername(String username);
}
