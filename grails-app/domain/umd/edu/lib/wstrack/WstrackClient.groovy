package umd.edu.lib.wstrack

import grails.converters.JSON
import java.security.MessageDigest
import sun.misc.BASE64Encoder

class WstrackClient {

  String ip
  String hostName
  String os
  String userHash
  String status
  Boolean guestFlag

  static mapping = {
    table "History"
    version false
  }

  static constraints = { ip(blank:false) }

}
