package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LoggerRegistryTest {
  
  @Before
  public void setUp() {
    LoggerRegistry.reset();
  }

  @Test
  public void mergeConfigurationsCorrectly() {
    LoggingTarget console = Mockito.mock(LoggingTarget.class);
    LoggerRegistry.addTarget("console", console);
    LoggingTarget email = Mockito.mock(LoggingTarget.class);
    LoggerRegistry.addTarget("email", email);

    LoggerRegistry.setBaseConfguration(new LoggerConfiguration(LogLevels.ERROR, "console"));

    LoggerRegistry.addConfiguration("com.", new LoggerConfiguration(LogLevels.INFO));
    LoggerRegistry.addConfiguration("com.strange", new LoggerConfiguration("email"));

    Logger logger = Logger.on("com.strange");
    logger.info("info message");
    Mockito.verify(console, Mockito.never()).log(Mockito.anyInt(), Mockito.any());
    Mockito.verify(email, Mockito.times(1)).log(LogLevels.INFO.getLevel(), new Object[] {"info message"});

    Mockito.reset(console, email);

    logger = Logger.on("com");
    logger.error("error message");
    Mockito.verify(console, Mockito.times(1)).log(LogLevels.ERROR.getLevel(), new Object[] {"error message"});
    Mockito.verify(email, Mockito.never()).log(Mockito.anyInt(), Mockito.any());

    Mockito.reset(console, email);

    logger = Logger.on("com.other");
    logger.error("error message");
    Mockito.verify(console, Mockito.times(1)).log(LogLevels.ERROR.getLevel(), new Object[] {"error message"});
    Mockito.verify(email, Mockito.never()).log(Mockito.anyInt(), Mockito.any());
  }
}
