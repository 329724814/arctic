/*
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

package com.netease.arctic.spark.sql.catalyst.plans

import com.netease.arctic.spark.sql.utils.WriteQueryProjections
import org.apache.spark.sql.catalyst.analysis.NamedRelation
import org.apache.spark.sql.catalyst.plans.logical.{Command, LogicalPlan, V2WriteCommand, V2WriteCommandLike, WriteDelta}
import org.apache.spark.sql.connector.write.Write

case class ArcticRowLevelWrite(
    table: NamedRelation,
    query: LogicalPlan,
    options: Map[String, String],
    projections: WriteQueryProjections,
    write: Option[Write] = None) extends V2WriteCommandLike {

  def isByName: Boolean = false

  override def outputResolved: Boolean = true

  override protected def withNewChildInternal(newChild: LogicalPlan): ArcticRowLevelWrite = {
    copy(query = newChild)
  }

}
