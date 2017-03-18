package listeners;

import net.dv8tion.jda.core.events.guild.voice.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.STATICS;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zekro on 18.03.2017 / 01:29
 * DiscordBot / listener
 * © zekro 2017
 */


public class voiceChannelListener extends ListenerAdapter {

    public String logChannel = STATICS.voiceLogChannel;
    public String getTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("[ HH:mm:ss - dd.MM ]   ");
        return df.format(date.getTime());
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {


        if (event.getGuild().getTextChannelsByName(logChannel, false).isEmpty())
            return;

        event.getGuild().getTextChannelsByName(logChannel, true).get(0).sendMessage(
                ":white_check_mark:   " + getTime() + "**" + event.getVoiceState().toString().split(":")[2] + "** joined voice channel ` " + event.getChannelJoined().getName() + " `."
        ).queue();

    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {

        if (event.getGuild().getTextChannelsByName(logChannel, false).isEmpty())
            return;

        event.getGuild().getTextChannelsByName(logChannel, true).get(0).sendMessage(
                ":arrow_right:   " + getTime() + "**" + event.getVoiceState().toString().split(":")[2] + "** went from ` " + event.getChannelLeft().getName() + " ` to ` " + event.getChannelJoined().getName() + " `."
        ).queue();

    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {

        if (event.getGuild().getTextChannelsByName(logChannel, false).isEmpty())
            return;

        event.getGuild().getTextChannelsByName(logChannel, true).get(0).sendMessage(
                ":small_red_triangle_down:   " + getTime() + "**" + event.getVoiceState().toString().split(":")[2] + "** left voice channel ` " + event.getChannelLeft().getName() +  " `."
        ).queue();

    }
}
