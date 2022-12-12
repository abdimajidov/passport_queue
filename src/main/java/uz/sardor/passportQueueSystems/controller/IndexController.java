package uz.sardor.passportQueueSystems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.sardor.passportQueueSystems.service.AutoQueueService;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class IndexController {
    private final AutoQueueService autoQueueService;

    @GetMapping
    public String getIndex(){
        return "index";
    }
    @GetMapping("/generate")
    public String getGenerate(){
        return "generate";
    }
    @GetMapping("/control")
    public String getControl(){
        return "control";
    }

    @PostMapping("/generate")
    public String generate(@RequestParam(value = "type") String type) throws InterruptedException {
        return autoQueueService.generateQueue(type);
    }

    @PostMapping("/queue/next")
    public String getNextQueue() throws InterruptedException {
        return autoQueueService.getNextQueue();
    }

}
