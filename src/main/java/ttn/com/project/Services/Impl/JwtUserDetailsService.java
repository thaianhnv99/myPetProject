package ttn.com.project.Services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ttn.com.project.Entity.Accounts;
import ttn.com.project.Repositories.AccountsRepository;
import ttn.com.project.Services.AccountsService;
import ttn.com.project.config.JwtAuthenticationEntryPoint;
import ttn.com.project.dto.AccountsDto;

import java.util.ArrayList;

@Service
@Qualifier("JwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService, AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountsRepository.findByUsername(username);
        if (accounts == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        String encodedPassword = accounts.getPassword();
        accounts.setPassword(encodedPassword);
        return new User(accounts.getUsername(), encodedPassword, new ArrayList<>());
    }

    public AccountsDto loadUserDtoByUsername(String username) throws UsernameNotFoundException {
        AccountsDto accountsDto = modelMapper.map(accountsRepository.findByUsername(username), AccountsDto.class);
        if (accountsDto == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        String encodedPassword = accountsDto.getPassword();
        accountsDto.setPassword(encodedPassword);
        return accountsDto;
    }

}
