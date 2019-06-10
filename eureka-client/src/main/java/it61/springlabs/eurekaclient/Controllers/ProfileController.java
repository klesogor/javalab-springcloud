package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.user.AccountDetailsDto;
import it61.springlabs.data.exceptions.NotFoundException;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Auth.JwtTokenDetails;
import it61.springlabs.eurekaclient.Services.AuthService;
import it61.springlabs.eurekaclient.Services.Logger;
import it61.springlabs.eurekaclient.dal.AccountDetailsRepository;
import it61.springlabs.eurekaclient.dal.UserRepository;
import it61.springlabs.eurekaclient.entities.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    private UserRepository userRepo;
    private AuthService authService;
    private AccountDetailsRepository detailsRepo;
    private Logger logger;
    @Autowired
    public ProfileController(UserRepository repo, AuthService service, AccountDetailsRepository detailsRepo, Logger logger){
        userRepo = repo;
        authService = service;
        this.detailsRepo = detailsRepo;
        this.logger = logger;
    }

    @GetMapping(value = "/api/v1/me/profile")
    public Response<AccountDetailsDto> getDetails(){
        JwtTokenDetails details = (JwtTokenDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Response.Of(userRepo.findById(details.getUserId())
                .orElseThrow(() -> NotFoundException.of(details.getUserId(),"user"))
                .getAccountDetails()
                .toDto());
    }

    @PutMapping(value = "/api/v1/me/profile")
    public void updateProfile(@RequestBody AccountDetailsDto dto){
        JwtTokenDetails jwtTokenDetails = (JwtTokenDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Updating account details for user " + jwtTokenDetails.getUserId().toString());
        AccountDetails details = userRepo.findById(jwtTokenDetails.getUserId()).orElseThrow(() -> NotFoundException.of(jwtTokenDetails.getUserId(),"user")).getAccountDetails();
        details.setAge(dto.getAge());
        details.setCity(dto.getCity());
        details.setCompany(dto.getCompany());
        details.setEmail(dto.getEmail());
        details.setPhone(dto.getPhone());

        detailsRepo.save(details);
    }

    @DeleteMapping(value = "/api/v1/me/profile")
    public void deleteProfile(){
        JwtTokenDetails jwtTokenDetails = (JwtTokenDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Deleting user account  " + jwtTokenDetails.getUserId().toString());
        authService.DeleteUser(jwtTokenDetails.getUserId());
    }
}
