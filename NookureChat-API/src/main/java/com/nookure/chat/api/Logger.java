package com.nookure.chat.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public interface Logger {
  /**
   * Log a message to the console
   * @param message The message to log
   */
  default void info(String message) {
    info(Component.text(message).color(NamedTextColor.WHITE));
  }

  /**
   * Log a warning to the console
   * @param message The message to log
   */
  default void warning(String message) {
    warning(Component.text(message).color(NamedTextColor.YELLOW));
  }

  /**
   * Log a severe error to the console
   * @param message The message to log
   */
  default void severe(String message) {
    severe(Component.text(message).color(NamedTextColor.RED));
  }

  /**
   * Log a debug message to the console
   * @param message The message to log
   */
  default void debug(String message) {
    debug(Component.text(message).color(NamedTextColor.GRAY));
  }

  /**
   * Log a message to the console
   * @param component The component to log
   * @see net.kyori.adventure.text.Component
   */
  void info(Component component);

  /**
   * Log a warning to the console
   * @param component The component to log
   * @see net.kyori.adventure.text.Component
   */
  void warning(Component component);

  /**
   * Log a severe error to the console
   * @param component The component to log
   * @see net.kyori.adventure.text.Component
   */
  void severe(Component component);

  /**
   * Log a debug message to the console
   * @param component The component to log
   * @see net.kyori.adventure.text.Component
   */
  void debug(Component component);
}
