package soa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import io.jsonwebtoken.Claims;
import soa.model.Tourist;
import soa.service.TouristService;
import soa.service.JWTService;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    TouristService TS;
    JWTService JS;

    public TouristController(TouristService TS, JWTService JS)
    {
        this.TS = TS;
        this.JS = JS;
    }

    // Add Tourist
    @PostMapping
    public Object tourists(@RequestBody Tourist T)
    {
        return TS.addTourist(T);
    }

    // Update Tourist with JWT validation
    @PutMapping("/{id}")
    public Object tourists(@PathVariable Long id,
                           @RequestHeader(value = "Token", required = false) String token,
                           @RequestBody Tourist T)
    {
        try
        {
            if (token != null && !token.isBlank())
            {
                Claims claims = JS.validateToken(token);
            }

            return ResponseEntity.ok(TS.updateTourist(id, T));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(401).body("Invalid Token");
        }
    }
}