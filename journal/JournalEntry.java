package journal;

import java.time.LocalDate;

public class JournalEntry {
    private LocalDate date;
    private String subject;
    private String entry;

    public JournalEntry(LocalDate date, String subject, String entry) {
        this.date = date;
        this.subject = subject;
        this.entry = entry;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}