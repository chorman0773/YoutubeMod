package dawg.youtubemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class CobaltBlock extends Block {

	public CobaltBlock(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	@Override
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon("youtubemod:cobalt_block");
	}

}
