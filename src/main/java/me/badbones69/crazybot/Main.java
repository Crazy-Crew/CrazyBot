import me.badbones69.crazybot.bot.CrazyBot;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import java.util.List;

public static void main() {
    new CrazyBot(
            System.getenv("crazybot"),
            List.of(
                    GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_PRESENCES,


                    GatewayIntent.MESSAGE_CONTENT
            ),
            List.of(
                    CacheFlag.ACTIVITY
            ),
            "crazybot"
    ).start();
}