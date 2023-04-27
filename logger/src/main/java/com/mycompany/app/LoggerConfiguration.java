package com.mycompany.app;

public class LoggerConfiguration {
  
  private int level = -1;
  private String target;
  private LoggingTarget targetImplementation;

  public LoggerConfiguration() {
  }

  public LoggerConfiguration(LogLevels level, String target) {
    this.level = level.getLevel();
    this.target = target;
  }

  public LoggerConfiguration(LogLevels level) {
    this.level = level.getLevel();
  }

  public LoggerConfiguration(String target) {
    this.target = target;
  }

  public void mergeOther(LoggerConfiguration other) {
    if (other.level != -1) {
      this.level = other.level;
    }
    if (other.target != null) {
      this.target = other.target;
    }
    if (other.targetImplementation != null) {
      this.targetImplementation = other.targetImplementation;
    }
  }

  public void setLevel(int level) {
    this.level = level;
  }
  public int getLevel() {
    return level;
  }

  public void setTargetImplementation(LoggingTarget target) {
    this.targetImplementation = target;
  }
  public LoggingTarget getTargetImplementation() {
    return targetImplementation;
  }

  public void setTarget(String targetName) {
    this.target = targetName;
  }
  public String getTarget() {
    return target;
  }


}
