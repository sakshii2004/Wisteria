package timer;

public class TimedTask {
    private String taskName;
    private int timeLimit;

    public TimedTask(String taskName, int timeLimit) {
        this.taskName = taskName;
        this.timeLimit = timeLimit;
    }

    // Getters and setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}

