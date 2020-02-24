package frc.health;

public abstract class HealthCheck {
    
    public HealthReport checkHealth(){
        return new HealthReport();
    }

}