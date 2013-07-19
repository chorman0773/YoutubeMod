package dawg.youtubemod;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.Status;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class TwitterBlock extends BlockContainer{

	Icon theIcon;
	
	protected TwitterBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("twitterBlock");
	}
	
	// Controls whether players can attach torches, or if its opaque vs. full 1m square cube.
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    // Event fires when player right clicks on the block.
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {    	
	    	par5EntityPlayer.openGui(YoutubeMod.instance, 0, par1World, x, y, z);

    		
    	return true;
    }
    
    public Icon getIcon(int par1, int par2)
    {
        return par1 != 1 && par1 != 0 ? this.blockIcon : this.theIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        this.blockIcon = reg.registerIcon("youtubemod:twitter_block_side");
        this.theIcon = reg.registerIcon("youtubemod:twitter_block_top");
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TwitterTileEntity();
	}

}
