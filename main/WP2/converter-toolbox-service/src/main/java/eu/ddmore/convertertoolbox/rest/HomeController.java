package eu.ddmore.convertertoolbox.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method=RequestMethod.GET, produces={MediaType.TEXT_HTML_VALUE})
    public @ResponseBody String index() {
        return "This is an empty list";
    }
    
}
