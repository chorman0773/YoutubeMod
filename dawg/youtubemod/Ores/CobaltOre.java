package dawg.youtubemod.Ores;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dawg.youtubemod.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class CobaltOre extends BlockOre{

	public CobaltOre(int par1) {
		super(par1);
		
		this.setHardness(3.0F); // Diamond Hardness
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName(Constants.BLOCKS.COBALT_ORE_BLOCK_NAME);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon("youtubemod:cobalt_ore");
	}
	
}
