package com.stratio.tikitakka.discovery

import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class DiscoveryComponentUnitTest extends WordSpec with ShouldMatchers {

  "DiscoveryComponent" should {

    "know if the discovery service is up" in new DummyDiscoveryComponent {

      val uri = upHost

      isUp should be(true)

    }

    "know if the discovery service is down" in new DummyDiscoveryComponent {

      val uri = "fakeHost"

      isUp should be(false)

    }
  }

}
