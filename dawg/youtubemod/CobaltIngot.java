package dawg.youtubemod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CobaltIngot extends Item{

	public CobaltIngot(int par1) {
		super(par1);

		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("cobaltIngot");		
	}
	
	@Override
	public void registerIcons(IconRegister reg)
	{
		this.itemIcon = reg.registerIcon("youtubemod:cobalt_ingot");
	}

}
