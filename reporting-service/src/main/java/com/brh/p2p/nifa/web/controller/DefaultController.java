package com.brh.p2p.nifa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DefaultController {
    @GetMapping("/")
    public void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/controlpad.html");
    }
}
