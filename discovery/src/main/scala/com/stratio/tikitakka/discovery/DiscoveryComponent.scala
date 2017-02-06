package com.stratio.tikitakka.discovery

trait DiscoveryComponent[F[_]] {

  val uri: String

  def isUp: F[Boolean]

}
