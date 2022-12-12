package uz.sardor.passportQueueSystems.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "className", "created", "updated"})
public class AutoQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "meeting_seq")
    @SequenceGenerator(name = "meeting_seq", sequenceName = "meeting_seq", initialValue = 1000, allocationSize = 1)
    Long id;

    String name;
    Integer queue=0;
    LocalTime created;
    Boolean missed = false;

    @Enumerated(EnumType.STRING)
    TypeQueue typeQueue;

    @PrePersist
    public void setCurrentTime(){
        created=LocalTime.now();
    }
}
