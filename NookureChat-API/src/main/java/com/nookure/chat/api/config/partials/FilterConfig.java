package com.nookure.chat.api.config.partials;

public abstract class FilterConfig {
  abstract public boolean isEnabled();
  abstract public String getDenyMessage();
}
