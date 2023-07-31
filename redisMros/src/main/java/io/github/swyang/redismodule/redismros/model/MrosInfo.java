package io.github.swyang.redismodule.redismros.model;

public class MrosInfo {

    private final Long capacity;
    private final Integer size;
    private final Integer layerCnt;

    private final Long layer_size;

    private final Long bytes;

    public MrosInfo(Long capacity, Integer size, Integer layerCnt, Long layer_size, Long bytes) {
        this.capacity = capacity;
        this.size = size;
        this.layerCnt = layerCnt;
        this.layer_size = layer_size;
        this.bytes = bytes;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getLayerCnt() {
        return layerCnt;
    }

    public Long getLayer_size() {
        return layer_size;
    }

    public Long getBytes() {
        return bytes;
    }
}
