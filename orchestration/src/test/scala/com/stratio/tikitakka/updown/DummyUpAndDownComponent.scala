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
package com.stratio.tikitakka.updown

import com.stratio.tikitakka.common.exceptions.ResponseException

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.stratio.tikitakka.common.model.AppInfo
import com.stratio.tikitakka.common.model.BuildApp
import com.stratio.tikitakka.common.model.Container

trait DummyUpAndDownComponent extends UpAndDownComponent {

  val validBuild =
    BuildApp("validBuild", 0.2, 256, 2, Container("containerId", Seq()))

  val invalidBuild =
    BuildApp("invalidBuild", 0.2, 256, 2, Container("containerId", Seq()))

  def upApplication(application: BuildApp): Future[AppInfo] = Future {
    if (application == validBuild) AppInfo(validBuild.id)
    else throw ResponseException("Error when up", new Exception)
  }

  def downApplication(application: AppInfo): Future[AppInfo] = Future {
    if (application.id == validBuild.id) AppInfo(validBuild.id)
    else throw ResponseException("Error when up", new Exception)
  }
}
