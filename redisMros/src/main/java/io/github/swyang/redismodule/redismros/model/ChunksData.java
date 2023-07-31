package io.github.swyang.redismodule.redismros.model;

public class ChunksData {

    private int iter;

    private byte[] data;

    public ChunksData(int iter, byte[] data) {
        this.iter = iter;
        this.data = data;
    }

    public int getIter() {
        return iter;
    }

    public byte[] getData() {
        return data;
    }
}
