package mk.ukim.finki.emt.lab.jobs;

import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final HostApplicationService hostApplicationService;

    public ScheduledTasks(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }


    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        this.hostApplicationService.refreshMaterializedView();
    }
}
