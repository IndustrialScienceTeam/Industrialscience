package de.zsgn.industrialscience.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import de.zsgn.industrialscience.IndustrialScience;

public class WorldGeneratorPortalRoom implements IWorldGenerator {
    public static final int MAXHEIGHT = 45;
    public static final int MINHEIGHT = 3;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int chunkStartX = chunkX * 16;
        int chunkStartZ = chunkZ * 16;
        if (random.nextInt(24) == 0 && world.provider.dimensionId == 0) {
            int portaly = random.nextInt(MAXHEIGHT - MINHEIGHT + 1) + MINHEIGHT;
            int portalx = chunkStartX + 7;
            int portalz = chunkStartZ + 7;
            if (world.isAirBlock(portalx, portaly, portalz)) {
                return;
            }
            int x = portalx - 2;
            int y = portaly;
            int z = portalz - 2;
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int j2 = 0; j2 < 5; j2++) {
                        world.setBlock(x + j, y + i * 4, z + j2,
                                IndustrialScience.getInstance()
                                        .getBlockreinforcedbricks());
                    }
                }
            }
            x = portalx - 2;
            y = portaly + 1;
            z = portalz - 2;
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int j2 = 0; j2 < 5; j2++) {
                        world.setBlock(x + i * 4, y + j, z + j2,
                                IndustrialScience.getInstance()
                                        .getBlockreinforcedbricks());
                    }
                }
            }
            x = portalx - 1;
            y = portaly + 1;
            z = portalz - 2;
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int j2 = 0; j2 < 4; j2++) {
                        world.setBlock(x + j2, y + j, z + i * 4,
                                IndustrialScience.getInstance()
                                        .getBlockreinforcedbricks());
                    }
                }
            }
            world.setBlock(portalx, portaly, portalz, IndustrialScience
                    .getInstance().getBlockmysteriousportal());
        }

    }

}
