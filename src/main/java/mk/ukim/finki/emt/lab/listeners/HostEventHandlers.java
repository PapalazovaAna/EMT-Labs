package mk.ukim.finki.emt.lab.listeners;

import mk.ukim.finki.emt.lab.events.HostChangedEvent;
import mk.ukim.finki.emt.lab.events.HostCreatedEvent;
import mk.ukim.finki.emt.lab.events.HostDeletedEvent;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostApplicationService hostApplicationService;

    public HostEventHandlers(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event){
        this.hostApplicationService.refreshMaterializedView();
    }

    @EventListener
    public void onHostChanged(HostChangedEvent event){
        this.hostApplicationService.refreshMaterializedView();
    }

    @EventListener
    public void onHostDeleted(HostDeletedEvent event){
        this.hostApplicationService.refreshMaterializedView();
    }
}


