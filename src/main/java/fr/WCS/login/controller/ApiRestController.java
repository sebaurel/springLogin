package fr.WCS.login.controller;

import fr.WCS.login.dao.AppUserDAO;
import fr.WCS.login.service.UserDetailsServiceImpl;
import fr.WCS.login.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

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

}
