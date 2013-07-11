package dawg.youtubemod;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
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
	
	// Block ID Section
	int cobaltBlockID;
	int cobaltOreBlockID;
	int cobaltIngotID;
	int twitterBlockID;
	
	// Armor ID Section
	int cobaltHelmetID;
	int cobaltChestID;
	int cobaltLeggingsID;
	int cobaltBootsID;
	
	// Block Section
	public static Block cobaltBlock;
	public static Block cobaltOre;
	public static Block twitterBlock;
	
	// Item Section
	public static Item cobaltIngot;

	// Twitter class level variables
	String consumerKey = "";
	String consumerSecret = "";
	String accessToken = "";
	String accessTokenSecret = "";
	
	// Material Section
	// Diamond has 33 Durability
	// Enchantibility - Diamond = 10
	public static EnumArmorMaterial cobaltArmor = 
			EnumHelper.addArmorMaterial("CobaltArmor", 21, new int[] { 4, 10, 4, 3 }, 5);
	
	// Armor Section
	public static Item cobaltHelmet;
	public static Item cobaltChest;
	public static Item cobaltLeggings;
	public static Item cobaltBoots;
	
	
	// Instance of our Mod
	public static YoutubeMod instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		// Configuration Code
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		Property consumerKey = config.get(Configuration.CATEGORY_GENERAL, "consumerKey", "NULL");
		Property consumerSecret = config.get(Configuration.CATEGORY_GENERAL, "consumerSecret", "NULL");
		Property accessToken = config.get(Configuration.CATEGORY_GENERAL, "accessToken", "NULL");
		Property accessTokenSecret = config.get(Configuration.CATEGORY_GENERAL, "accessTokenSecret", "NULL");
		
		cobaltBlockID = config.get("Block IDs", "Cobalt Block ID", 1000).getInt();
		cobaltOreBlockID = config.get("Block IDs", "Cobalt Ore Block ID", 1001).getInt();
		twitterBlockID = config.get("Block IDs", "Twitter Block ID", 1003).getInt();
		cobaltIngotID = config.get("Material IDs", "Cobalt Ingot ID", 1002).getInt();
	
		cobaltHelmetID = config.get("Armor IDs", "Cobalt Helmet ID", 1004).getInt();
		cobaltChestID = config.get("Armor IDs", "Cobalt Chest ID", 1005).getInt();
		cobaltLeggingsID = config.get("Armor IDs", "Cobalt Leggings ID", 1006).getInt();
		cobaltBootsID = config.get("Armor IDs", "Cobalt Boots ID", 1007).getInt();
		
		// Twitter Mod Configuration Variable Initialization
		this.consumerKey = consumerKey.getString();
		this.consumerSecret = consumerSecret.getString();
		this.accessToken = accessToken.getString();
		this.accessTokenSecret = accessTokenSecret.getString();
		
		config.save();
	
		// Initialize our Blocks
		this.cobaltBlock = new CobaltBlock(cobaltBlockID, Material.rock);
		this.cobaltOre = new CobaltOre(cobaltOreBlockID);
		this.twitterBlock = new TwitterBlock(twitterBlockID, Material.rock);
		
		// Initialize our Items
		this.cobaltIngot = new CobaltIngot(cobaltIngotID);
		
		// Initialize our Armor
		cobaltHelmet = new Armor(cobaltHelmetID, cobaltArmor, 0, 0, "Cobalt");
		cobaltChest = new Armor(cobaltChestID, cobaltArmor, 0, 1, "Cobalt");
		cobaltLeggings = new Armor(cobaltLeggingsID, cobaltArmor, 0, 2, "Cobalt");
		cobaltBoots = new Armor(cobaltBootsID, cobaltArmor, 0, 3, "Cobalt");		
		
		// Cobalt Block Stuff
		LanguageRegistry.addName(cobaltBlock, "Cobalt Block");
		MinecraftForge.setBlockHarvestLevel(cobaltBlock, "pickaxe", 2);
		GameRegistry.registerBlock(cobaltBlock, "cobaltBlock");
		
		// Cobalt Ore Stuff
		LanguageRegistry.addName(cobaltOre, "Cobalt Ore");
		MinecraftForge.setBlockHarvestLevel(cobaltOre, "pickaxe", 3);
		GameRegistry.registerBlock(cobaltOre, "cobaltOre");
		
		// Cobalt Ingot Stuff
		LanguageRegistry.addName(cobaltIngot, "Cobalt Ingot");
		
		// Cobalt Armor Stuff
		LanguageRegistry.addName(cobaltHelmet, "Cobalt Helmet");
		LanguageRegistry.addName(cobaltChest, "Cobalt Chest");
		LanguageRegistry.addName(cobaltLeggings, "Cobalt Leggings");
		LanguageRegistry.addName(cobaltBoots, "Cobalt Boots");		
		
		// Smelting Recipes
		GameRegistry.addSmelting(cobaltOreBlockID, new ItemStack(cobaltIngot), 0.7f);

		// Crafting Recipes
		GameRegistry.addRecipe(new ItemStack(cobaltBlock), 
				"xxx", "xxx", "xxx",
				'x', cobaltIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(cobaltHelmet), 
				"xxx", "x x",
				'x', cobaltIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(cobaltChest), 
				"x x", "xxx", "xxx",
				'x', cobaltIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(cobaltLeggings), 
				"xxx", "x x", "x x",
				'x', cobaltIngot
				);
		
		GameRegistry.addRecipe(new ItemStack(cobaltBoots), 
				"   ", "x x", "x x",
				'x', cobaltIngot
				);
		
		
		
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
