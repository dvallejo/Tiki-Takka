package com.stratio.tikitakka.discovery

import scalaz.Id.Id

trait DummyDiscoveryComponent extends DiscoveryComponent[Id] {

  val uri: String

  val upHost = "upHost"

  def isUp: Boolean =
    if (uri == upHost) true else false

}
