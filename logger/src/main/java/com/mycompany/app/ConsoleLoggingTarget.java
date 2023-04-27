package com.mycompany.app;

import java.util.Arrays;

public class ConsoleLoggingTarget implements LoggingTarget {

  @Override
  public void log(int level, Object[] data) {
    System.out.println(level + ":" + Arrays.toString(data));
  }

}
