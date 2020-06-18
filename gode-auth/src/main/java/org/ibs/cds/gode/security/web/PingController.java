package org.ibs.cds.gode.security.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<Boolean> get(final Principal principal) {
        return ResponseEntity.ok(true);
    }

}
