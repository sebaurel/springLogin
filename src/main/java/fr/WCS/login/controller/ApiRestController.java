package fr.WCS.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.WCS.login.dao.AppUserDAO;
import fr.WCS.login.service.UserDetailsServiceImpl;
import fr.WCS.login.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ApiRestController {

    @Autowired
    private AppUserDAO appUserDAO;


    //Récupérer un user par son nom
    @GetMapping(value="/userlogin/{userName}")
    public AppUser afficherUnUser(@PathVariable String userName) {
        AppUser appUser = this.appUserDAO.findUserAccount(userName);

        return appUser;
    }

    @PostMapping(path = "/createuser", consumes = "application/json", produces = "application/json")
    public String addMember(@RequestBody String json) {
        AppUser appUser = new AppUser();
        ObjectMapper mapper = new ObjectMapper();
        try {
            appUser = mapper.readValue(json, AppUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int valide = appUserDAO.CreateUserAccount(appUser);
        if (valide == 0){
            return "Utilisateur entrée dans la base de donnée";
        } else {
            return "erreur";
        }
    }
}
