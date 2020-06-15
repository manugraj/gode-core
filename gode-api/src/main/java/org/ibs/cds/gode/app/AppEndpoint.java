package org.ibs.cds.gode.app;

import org.ibs.cds.gode.system.GodeConstant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@CrossOrigin
@RestController
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value= GodeConstant.GODE_API_BASE, produces = "application/json;charset=UTF-8")
public @interface AppEndpoint {
}
