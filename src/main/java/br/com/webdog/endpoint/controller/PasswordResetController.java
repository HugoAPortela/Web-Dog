package br.com.webdog.endpoint.controller;

import br.com.webdog.endpoint.controller.dto.PasswordResetDto;
import br.com.webdog.persistence.model.ResetToken;
import br.com.webdog.persistence.model.User;
import br.com.webdog.persistence.repository.ResetTokenRepository;
import br.com.webdog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResetTokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        ResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "Não foi encontrada a chave de reinicialização da Senha.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Chave expirada, por favor solicite uma nova chave para reiniciar sua Senha.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }
        return "security/reset-password";
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        ResetToken token = tokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        userService.updatePassword(updatedPassword, user.getId());
        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }
}
