package org.ibs.cds.gode.security.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/ping")
@Api(tags={"Ping the secure system"})
@PreAuthorize("hasRole('ROLE_USER')")
public class PingController {

    @GetMapping
    @ApiOperation("Ping operation")
    public ResponseEntity<Boolean> ping() {
        return ResponseEntity.ok(true);
    }

}
