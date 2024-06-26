package me.badbones69.crazybot.bot;

import com.ryderbelserion.vital.discord.VitalDiscord;
import me.badbones69.crazybot.api.util.LogUtil;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CrazyBot extends VitalDiscord {

    public CrazyBot(@NotNull final String token, @NotNull final List<GatewayIntent> keys, @NotNull final List<CacheFlag> flags, @NotNull final String folder) {
        super(LogUtil.getLogger(), folder, token, keys, flags);
    }

    @Override
    public void ready(@NotNull final Guild guild) {

    }

    @Override
    public void start() {
        getDirectory().mkdir();
    }

    @Override
    public void ready() {

    }

    @Override
    public void stop() {

    }
}