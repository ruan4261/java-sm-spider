package com.spider.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpiderController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object view() {
        return "spiderView";
    }

}
