package eu.ddmore.convertertoolbox.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RelationsController {
    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${cts.support.url:}")
    private String supportUrl;
    
    @RequestMapping("/relations")
    public String index(Map<String, Object> model) {
        model.put("appName", appName);
        model.put("appVersion", appVersion);
        model.put("supportUrl", supportUrl);
        return "relations/api";
    }
}
