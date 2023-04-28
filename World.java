import java.util.concurrent.ThreadLocalRandom;

public class World {
	int[][][] blockData;
	int totalBlocks = 2;

	World() {
		blockData = new int[16][16][16];
	}

	public void removeBlock(int[] pos) {
		if (pos[0] >= 0 && pos[0] < blockData.length) {
			if (pos[1] >= 0 && pos[1] < blockData[0].length) {
				if (pos[2] >= 0 && pos[2] < blockData[1].length) {
					blockData[pos[0]][pos[1]][pos[2]] = 0;
				}
			}
		}
	}

	public void addBlock(int[] pos) {
		if (pos[0] >= 0 && pos[0] < blockData.length) {
			if (pos[1] >= 0 && pos[1] < blockData[0].length) {
				if (pos[2] >= 0 && pos[2] < blockData[1].length) {
					blockData[pos[0]][pos[1] + 1][pos[2]] = 4;
				}
			}
		}
	}

	public boolean testBlock(int[] pos) {
		if (pos[0] >= 0 && pos[0] < blockData.length) {
			if (pos[1] >= 0 && pos[1] < blockData[0].length) {
				if (pos[2] >= 0 && pos[2] < blockData[1].length) {
					if (blockData[pos[0]][pos[1]][pos[2]] != 0) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	void genFlat() {
		for (int x = 0; x < blockData.length; x++) {
			for (int y = 0; y < blockData[x].length; y++) {
				for (int z = 0; z < blockData[x][y].length; z++) {
					if (y < 3)
						blockData[x][y][z] = 3;
					if (y == 3)
						blockData[x][y][z] = 2;
					if (y == 4)
						blockData[x][y][z] = 1;
				}
			}
		}
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
