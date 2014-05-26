package de.zsgn.industrialscience.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import de.zsgn.industrialscience.IndustrialScience;

public class WorldGeneratorPortalRoom implements IWorldGenerator {
    public final static int maxheight=45;
    public final static int minheight=3;
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int chunkStartX=chunkX*16;
        int chunkStartZ=chunkZ*16;
        if(random.nextInt(4)==0&&world.provider.dimensionId==0){
            int portaly=random.nextInt(maxheight-minheight+1)+minheight;
            int portalx= chunkStartX+7;
            int portalz= chunkStartZ+7;
            int x = portalx-2;
            int y = portaly;
            int z = portalz-2;
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int j2 = 0; j2 < 5; j2++) {
                        world.setBlock(x+j, y+i*4, z+j2, IndustrialScience.getInstance().getBlockreinforcedbricks());
                    }
                }
            }
            x=portalx-2;
            y=portaly+1;
            z=portalz-2;
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int j2 = 0; j2 < 5; j2++) {
                        world.setBlock(x+i*4, y+j, z+j2, IndustrialScience.getInstance().getBlockreinforcedbricks());
                    }
                }
            }
            x=portalx-1;
            y=portaly+1;
            z=portalz-2;
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int j2 = 0; j2 < 4; j2++) {
                        world.setBlock(x+j2, y+j, z+i*4, IndustrialScience.getInstance().getBlockreinforcedbricks());
                    }
                }
            }
            world.setBlock(portalx, portaly, portalz, Blocks.furnace);
            }
        
    }

}
