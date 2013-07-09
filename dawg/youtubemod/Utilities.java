package dawg.youtubemod;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class Utilities {

	public static void SendChat(ICommandSender player, String message)
	{
		((EntityPlayer)player).addChatMessage(message);
	}
	
}
