package me.badbones69.crazybot.bot.util;

import me.badbones69.crazybot.api.discord.JavaBot;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MiscUtil {

    public static <T extends JavaBot> T getInstance(@NotNull Class<T> classObject) {
        try {
            Constructor<T> constructor = classObject.getDeclaredConstructor();

            constructor.setAccessible(true); // Allow access to private constructors

            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException("Error creating instance of " + classObject, exception);
        }
    }
}