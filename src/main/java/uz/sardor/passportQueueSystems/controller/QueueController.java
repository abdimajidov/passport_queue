package uz.sardor.passportQueueSystems.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.sardor.passportQueueSystems.service.AutoQueueService;

@Controller
@RequestMapping("/queue")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class QueueController {
    final AutoQueueService autoQueueService;
    static final String NEXT_QUEUE="/next/";
}
