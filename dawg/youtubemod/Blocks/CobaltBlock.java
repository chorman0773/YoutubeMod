package dawg.youtubemod.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dawg.youtubemod.Constants;
import dawg.youtubemod.Blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class CobaltBlock extends Block {

	public CobaltBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		
		this.setHardness(3.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName(Constants.BLOCKS.COBALT_BLOCK_NAME);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon("youtubemod:cobalt_block");
	}

}
