package onlineshop.controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ApiShutdownController {
	

	@Autowired
    private ApplicationContext context;
 
    public void initiateAppShutdown(int returnCode) {
        SpringApplication.exit(context, () -> returnCode);
    }
 
    @RequestMapping("/shutdown")
    public void shutdown() {
        initiateAppShutdown(0);
        System.exit(0);
    }
    

}
