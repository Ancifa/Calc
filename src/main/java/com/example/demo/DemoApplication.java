package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class DemoApplication {

/*    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Page page = restTemplate.getForObject
                ("http://iss.moex.com/iss/engines.json", Page.class);
        Engines engines = page.getEngines();
        System.out.println("Data:    " + engines.getData());
        System.out.println("Columns: " + engines.getColumns());
    }*/

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
class HomeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping("/calc")
    public void test() {
        CalculatorView calculatorView = new CalculatorView();
        calculatorView.init(null);
    }
}