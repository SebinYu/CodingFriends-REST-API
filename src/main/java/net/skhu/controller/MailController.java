package net.skhu.controller;

import net.skhu.dto.MailDTO;
import net.skhu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/")
    public String mail(MailDTO data, HttpServletRequest request) {
        String[] name = request.getParameterValues("name");
        String[] email = request.getParameterValues("email");

            data.setName(name[0]);
            data.setEmail(email[0]);
            String res = this.mailService.sendSimpleMessage(data);

        return res;
    }
}
