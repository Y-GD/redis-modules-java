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

package io.github.swyang.redismodule.redismros.model;

import io.github.swyang.redismodule.redismros.protocol.KeyWords;

import java.util.List;

public class InsertArgs {
    /**
     * The number of entries you intend to add to the filter
     */
    private int capacity;


    public InsertArgs() {
        this(-1);
    }

    public InsertArgs(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public InsertArgs setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }


    public void build(List<Object> args) {
        if (getCapacity() > 0) {
            args.add(KeyWords.CAPACITY);
            args.add(getCapacity());
        }
    }
}
