package dawg.youtubemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLLoadEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="youtubemod", name="YoutubeMod", version="0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class YoutubeMod {
	
	// Block Section
	public final static Block cobaltBlock = 
			new CobaltBlock(501, Material.ground)
	.setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep)
	.setUnlocalizedName("cobaltBlock")
	.setCreativeTab(CreativeTabs.tabBlock);
	
	// Instance of our Mod
	public static YoutubeMod instance;
	
	// Twitter class level variables
	String consumerKey = "";
	String consumerSecret = "";
	String accessToken = "";
	String accessTokenSecret = "";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		// Configuration Code
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		Property consumerKey = config.get(Configuration.CATEGORY_GENERAL, "consumerKey", "NULL");
		Property consumerSecret = config.get(Configuration.CATEGORY_GENERAL, "consumerSecret", "NULL");
		Property accessToken = config.get(Configuration.CATEGORY_GENERAL, "accessToken", "NULL");
		Property accessTokenSecret = config.get(Configuration.CATEGORY_GENERAL, "accessTokenSecret", "NULL");
				
		config.save();
		
		// Twitter Mod Configuration Variable Initialization
		if(consumerKey.getString() != "NULL")
			this.consumerKey = consumerKey.getString();
		if(consumerSecret.getString() != "NULL")
			this.consumerSecret = consumerSecret.getString();
		if(accessToken.getString() != "NULL")
			this.accessToken = accessToken.getString();
		if(accessTokenSecret.getString() != "NULL")
			this.accessTokenSecret = accessTokenSecret.getString();
		
		// Cobalt Block Stuff
		LanguageRegistry.addName(cobaltBlock, "Cobalt Block");
		MinecraftForge.setBlockHarvestLevel(cobaltBlock, "pickaxe", 2);
		GameRegistry.registerBlock(cobaltBlock, "cobaltBlock");
		
	}
	
	@EventHandler
	public void load(FMLLoadEvent event){
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
	
	@EventHandler
	public void ServerStart(FMLServerStartingEvent event){
	
		ICommandManager manager = MinecraftServer.getServer().getCommandManager();
		ServerCommandManager serverManager = ((ServerCommandManager)manager);
		
		serverManager.registerCommand(
				new TweetCommand(consumerKey, consumerSecret, accessToken, accessTokenSecret));
		
	}
	
}
