package br.com.webdog.endpoint.controller;

import br.com.webdog.endpoint.controller.dto.MailDto;
import br.com.webdog.endpoint.controller.dto.PasswordForgotDto;
import br.com.webdog.persistence.model.ResetToken;
import br.com.webdog.persistence.model.User;
import br.com.webdog.persistence.repository.ResetTokenRepository;
import br.com.webdog.service.EmailService;
import br.com.webdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayForgotPasswordPage() {
        return "security/forgot-password";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "security/forgot-password";
        }

        User user = userService.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "Não encontramos uma conta associada a este endereço de e-mail.");
            return "security/forgot-password";
        }

        ResetToken token = new ResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        MailDto mail = new MailDto();
        mail.setFrom("no-reply@webdog.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Solicitação de reinicialização de senha");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "http://www.webdog.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }
}
