package com.nookure.chat.api.managers;

import com.google.inject.Singleton;
import com.nookure.chat.api.chat.ChatFilter;
import com.nookure.chat.api.chat.ChatFilterData;
import com.nookure.chat.api.config.partials.filters.FilterConfig;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.TreeMap;

@Singleton
public class FilterManager {
  private final HashMap<ChatFilterData, ChatFilter> filters = new HashMap<>();
  private final LinkedHashMap<String, ChatFilterData> filterData = new LinkedHashMap<>();
  private final TreeMap<String, ChatFilter> sortedFilters = new TreeMap<>();

  public void registerFilter(ChatFilter filter, FilterConfig config) {
    if (!config.isEnabled()) return;

    ChatFilterData data = filter.getClass().getAnnotation(ChatFilterData.class);
    if (data == null) {
      throw new IllegalArgumentException("ChatFilterData annotation not found on " + filter.getClass().getName());
    }

    filters.put(data, filter);
    sortedFilters.put(data.name(), filter);
    filterData.put(data.name(), data);
  }

  public Optional<ChatFilter> getFilter(String name) {
    return Optional.ofNullable(filters.get(filterData.get(name)));
  }

  public Optional<ChatFilterData> getFilterData(String name) {
    return Optional.ofNullable(filterData.get(name));
  }

  public HashMap<ChatFilterData, ChatFilter> getFilters() {
    return filters;
  }

  public LinkedHashMap<String, ChatFilterData> getFilterData() {
    return filterData;
  }

  public TreeMap<String, ChatFilter> getSortedFilters() {
    return sortedFilters;
  }
}
