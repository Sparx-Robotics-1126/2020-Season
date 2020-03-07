package frc.health;

public class HealthReport {

    private String message;
    private boolean error;

    public HealthReport(){
        this.error = false;
        this.message = "Good";
    }

    public HealthReport(boolean error, String message){
        this.error = error;
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public boolean isError(){
        return error;
    }

}
