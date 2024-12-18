package com.nookure.chat.api.config.serializer;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.Objects;

public class BukkitSoundSerializer implements TypeSerializer<Sound> {
  @Override
  public Sound deserialize(@NotNull Type type, @NotNull ConfigurationNode node) throws SerializationException {
    if (node.getString() != null) {
      return null;
    }

    return Registry.SOUNDS.get(NamespacedKey.minecraft(node.getString()));
  }

  @Override
  public void serialize(@NotNull Type type, @Nullable Sound obj, @NotNull ConfigurationNode node) throws SerializationException {
    if (obj == null) {
      return;
    }

    node.set(Objects.requireNonNull(Registry.SOUNDS.getKey(obj)).getKey());
  }
}
