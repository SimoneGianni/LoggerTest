package com.mycompany.app;

public enum LogLevels {
  DEBUG(0),
  INFO(1),
  WARN(2),
  ERROR(3);

  private int level;

  LogLevels(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }
}
