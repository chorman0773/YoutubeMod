package dawg.youtubemod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetCommand extends CommandBase{

	String consumerKey = "";
	String consumerSecret = "";
	String accessToken = "";
	String accessTokenSecret = "";
	
	public TweetCommand(String consumerKey,String consumerSecret,String accessToken,String accessTokenSecret)
	{
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
	}
	
	@Override
	public String getCommandName() {
		// Player will type /tweet to execute the command
		return "tweet";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/tweet 'message'";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		
		if(astring.length > 0)
		{
			String message = "";
			
			for(String s: astring)
			{
				message += s + " ";
			}
			
			try
			{
				twitter.updateStatus(message);
			}
			catch(TwitterException ex)
			{
				Utilities.SendChat(icommandsender, "Error sending tweet. Verify configuration for API tokens");
			}
			
			Utilities.SendChat(icommandsender, "Tweet sent!");
		}
		else
		{
			Utilities.SendChat(icommandsender, "Please type /tweet 'message'");
		}
			
	}

}
