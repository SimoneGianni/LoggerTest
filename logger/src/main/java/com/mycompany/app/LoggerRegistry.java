package com.mycompany.app;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerRegistry {
  
  static ConcurrentHashMap<String, Logger> registry = new ConcurrentHashMap<>();
  static Map<String, LoggerConfiguration> configuration = new TreeMap<>();
  static Map<String, LoggingTarget> targets = new HashMap<>();

  public static Logger get(String context) {
    return registry.computeIfAbsent(context, (ctx) -> new Logger(getConfiguration(ctx)));
  }

  public static void addTarget(String name, LoggingTarget target) {
    targets.put(name, target);
  }

  public static void addConfiguration(String context, LoggerConfiguration config) {
    configuration.put(context, config);
  }

  public static void setBaseConfguration(LoggerConfiguration config) {
    addConfiguration("", config);
  }



  private static LoggerConfiguration getConfiguration(String ctx) {
    // Find all configurations that match the context, considering substrings, and merge them
    LoggerConfiguration config = new LoggerConfiguration();
    // Configurations are sorted by keys, longer strings are later, so this provide efficient override for scoped contextes
    for (String key : configuration.keySet()) {
      if (ctx.startsWith(key)) {
        config.mergeOther(configuration.get(key));
      }
    }
    LoggingTarget target = targets.get(config.getTarget());
    if (target == null) {
      throw new IllegalArgumentException("Target " + config.getTarget() + " not found");
    }
    config.setTargetImplementation(target);
    return config;
  }

  /**
   * Visible for testing only.
   */
  protected static void reset() {
    registry.clear();
    configuration.clear();
    targets.clear();
  }
}
