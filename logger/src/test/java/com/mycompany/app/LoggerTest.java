package com.mycompany.app;

import org.junit.Test;
import org.mockito.Mockito;

public class LoggerTest {

  private LoggingTarget target = Mockito.mock(LoggingTarget.class);
  
  @Test
  public void whenDebugEnabled_logsDebugStatements() {
    LoggerConfiguration config = new LoggerConfiguration();
    config.setLevel(LogLevels.DEBUG.getLevel());
    config.setTargetImplementation(target);
    Logger logger = new Logger(config);
    logger.debug("debug message");

    Mockito.verify(target, Mockito.times(1)).log(LogLevels.DEBUG.getLevel(), new Object[] {"debug message"});
  }

}
