package com.mischievous.elf.controller;

import com.mischievous.elf.service.WelfareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lifang
 */
@Controller
@RequestMapping(value = "/welfare")
public class WelfareController {
    private static final Logger logger = LoggerFactory.getLogger(WelfareController.class);

    @Autowired
    WelfareService welfareService;

    @RequestMapping(value = "/main")
    public void toMainPage(Model model) {
        logger.warn("===toMainPage=");
    }
}
