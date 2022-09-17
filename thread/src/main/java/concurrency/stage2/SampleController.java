package concurrency.stage2;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

    private static final Logger log = LoggerFactory.getLogger(SampleController.class);

    private static final AtomicInteger count = new AtomicInteger(0);

    private final HelloWorldService helloWorldService;

    @Autowired
    public SampleController(final HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/test")
    @ResponseBody
    public ModelAndView helloWorld(HttpServletRequest request) throws InterruptedException {
        log.info("http call count : {}", count.incrementAndGet());

        HttpSession session = request.getSession();
        String name = "테스트";
        session.setAttribute("sessionId", name);

        session.isNew();

        ModelAndView model = new ModelAndView("test");

        return model;
    }
}
