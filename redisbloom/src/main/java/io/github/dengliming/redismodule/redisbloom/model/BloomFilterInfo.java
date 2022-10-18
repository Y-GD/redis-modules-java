/*
 * Copyright 2020-2022 dengliming.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.dengliming.redismodule.redisbloom.model;

/**
 * @author dengliming
 */
public class BloomFilterInfo {
    private final Integer capacity;
    private final Integer size;
    private final Integer filterNum;
    private final Integer insertedNum;
    private final Integer expansionRate;

    public BloomFilterInfo(Integer capacity, Integer size, Integer filterNum, Integer insertedNum, Integer expansionRate) {
        this.capacity = capacity;
        this.size = size;
        this.filterNum = filterNum;
        this.insertedNum = insertedNum;
        this.expansionRate = expansionRate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getFilterNum() {
        return filterNum;
    }

    public Integer getInsertedNum() {
        return insertedNum;
    }

    public Integer getExpansionRate() {
        return expansionRate;
    }
}
