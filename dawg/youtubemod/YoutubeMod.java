package dawg.youtubemod;

import java.io.File;
import java.util.EnumSet;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.settings.KeyBinding;
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
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLLoadEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import dawg.youtubemod.Blocks.CobaltBlock;
import dawg.youtubemod.Items.Armor;
import dawg.youtubemod.Items.CobaltIngot;
import dawg.youtubemod.Ores.CobaltOre;

@Mod(modid=Constants.MOD.ID, 
	name=Constants.MOD.NAME, 
	version=Constants.MOD.VERSION)
@NetworkMod(clientSideRequired=true)
public class YoutubeMod {
	
	// Block ID Section
	int cobaltBlockID;
	int cobaltOreBlockID;
	int cobaltIngotID;
	
	// Armor ID Section
	int cobaltHelmetID;
	int cobaltChestID;
	int cobaltLeggingsID;
	int cobaltBootsID;
	
	// Block Section
	public static Block cobaltBlock;
	public static Block cobaltOre;
	
	// Item Section
	public static Item cobaltIngot;

	// Armor Section
	public static Item cobaltHelmet;
	public static Item cobaltChest;
	public static Item cobaltLeggings;
	public static Item cobaltBoots;
	
	// Material Section
	// Diamond has 33 Durability
	// Enchantibility - Diamond = 10
	public static EnumArmorMaterial cobaltArmor = 
			EnumHelper.addArmorMaterial(Constants.ARMOR.COBALT_MATERIAL, 
					21, 
					new int[] { 4, 10, 4, 3 }, 
					5);

	
	// Instance of our Mod
	@Instance(Constants.MOD.ID)
	public static YoutubeMod instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		this.SetupConfiguration(event.getSuggestedConfigurationFile());
		this.InitializeAssets();
		this.SetupLanguageRegistry();
		this.MinecraftForgeSetup();
		this.RegisterBlocks();
		this.RegisterRecipes();		
	}
	
	@EventHandler
	public void load(FMLLoadEvent event){

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}	
	
	private void SetupConfiguration(File configFile)
	{
		// Configuration Code
		Configuration config = new Configuration(configFile);
		
		config.load();		
	
		cobaltBlockID = config.get("Block IDs", "Cobalt Block ID", 1000).getInt();
		cobaltOreBlockID = config.get("Block IDs", "Cobalt Ore Block ID", 1001).getInt();
		cobaltIngotID = config.get("Item IDs", "Cobalt Ingot ID", 1002).getInt();
	
		cobaltHelmetID = config.get("Armor IDs", "Cobalt Helmet ID", 1004).getInt();
		cobaltChestID = config.get("Armor IDs", "Cobalt Chest ID", 1005).getInt();
		cobaltLeggingsID = config.get("Armor IDs", "Cobalt Leggings ID", 1006).getInt();
		cobaltBootsID = config.get("Armor IDs", "Cobalt Boots ID", 1007).getInt();
		
		config.save();
	}
	
	private void InitializeAssets()
	{
		// Initialize our Blocks
		this.cobaltBlock = new CobaltBlock(cobaltBlockID, Material.rock);
		this.cobaltOre = new CobaltOre(cobaltOreBlockID);
		
		// Initialize our Items
		this.cobaltIngot = new CobaltIngot(cobaltIngotID);
		
		// Initialize our Armor
		cobaltHelmet = new Armor(cobaltHelmetID, cobaltArmor, 0, 0, Constants.ARMOR.COBALT_TYPE);
		cobaltChest = new Armor(cobaltChestID, cobaltArmor, 0, 1, Constants.ARMOR.COBALT_TYPE);
		cobaltLeggings = new Armor(cobaltLeggingsID, cobaltArmor, 0, 2, Constants.ARMOR.COBALT_TYPE);
		cobaltBoots = new Armor(cobaltBootsID, cobaltArmor, 0, 3, Constants.ARMOR.COBALT_TYPE);
	}
	
	private void SetupLanguageRegistry()
	{
		LanguageRegistry.addName(cobaltBlock, Constants.BLOCKS.COBALT_BLOCK);
		LanguageRegistry.addName(cobaltOre, Constants.BLOCKS.COBALT_ORE_BLOCK);
		LanguageRegistry.addName(cobaltIngot, Constants.ITEMS.COBALT_INGOT);
		LanguageRegistry.addName(cobaltHelmet, Constants.ARMOR.COBALT_HELMET);
		LanguageRegistry.addName(cobaltChest, Constants.ARMOR.COBALT_CHEST);
		LanguageRegistry.addName(cobaltLeggings, Constants.ARMOR.COBALT_LEGGINGS);
		LanguageRegistry.addName(cobaltBoots, Constants.ARMOR.COBALT_BOOTS);		
	}
	
	private void MinecraftForgeSetup()
	{
		MinecraftForge.setBlockHarvestLevel(cobaltBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(cobaltOre, "pickaxe", 3);
	}
	
	private void RegisterBlocks()
	{
		GameRegistry.registerBlock(cobaltBlock, Constants.BLOCKS.COBALT_BLOCK_NAME);
   	    GameRegistry.registerBlock(cobaltOre, Constants.BLOCKS.COBALT_ORE_BLOCK_NAME);
	}
	
	private void RegisterRecipes()
	{
		GameRegistry.addSmelting(cobaltOreBlockID, new ItemStack(cobaltIngot), 0.7f);
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
}
