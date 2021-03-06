/*
 * Copyright (C) 2015 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.tikitakka.columbus.test.utils.consul

import play.api.libs.json.Json
import play.api.libs.json.Reads
import play.api.libs.json.Writes

case class NodeCatalog(
                        ID: Option[String],
                        Node: String,
                        Address: String,
                        CreateIndex: Int,
                        ModifyIndex: Int
                        )

object NodeCatalog {

  implicit val writer: Writes[NodeCatalog] = Json.writes[NodeCatalog]
  implicit val reads: Reads[NodeCatalog] = Json.reads[NodeCatalog]
}