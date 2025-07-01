/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.util;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static java.time.ZoneOffset.UTC;

/**
 * An abstract base class for Clocks with the following default behavior:
 * <ul>
 * <li>Zone-agnostic: always returns UTC, ignoring any other zones</li>
 * <li>millis-centric: shifts responsibility of subclasses to defining {@link #millis()},
 * creating an Instant based on it (instead of vice versa as in {@link java.time.Clock})</li>
 * </ul>
 * Subclasses that want to change this behavior can either override relevant methods or
 * subclass {@link java.time.Clock} directly.
 */
public abstract class AbstractClock extends Clock {

  @Override
  public ZoneId getZone() {
    return UTC;
  }

  @Override
  public Clock withZone(ZoneId zone) {
    return this;
  }

  @Override
  public abstract long millis();

  @Override
  public Instant instant() {
    return Instant.ofEpochMilli(millis());
  }
}
