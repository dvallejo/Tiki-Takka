package com.stratio.tikitakka.discovery.consul

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

import akka.http.scaladsl.model.HttpRequest

import com.stratio.tikitakka.common.ConfigComponent
import com.stratio.tikitakka.common.HttpRequestUtils
import com.stratio.tikitakka.discovery.DiscoveryComponent

trait ConsulComponent extends DiscoveryComponent[Future] with HttpRequestUtils {

  val uri = ConfigComponent.config.getString("consul.host")

  val versionField = "consul.api.version"
  val defaultApiVersion = "v1"

  val version =
    Try {
      ConfigComponent.config.getString(versionField)
    } getOrElse defaultApiVersion

  def isUp =
    (for {
      _ <- doRequest[String](instanceRequestBuilder("status/leader"))
      _ <- doRequest[String](instanceRequestBuilder("status/peers"))
    } yield true) recover { case _ => false}

  val instanceRequestBuilder: String => HttpRequest = createAPIRequest(uri)

  private def createAPIRequest(uri: String)(resource: String): HttpRequest =
    HttpRequest(uri = s"$uri/$version/$resource")
}
