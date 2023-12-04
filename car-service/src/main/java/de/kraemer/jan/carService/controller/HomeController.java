package de.kraemer.jan.carService.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  private static final Logger log = LoggerFactory.getLogger(HomeController.class);

  @GetMapping("/home")
  public String home(Principal principal) {
    var username = principal.getName();
    if (principal instanceof JwtAuthenticationToken token) {
      log.info("claims: " + token.getTokenAttributes());
    }
    return "Hello, " + username;
  }
}
