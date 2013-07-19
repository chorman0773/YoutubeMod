package dawg.youtubemod;

import java.util.List;

import org.lwjgl.opengl.GL11;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class TwitterGui extends GuiScreen {
	
	List<Status> statuses;

	int currentPage = 1;
	int maxPage = 15;
	
	public TwitterGui(EntityPlayer par1EntityPlayer)
	{		this.PopulateStatuses(par1EntityPlayer);
	}

	private void PopulateStatuses(EntityPlayer par1EntityPlayer) {
    	Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(YoutubeMod.consumerKey, YoutubeMod.consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(YoutubeMod.accessToken, YoutubeMod.accessTokenSecret));

		try {
			statuses = twitter.getHomeTimeline(new Paging(1, maxPage));	    				
		} catch (TwitterException e) {
			e.printStackTrace();
			Utilities.SendChat(par1EntityPlayer, "Error getting tweets. Verify configuration is set up correctly.");
		}		
	}

	public void initGui()
	{
		this.buttonList.clear();
	    int posX = (this.width - 100) / 2;
	    int posY = (this.width - 20) / 2;
	    
	    GuiButton refresh = new GuiButton(0, posX, posY - 23, 100, 20, "Refresh");
	    GuiButton previous = new GuiButton(1, posX - 50, posY - 23, 50, 20, "<<"); 
	    GuiButton next = new GuiButton(2, posX + 100, posY - 23, 50, 20, ">>");
	    refresh.id = 0;
	    previous.id = 1;
	    next.id = 2;
	    
	    this.buttonList.add(refresh);
	    this.buttonList.add(previous);
	    this.buttonList.add(next);
	    
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();

        this.mc.renderEngine.func_110577_a(new ResourceLocation("youtubemod:textures/gui/twitter_gui.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 192) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, 256, 192);
        
        int x = posX + 13;
        int y = posY + 10;
        
        if(statuses.size() > 0)
        {
        	Status s = statuses.get(currentPage - 1);
        	// TODO: CHECK IF TWEET EXISTS UP TO MAXPAGe.
        	String name = "@" + s.getUser().getName();
        	String status = s.getText();
	        String dateSent = s.getCreatedAt().toString();
	        String seperator = "--------------------------------------";
	        this.drawString(fontRenderer, 
						name, 
						x, 
						y, 
						0xffffff);
			y+=10;
			
	        this.drawString(fontRenderer, 
						seperator, 
						x, 
						y, 
						0xffffff);
			
	        y+=10;
	        
	        if(status.length() > 40)
		    {
	        	
				int count = status.length() / 40;
				int mod = status.length() % 40;
				
				if(mod != 0)
			  		count++;
				
				for(int m = 0; m < count; m++)
				{
					int start = 40 * m;
					int end = 40 * (m + 1);
					
					if(end > status.length())
				    	end = status.length();
						
					this.drawString(fontRenderer, 
							status.substring(start, end), 
								x, 
								y, 
								0xffffff);
					y+=10;
				}
		    }
        	else
        	{
	        	this.drawString(fontRenderer, 
	        			status, 
	        			x, 
	        			y, 
	        			0xffffff);
	        	y += 10;
        	}
	    
	        this.drawString(fontRenderer, 
						seperator, 
						x, 
						y, 
						0xffffff);
			
	        y+=10;
        	x+=65;
    	
        	this.drawString(fontRenderer, 
        			"-" + dateSent, 
        			x, 
        			y, 
        			0xffffff);
	        
	        //for(Status s : statuses)
	        //{
	        //	String output = "@" + s.getUser().getName() + " - " + s.getText();
	        //	String dateSent = s.getCreatedAt().toString();

	        //	if(output.length() > 43)
	        //	{
	    	//		int count = output.length() / 43;
	    	//		int mod = output.length() % 43;
	    			
	    	//		if(mod != 0)
	    	//			count++;
	    			
	    	//		for(int m = 0; m < count; m++)
	    	//		{
	    	//			int start = 43 * m;
	    	//			int end = 43 * (m + 1);
	    				
	    	//			if(end > output.length())
	    	//				end = output.length();
	    				
			//        	this.drawString(fontRenderer, 
		   // 					output.substring(start, end), 
			//        			x, 
			//        			y, 
			//        			0xffffff);
	    				
	    	//			y+=10;
	    	//		}
	    			
	        //	}
	        //	else
	        //	{
		    //    	this.drawString(fontRenderer, 
		    //    			output, 
		    //    			x, 
		    //    			y, 
		    //    			0xffffff);
		    //    	y += 10;
	        //	}
	        	
	        //	x+=65;
	        	
	        //	this.drawString(fontRenderer, 
	        //			"-" + dateSent, 
	        //			x, 
	        //			y, 
	        //			0xffffff);
	        //	y+=10;
	        //	x-=65;
	        //}
        }
        
        super.drawScreen(i, j, f);
    }
    
    public void actionPerformed(GuiButton button)
    {
    	switch(button.id)
    	{
    	case 0:
    		// TODO Wire Up Populate
    		this.PopulateStatuses(null);
    		break;
    	case 1:
    		if(currentPage == 1){ currentPage = 15; }
    		else
    		{
    			currentPage--;
    		}
    		break;
    	case 2:
    		if(currentPage == 15){ currentPage = 1; }
    		else
    		{
    			currentPage++;
    		}
    		break;    		
    	}
    }
}
