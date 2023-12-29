package com.nookure.chat.api.config.partials.filters;

public abstract class FilterConfig {
  abstract public boolean isEnabled();
  abstract public String getDenyMessage();
}
