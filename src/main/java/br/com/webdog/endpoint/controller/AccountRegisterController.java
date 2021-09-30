package br.com.webdog.endpoint.controller;

import br.com.webdog.endpoint.controller.dto.AccountRegisterDto;
import br.com.webdog.persistence.model.User;
import br.com.webdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class AccountRegisterController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public AccountRegisterDto registrationDto(){
        return new AccountRegisterDto();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showUserAccountRegisterForm(){
        return "security/registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute("user") @Valid AccountRegisterDto accountDto,
                                      BindingResult result){
        User existing = userService.findByEmail(accountDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "Já existe uma conta registrada com este e-mail.");
        }
        if (result.hasErrors()){
            return "security/registration";
        }
        userService.save(accountDto);
        return "redirect:/registration?success";
    }
}
