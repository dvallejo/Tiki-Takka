package com.stratio.tikitakka.common

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ HttpRequest, ResponseEntity }
import akka.http.scaladsl.unmarshalling.{ Unmarshal, Unmarshaller }
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging

trait HttpRequestUtils extends LazyLogging {

  implicit val system: ActorSystem
  implicit val actorMaterializer: ActorMaterializer

  lazy val httpSystem = Http(system)

  def doRequest[T](httpRequest: HttpRequest)(implicit ev: Unmarshaller[ResponseEntity,T]): Future[T] = {
    logger.info(s"Doing HTTP request to ${httpRequest.uri}")
    for {
      response <- httpSystem.singleRequest(httpRequest)
      entity <- Unmarshal(response.entity).to[T]
    } yield entity
  }

  def close: Future[Unit] = httpSystem.shutdownAllConnectionPools()
}
