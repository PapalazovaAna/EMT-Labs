package mk.ukim.finki.emt.lab.events;

import lombok.Getter;
import mk.ukim.finki.emt.lab.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostChangedEvent extends ApplicationEvent {
    private LocalDateTime when;

    public HostChangedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}
