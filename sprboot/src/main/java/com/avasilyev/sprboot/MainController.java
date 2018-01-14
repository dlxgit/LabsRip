package com.avasilyev.sprboot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class MainController {

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  // @RequestMapping(value="/index.html", method = RequestMethod.GET)
  /*public ModelAndView getForm() {
    ModelAndView model = new ModelAndView("index.html");
    return model;
  }*/
  //@ResponseBody
  String home() {
    return "index";
  }

  @RequestMapping(value = "/index", method = RequestMethod.POST)
  // @RequestMapping(value="/index.html", method = RequestMethod.GET)
  /*public ModelAndView getForm() {
    ModelAndView model = new ModelAndView("index.html");
    return model;
  }*/
  @ResponseBody
  String hm() {
    return "index";
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(MainController.class, args);
  }
}