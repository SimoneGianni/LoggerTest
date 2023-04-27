package com.mycompany.app;

import static com.mycompany.app.LogLevels.DEBUG;
import static com.mycompany.app.LogLevels.ERROR;
import static com.mycompany.app.LogLevels.INFO;
import static com.mycompany.app.LogLevels.WARN;

public class Logger {
  
  private LoggerConfiguration configuration;

  public Logger(LoggerConfiguration configuration) {
    this.configuration = configuration;
  }

  public static Logger on(String context) {
    return LoggerRegistry.get(context);
  }

  public static Logger on(Class<?> c) {
    return LoggerRegistry.get(c.getName());
  }

  public void log(int level, Object... data) {
    if (level >= configuration.getLevel()) {
      configuration.getTargetImplementation().log(level, data);
    }
  }

  public void log(int level, Object message) {
    if (level >= configuration.getLevel()) {
      configuration.getTargetImplementation().log(level, new Object[] {message});
    }
  }

  public void log(int level, Object message, Throwable t) {
    if (level >= configuration.getLevel()) {
      configuration.getTargetImplementation().log(level, new Object[] {message, t});
    }
  }

  public void log(int level, Throwable t) {
    if (level >= configuration.getLevel()) {
      configuration.getTargetImplementation().log(level, new Object[] {t});
    }
  }

  public void debug(Object... data) {
    log(DEBUG.getLevel(), data);
  }
  public void debug(Object message) {
    log(DEBUG.getLevel(), message);
  }
  public void debug(Object message, Throwable t) {
    log(DEBUG.getLevel(), message, t);
  }
  public void debug(Throwable t) {
    log(DEBUG.getLevel(), t);
  }

  public void info(Object... data) {
    log(INFO.getLevel(), data);
  }
  public void info(Object message) {
    log(INFO.getLevel(), message);
  }
  public void info(Object message, Throwable t) {
    log(INFO.getLevel(), message, t);
  }
  public void info(Throwable t) {
    log(INFO.getLevel(), t);
  }

  public void warn(Object... data) {
    log(WARN.getLevel(), data);
  }
  public void warn(Object message) {
    log(WARN.getLevel(), message);
  }
  public void warn(Object message, Throwable t) {
    log(WARN.getLevel(), message, t);
  }
  public void warn(Throwable t) {
    log(WARN.getLevel(), t);
  }

  public void error(Object... data) {
    log(ERROR.getLevel(), data);
  }
  public void error(Object message) {
    log(ERROR.getLevel(), message);
  }
  public void error(Object message, Throwable t) {
    log(ERROR.getLevel(), message, t);
  }
  public void error(Throwable t) {
    log(ERROR.getLevel(), t);
  }
  
}
