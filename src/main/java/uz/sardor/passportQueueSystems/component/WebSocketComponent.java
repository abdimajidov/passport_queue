package uz.sardor.passportQueueSystems.component;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import uz.sardor.passportQueueSystems.entity.AutoQueue;


@Component
public class WebSocketComponent {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketComponent(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendQueue(AutoQueue autoQueue) {
        String text = autoQueue.getName() + "\n Navbat:" + autoQueue.getQueue() + "\n Sana:" + autoQueue.getCreated();
        messagingTemplate.convertAndSend("/topic/queue", text);
    }

    public void senText(String s) {
        messagingTemplate.convertAndSend("/topic/queue", s);
    }
}
