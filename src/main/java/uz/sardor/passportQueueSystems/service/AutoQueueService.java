package uz.sardor.passportQueueSystems.service;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.output.PrinterOutputStream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import uz.sardor.passportQueueSystems.component.WebSocketComponent;
import uz.sardor.passportQueueSystems.entity.AutoQueue;
import uz.sardor.passportQueueSystems.entity.TypeQueue;
import uz.sardor.passportQueueSystems.repository.AutoQueueRepository;

import javax.print.PrintService;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AutoQueueService {

    final WebSocketComponent webSocket;
    final AutoQueueRepository autoQueueRepository;
    int queueA = 0;
    int queueB = 0;
    int queueC = 0;
    int queueD = 0;

    public String generateQueue(String type) throws InterruptedException {
        switch (type) {
            case "a" -> {
                autoQueueRepository.save(AutoQueue.builder()
                        .queue(queueA + 1)
                        .name("A bo'lim")
                        .typeQueue(TypeQueue.A)
                        .build());
                queueA++;
                Thread.sleep(500);

            }
            case "b" -> {

                autoQueueRepository.save(AutoQueue.builder()
                        .queue(queueB + 1)
                        .name("B bo'lim")
                        .typeQueue(TypeQueue.B)
                        .build());
                queueB++;
                Thread.sleep(500);
            }
            case "c" -> {
                autoQueueRepository.save(AutoQueue.builder()
                        .queue(queueC + 1)
                        .name("C bo'lim")
                        .typeQueue(TypeQueue.C)
                        .build());
                queueC++;
                Thread.sleep(500);
            }
            case "d" -> {
                autoQueueRepository.save(AutoQueue.builder()
                        .queue(queueD + 1)
                        .name("D bo'lim")
                        .typeQueue(TypeQueue.D)
                        .build());
                queueD++;
                Thread.sleep(500);
            }
        }

//      return admin page
        return "redirect:/generate";
    }

    public String getNextQueue() throws InterruptedException {
        try {
            Optional<AutoQueue> lastQueue = autoQueueRepository.getLastQueue();
            webSocket.sendQueue(lastQueue.get());
            printCheck(lastQueue.get());
            autoQueueRepository.deleteById(lastQueue.get().getId());
            Thread.sleep(2000);
            return "redirect:/control";
        } catch (NoSuchElementException | IOException e) {
            webSocket.senText("Navbatlar yo'q");
            return "redirect:/control";
        }
    }

    private void printCheck(AutoQueue autoQueue) throws IOException {
        String headText="\n"+autoQueue.getName()+"\n";
        String printText =headText+
                "Navbat: " + autoQueue.getQueue() +
                "\nSana: " + autoQueue.getCreated()+
                "\n - - - - - - -";
        Style styleHeader=new Style().setFontSize(Style.FontSize._2, Style.FontSize._4)
                .setFontName(Style.FontName.Font_C)
                .setJustification(EscPosConst.Justification.Center)
                .setBold(true)
                .setColorMode(Style.ColorMode.WhiteOnBlack);
        PrintService printService = PrinterOutputStream.getPrintServiceByName("XP-58");
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
        EscPos escpos = new EscPos(printerOutputStream);
        escpos.writeLF(styleHeader,"Passport Queue")
                .writeLF(checkStyle(),printText);
        escpos.feed(5).cut(EscPos.CutMode.FULL);
        escpos.close();
    }

    private Style checkStyle() {
        return new Style()
                .setFontName(Style.FontName.Font_B)
                .setJustification(EscPosConst.Justification.Left_Default)
                .setFontSize(Style.FontSize._2, Style.FontSize._2);
    }

}
