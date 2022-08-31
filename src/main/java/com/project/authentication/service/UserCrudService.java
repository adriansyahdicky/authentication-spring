package com.project.authentication.service;

import com.project.authentication.dto.RequestCreateUser;
import com.project.authentication.dto.RequestLoginDto;
import com.project.authentication.dto.ResponseApplication;
import com.project.authentication.dto.ResponseLoginDto;
import com.project.authentication.entity.DataUser;
import com.project.authentication.repository.DataUserRepository;
import com.project.authentication.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCrudService {
    @Autowired
    private DataUserRepository dataUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userDetailsService;


    public ResponseApplication<DataUser> save(RequestCreateUser requestCreateUser){
        try {
            return ResponseApplication.result(dataUserRepository.save(DataUser
                    .builder()
                    .name(requestCreateUser.getName())
                    .username(requestCreateUser.getUsername())
                    .password(passwordEncoder.encode(requestCreateUser.getPassword()))
                    .build()));
        }catch (Exception ex){
            log.error("Error save {} ", ex.getMessage());
            throw ex;
        }
    }

    public ResponseApplication<ResponseLoginDto> login(RequestLoginDto requestLoginDto) throws Exception {
        try {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(requestLoginDto.getUsername());
            authenticate(requestLoginDto.getUsername(), requestLoginDto.getPassword());
            return ResponseApplication.result(ResponseLoginDto.builder()
                    .token(jwtTokenUtil.generateToken(userDetails))
                    .build());
        }catch (UsernameNotFoundException e){
            log.error("User Not Found {} ", e.getMessage());
            throw e;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
