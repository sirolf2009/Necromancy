package com.sirolf2009.necromancy.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherChalice extends WorldGenerator {
    public WorldGenNetherChalice() {
    }

    @Override
    public boolean generate(World world, Random rand, int i, int j, int k) {
        if (world.getBlockId(i, j, k) != Block.lavaStill.blockID || world.getBlockId(i, j + 1, k) != 0)
            return false;
        /*
         * world.setBlockAndMetadataWithNotify(i + 0, j + 19, k + 1,
         * Block.netherFence.blockID, 0, 0); world.setBlockAndMetadata(i + 0, j
         * + 19, k + 2, Block.stairsNetherBrick.blockID, 4);
         * world.setBlockAndMetadata(i + 0, j + 19, k + 3,
         * Block.stairsNetherBrick.blockID, 4); world.setBlockAndMetadata(i + 0,
         * j + 19, k + 4, Block.stairsNetherBrick.blockID, 4); world.setBlock(i
         * + 0, j + 19, k + 5, Block.netherFence.blockID); world.setBlock(i + 0,
         * j + 20, k + 1, Block.netherFence.blockID); world.setBlock(i + 0, j +
         * 20, k + 2, Block.netherBrick.blockID); world.setBlock(i + 0, j + 20,
         * k + 3, Block.netherBrick.blockID); world.setBlock(i + 0, j + 20, k +
         * 4, Block.netherBrick.blockID); world.setBlock(i + 0, j + 20, k + 5,
         * Block.netherFence.blockID); world.setBlock(i + 0, j + 21, k + 1,
         * Block.netherFence.blockID); world.setBlock(i + 0, j + 21, k + 2,
         * Block.netherFence.blockID); world.setBlock(i + 0, j + 21, k + 3,
         * Block.netherFence.blockID); world.setBlock(i + 0, j + 21, k + 4,
         * Block.netherFence.blockID); world.setBlock(i + 0, j + 21, k + 5,
         * Block.netherFence.blockID); world.setBlock(i + 1, j + 18, k + 1,
         * Block.netherFence.blockID); world.setBlockAndMetadata(i + 1, j + 18,
         * k + 2, Block.stairsNetherBrick.blockID, 4);
         * world.setBlockAndMetadata(i + 1, j + 18, k + 3,
         * Block.stairsNetherBrick.blockID, 4); world.setBlockAndMetadata(i + 1,
         * j + 18, k + 4, Block.stairsNetherBrick.blockID, 4); world.setBlock(i
         * + 1, j + 18, k + 5, Block.netherFence.blockID); world.setBlock(i + 1,
         * j + 19, k + 0, Block.netherFence.blockID); world.setBlock(i + 1, j +
         * 19, k + 1, Block.netherBrick.blockID); world.setBlock(i + 1, j + 19,
         * k + 2, Block.netherBrick.blockID); world.setBlock(i + 1, j + 19, k +
         * 3, Block.netherBrick.blockID); world.setBlock(i + 1, j + 19, k + 4,
         * Block.netherBrick.blockID); world.setBlock(i + 1, j + 19, k + 5,
         * Block.netherBrick.blockID); world.setBlock(i + 1, j + 19, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 1, j + 20, k + 0,
         * Block.netherFence.blockID); world.setBlock(i + 1, j + 20, k + 1,
         * Block.netherBrick.blockID); world.setBlock(i + 1, j + 20, k + 5,
         * Block.netherBrick.blockID); world.setBlock(i + 1, j + 20, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 1, j + 21, k + 0,
         * Block.netherFence.blockID); world.setBlock(i + 1, j + 21, k + 1,
         * Block.netherFence.blockID); world.setBlock(i + 1, j + 21, k + 5,
         * Block.netherFence.blockID); world.setBlock(i + 1, j + 21, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 2, j + 0, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 0, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 0, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 1, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 1, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 1, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 2, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 2, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 2, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 3, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 3, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 3, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 4, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 4, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 4, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 5, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 5, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 5, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 6, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 6, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 6, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 7, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 7, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 7, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 8, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 8, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 8, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 9, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 9, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 9, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 10, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 10, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 10, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 11, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 11, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 11, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 12, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 12, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 12, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 13, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 13, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 13, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 14, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 14, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 14, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 15, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 15, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 15, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 16, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 16, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 16, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 17, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 17, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 2, j + 17, k + 4,
         * Block.fenceIron.blockID); world.setBlockAndMetadata(i + 2, j + 18, k
         * + 1, Block.stairsNetherBrick.blockID, 6); world.setBlock(i + 2, j +
         * 18, k + 2, Block.netherBrick.blockID); world.setBlock(i + 2, j + 18,
         * k + 3, Block.netherBrick.blockID); world.setBlock(i + 2, j + 18, k +
         * 4, Block.netherBrick.blockID); world.setBlockAndMetadata(i + 2, j +
         * 18, k + 5, Block.stairsNetherBrick.blockID, 7);
         * world.setBlockAndMetadata(i + 2, j + 19, k + 0,
         * Block.stairsNetherBrick.blockID, 6); world.setBlock(i + 2, j + 19, k
         * + 1, Block.netherBrick.blockID); world.setBlock(i + 2, j + 19, k + 5,
         * Block.netherBrick.blockID); world.setBlockAndMetadata(i + 2, j + 19,
         * k + 6, Block.stairsNetherBrick.blockID, 7); world.setBlock(i + 2, j +
         * 20, k + 0, Block.netherBrick.blockID); world.setBlock(i + 2, j + 20,
         * k + 6, Block.netherBrick.blockID); world.setBlock(i + 2, j + 21, k +
         * 0, Block.netherFence.blockID); world.setBlock(i + 2, j + 21, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 3, j + 0, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 0, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 1, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 1, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 2, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 2, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 3, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 3, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 4, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 4, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 5, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 5, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 6, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 6, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 7, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 7, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 8, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 8, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 9, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 9, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 10, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 10, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 11, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 11, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 12, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 12, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 13, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 13, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 14, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 14, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 15, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 15, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 16, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 16, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 17, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 3, j + 17, k + 4,
         * Block.fenceIron.blockID); world.setBlockAndMetadata(i + 3, j + 18, k
         * + 1, Block.stairsNetherBrick.blockID, 6); world.setBlock(i + 3, j +
         * 18, k + 2, Block.netherBrick.blockID); world.setBlock(i + 3, j + 18,
         * k + 4, Block.netherBrick.blockID); world.setBlockAndMetadata(i + 3, j
         * + 18, k + 5, Block.stairsNetherBrick.blockID, 7);
         * world.setBlockAndMetadata(i + 3, j + 19, k + 0,
         * Block.stairsNetherBrick.blockID, 6); world.setBlock(i + 3, j + 19, k
         * + 1, Block.netherBrick.blockID); world.setBlock(i + 3, j + 19, k + 5,
         * Block.netherBrick.blockID); world.setBlockAndMetadata(i + 3, j + 19,
         * k + 6, Block.stairsNetherBrick.blockID, 7); world.setBlock(i + 3, j +
         * 20, k + 0, Block.netherBrick.blockID); world.setBlock(i + 3, j + 20,
         * k + 6, Block.netherBrick.blockID); world.setBlock(i + 3, j + 21, k +
         * 0, Block.netherFence.blockID); world.setBlock(i + 3, j + 21, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 4, j + 0, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 0, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 0, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 1, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 1, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 1, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 2, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 2, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 2, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 3, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 3, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 3, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 4, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 4, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 4, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 5, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 5, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 5, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 6, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 6, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 6, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 7, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 7, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 7, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 8, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 8, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 8, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 9, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 9, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 9, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 10, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 10, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 10, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 11, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 11, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 11, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 12, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 12, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 12, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 13, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 13, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 13, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 14, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 14, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 14, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 15, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 15, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 15, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 16, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 16, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 16, k + 4,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 17, k + 2,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 17, k + 3,
         * Block.fenceIron.blockID); world.setBlock(i + 4, j + 17, k + 4,
         * Block.fenceIron.blockID); world.setBlockAndMetadata(i + 4, j + 18, k
         * + 1, Block.stairsNetherBrick.blockID, 6); world.setBlock(i + 4, j +
         * 18, k + 2, Block.netherBrick.blockID); world.setBlock(i + 4, j + 18,
         * k + 3, Block.netherBrick.blockID); world.setBlock(i + 4, j + 18, k +
         * 4, Block.netherBrick.blockID); world.setBlockAndMetadata(i + 4, j +
         * 18, k + 5, Block.stairsNetherBrick.blockID, 7);
         * world.setBlockAndMetadata(i + 4, j + 19, k + 0,
         * Block.stairsNetherBrick.blockID, 6); world.setBlock(i + 4, j + 19, k
         * + 1, Block.netherBrick.blockID); world.setBlock(i + 4, j + 19, k + 5,
         * Block.netherBrick.blockID); world.setBlockAndMetadata(i + 4, j + 19,
         * k + 6, Block.stairsNetherBrick.blockID, 7); world.setBlock(i + 4, j +
         * 20, k + 0, Block.netherBrick.blockID); world.setBlock(i + 4, j + 20,
         * k + 6, Block.netherBrick.blockID); world.setBlock(i + 4, j + 21, k +
         * 0, Block.netherFence.blockID); world.setBlock(i + 4, j + 21, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 5, j + 18, k + 1,
         * Block.netherFence.blockID); world.setBlockAndMetadata(i + 5, j + 18,
         * k + 2, Block.stairsNetherBrick.blockID, 5);
         * world.setBlockAndMetadata(i + 5, j + 18, k + 3,
         * Block.stairsNetherBrick.blockID, 5); world.setBlockAndMetadata(i + 5,
         * j + 18, k + 4, Block.stairsNetherBrick.blockID, 5); world.setBlock(i
         * + 5, j + 18, k + 5, Block.netherFence.blockID); world.setBlock(i + 5,
         * j + 19, k + 0, Block.netherFence.blockID); world.setBlock(i + 5, j +
         * 19, k + 1, Block.netherBrick.blockID); world.setBlock(i + 5, j + 19,
         * k + 2, Block.netherBrick.blockID); world.setBlock(i + 5, j + 19, k +
         * 3, Block.netherBrick.blockID); world.setBlock(i + 5, j + 19, k + 4,
         * Block.netherBrick.blockID); world.setBlock(i + 5, j + 19, k + 5,
         * Block.netherBrick.blockID); world.setBlock(i + 5, j + 19, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 5, j + 20, k + 0,
         * Block.netherFence.blockID); world.setBlock(i + 5, j + 20, k + 1,
         * Block.netherBrick.blockID); world.setBlock(i + 5, j + 20, k + 5,
         * Block.netherBrick.blockID); world.setBlock(i + 5, j + 20, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 5, j + 21, k + 0,
         * Block.netherFence.blockID); world.setBlock(i + 5, j + 21, k + 1,
         * Block.netherFence.blockID); world.setBlock(i + 5, j + 21, k + 5,
         * Block.netherFence.blockID); world.setBlock(i + 5, j + 21, k + 6,
         * Block.netherFence.blockID); world.setBlock(i + 6, j + 19, k + 1,
         * Block.netherFence.blockID); world.setBlockAndMetadata(i + 6, j + 19,
         * k + 2, Block.stairsNetherBrick.blockID, 5);
         * world.setBlockAndMetadata(i + 6, j + 19, k + 3,
         * Block.stairsNetherBrick.blockID, 5); world.setBlockAndMetadata(i + 6,
         * j + 19, k + 4, Block.stairsNetherBrick.blockID, 5); world.setBlock(i
         * + 6, j + 19, k + 5, Block.netherFence.blockID); world.setBlock(i + 6,
         * j + 20, k + 1, Block.netherFence.blockID); world.setBlock(i + 6, j +
         * 20, k + 2, Block.netherBrick.blockID); world.setBlock(i + 6, j + 20,
         * k + 3, Block.netherBrick.blockID); world.setBlock(i + 6, j + 20, k +
         * 4, Block.netherBrick.blockID); world.setBlock(i + 6, j + 20, k + 5,
         * Block.netherFence.blockID); world.setBlock(i + 6, j + 21, k + 1,
         * Block.netherFence.blockID); world.setBlock(i + 6, j + 21, k + 2,
         * Block.netherFence.blockID); world.setBlock(i + 6, j + 21, k + 3,
         * Block.netherFence.blockID); world.setBlock(i + 6, j + 21, k + 4,
         * Block.netherFence.blockID); world.setBlock(i + 6, j + 21, k + 5,
         * Block.netherFence.blockID); world.setBlockAndMetadataWithNotify(i +
         * 0, j + 22, k + 1, Block.torchRedstoneActive.blockID, 5);
         * world.setBlockAndMetadataWithNotify(i + 0, j + 22, k + 5,
         * Block.torchRedstoneActive.blockID, 5); world.setBlockWithNotify(i +
         * 1, j + 20, k + 2, Necromancy.BloodFlowing.blockID);
         * world.setBlockWithNotify(i + 1, j + 20, k + 3,
         * Necromancy.BloodFlowing.blockID); world.setBlockWithNotify(i + 1, j +
         * 20, k + 4, Necromancy.BloodFlowing.blockID);
         * world.setBlockAndMetadataWithNotify(i + 1, j + 22, k + 0,
         * Block.torchRedstoneActive.blockID, 5);
         * world.setBlockAndMetadataWithNotify(i + 1, j + 22, k + 6,
         * Block.torchRedstoneActive.blockID, 5);
         * world.setBlockAndMetadataWithNotify(i + 2, j + 19, k + 2,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 2, j + 19, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 2, j + 19, k + 4,
         * Necromancy.BloodFlowing.blockID, 9); world.setBlockWithNotify(i + 2,
         * j + 20, k + 1, Necromancy.BloodFlowing.blockID);
         * world.setBlockAndMetadataWithNotify(i + 2, j + 20, k + 2,
         * Necromancy.BloodFlowing.blockID, 1);
         * world.setBlockAndMetadataWithNotify(i + 2, j + 20, k + 3,
         * Necromancy.BloodFlowing.blockID, 1);
         * world.setBlockAndMetadataWithNotify(i + 2, j + 20, k + 4,
         * Necromancy.BloodFlowing.blockID, 1); world.setBlockWithNotify(i + 2,
         * j + 20, k + 5, Necromancy.BloodFlowing.blockID);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 0, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 1, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 2, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 3, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 4, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 5, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 6, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 7, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 8, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 9, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 10, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 11, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 12, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 13, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 14, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 15, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 16, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 17, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 18, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 19, k + 2,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 19, k + 3,
         * Necromancy.BloodFlowing.blockID, 1);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 19, k + 4,
         * Necromancy.BloodFlowing.blockID, 9); world.setBlockWithNotify(i + 3,
         * j + 20, k + 1, Necromancy.BloodFlowing.blockID);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 20, k + 2,
         * Necromancy.BloodFlowing.blockID, 1);
         * world.setBlockAndMetadataWithNotify(i + 3, j + 20, k + 4,
         * Necromancy.BloodFlowing.blockID, 1); world.setBlockWithNotify(i + 3,
         * j + 20, k + 5, Necromancy.BloodFlowing.blockID);
         * world.setBlockAndMetadataWithNotify(i + 4, j + 19, k + 2,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 4, j + 19, k + 3,
         * Necromancy.BloodFlowing.blockID, 9);
         * world.setBlockAndMetadataWithNotify(i + 4, j + 19, k + 4,
         * Necromancy.BloodFlowing.blockID, 9); world.setBlockWithNotify(i + 4,
         * j + 20, k + 1, Necromancy.BloodFlowing.blockID);
         * world.setBlockAndMetadataWithNotify(i + 4, j + 20, k + 2,
         * Necromancy.BloodFlowing.blockID, 1);
         * world.setBlockAndMetadataWithNotify(i + 4, j + 20, k + 3,
         * Necromancy.BloodFlowing.blockID, 1);
         * world.setBlockAndMetadataWithNotify(i + 4, j + 20, k + 4,
         * Necromancy.BloodFlowing.blockID, 1); world.setBlockWithNotify(i + 4,
         * j + 20, k + 5, Necromancy.BloodFlowing.blockID);
         * world.setBlockWithNotify(i + 5, j + 20, k + 2,
         * Necromancy.BloodFlowing.blockID); world.setBlockWithNotify(i + 5, j +
         * 20, k + 3, Necromancy.BloodFlowing.blockID);
         * world.setBlockWithNotify(i + 5, j + 20, k + 4,
         * Necromancy.BloodFlowing.blockID);
         * world.setBlockAndMetadataWithNotify(i + 5, j + 22, k + 0,
         * Block.torchRedstoneActive.blockID, 5);
         * world.setBlockAndMetadataWithNotify(i + 5, j + 22, k + 6,
         * Block.torchRedstoneActive.blockID, 5);
         * world.setBlockAndMetadataWithNotify(i + 6, j + 22, k + 1,
         * Block.torchRedstoneActive.blockID, 5);
         * world.setBlockAndMetadataWithNotify(i + 6, j + 22, k + 5,
         * Block.torchRedstoneActive.blockID, 5);
         */

        return true;
    }
}