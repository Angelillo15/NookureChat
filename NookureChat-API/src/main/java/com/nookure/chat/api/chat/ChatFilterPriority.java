package com.nookure.chat.api.chat;

public enum ChatFilterPriority {
  LOWEST(0),
  LOW(1),
  NORMAL(2),
  HIGH(3),
  HIGHEST(4);

  private final int priority;

  ChatFilterPriority(int priority) {
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }
}
