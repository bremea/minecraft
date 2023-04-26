import java.util.concurrent.ThreadLocalRandom;

public class World {
	int[][][] blockData;
	int totalBlocks = 2;

	World() {
		blockData = new int[16][16][16];
		blockData[0][0][0] = 1;
		blockData[1][0][0] = 1;
		blockData[2][0][0] = 1;
	}

	void genRand() {
		for (int x = 0; x < blockData.length; x++) {
			for (int y = 0; y < blockData[x].length; y++) {
				for (int z = 0; z < blockData[x][y].length; z++) {
					blockData[x][y][z] = ThreadLocalRandom.current().nextInt(0, totalBlocks);
				}
			}
		}
	}

	int[][][] getBlockData() {
		return blockData;
	}
}
