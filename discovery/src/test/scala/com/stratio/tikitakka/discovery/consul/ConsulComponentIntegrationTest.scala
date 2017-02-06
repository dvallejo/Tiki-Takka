package com.stratio.tikitakka.discovery.consul

import scala.concurrent.Await
import scala.concurrent.duration._

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.ActorMaterializerSettings
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class ConsulComponentIntegrationTest extends WordSpec with ShouldMatchers {

  trait ActorTestSystem {

    implicit val system = ActorSystem("Actor-Test-System")
    implicit val actorMaterializer = ActorMaterializer(ActorMaterializerSettings(system))
    implicit val timeout = 5 seconds
  }

  "ConsulComponent" should {

    "know if the discovery service is up" in new ConsulComponent with ActorTestSystem {

      Await.result(isUp, timeout) should be(right = true)

    }

    "know if the discovery service is down" in new ConsulComponent with ActorTestSystem {

      override val uri = "fakeHost"

      Await.result(isUp, timeout) should be(right = false)

    }
  }

}
