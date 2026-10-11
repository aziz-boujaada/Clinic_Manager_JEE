package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentSlot {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public AppointmentSlot(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() { return startDateTime; }
    public LocalDateTime getEndDateTime() { return endDateTime; }
    public String getStartTime() { return startDateTime.format(DateTimeFormatter.ofPattern("HH:mm")); }
    public String getEndTime() { return endDateTime.format(DateTimeFormatter.ofPattern("HH:mm")); }
}
