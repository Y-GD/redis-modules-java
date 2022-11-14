/*
 * Copyright 2020 dengliming.
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
public class CountMinSketchInfo {
    private final Integer width;
    private final Integer depth;
    private final Integer count;

    public CountMinSketchInfo(Integer width, Integer depth, Integer count) {
        this.width = width;
        this.depth = depth;
        this.count = count;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getDepth() {
        return depth;
    }

    public Integer getCount() {
        return count;
    }
}
