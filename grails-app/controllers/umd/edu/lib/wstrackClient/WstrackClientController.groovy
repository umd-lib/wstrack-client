package umd.edu.lib.wstrackClient

import grails.converters.JSON
import java.security.MessageDigest
import org.springframework.dao.DataIntegrityViolationException
import sun.misc.BASE64Encoder
import umd.edu.lib.wstrack.WstrackClient;

class WstrackClientController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def track() {
    def ret = ['params': params]

    def username = "user.name"
    def usernameProperty = System.getProperty(username)

    def nameOS = "os.name"
    def property = System.getProperty(nameOS)
    println "The OS Name is : ${property}"

    def hostName = InetAddress.getLocalHost().getHostName()
    println "The hostname is : ${hostName}"

    def SALT = 'RAGHAV'
    def saltedUsername = SALT + usernameProperty;
    def hashedUsername = generateHash(saltedUsername);

    def host = 'http://www.comitservices.com/ip.php'
    def ip = new URL(host).getText()
    println "This computer's IP address is: ${ip}"

    def guestFlag = Boolean.FALSE
    if(usernameProperty.startsWith("libguest")) {
      guestFlag = Boolean.TRUE
    }
    println "GuestFlag = $guestFlag"

    /*
     * @Javadoc- Rendering of the URL
     */

    render (text: 'http://'+hostName+':8888/wstrack-client/wstrackClient/track/'+ip+'/'+hashedUsername+'/'+property)

    /*
     *  Another way to publish the URL where the web page redirects to this link. If that is the case,
     *   how do I create custom GSPs at this location?
     */

    //redirect(url : "http://"+hostName+":8888/wstrack-client/wstrackClient/track/"+ip+"/"+hashedUsername+"/"+property)
  }
  
  def publishURL() {
    
    def username = "user.name"
    def usernameProperty = System.getProperty(username)

    def nameOS = "os.name"
    def property = System.getProperty(nameOS)
    println "The OS Name is : ${property}"

    def hostName = InetAddress.getLocalHost().getHostName()
    println "The hostname is : ${hostName}"

    def SALT = 'RAGHAV'
    def saltedUsername = SALT + usernameProperty;
    def hashedUsername = generateHash(saltedUsername);

    def host = 'http://www.comitservices.com/ip.php'
    def ip = new URL(host).getText()
    println "This computer's IP address is: ${ip}"

    def guestFlag = Boolean.FALSE
    if(usernameProperty.startsWith("libguest")) {
      guestFlag = Boolean.TRUE
    }
    
    redirect(url : "http://"+hostName+":8888/wstrack-client/wstrackClient/track/"+ip+"/"+hashedUsername+"/"+property)
  }

  def index() {

    def ret = ['params': params]
  }

  def list() {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    [wstrackClientInstanceList: WstrackClient.list(params), wstrackClientInstanceTotal: WstrackClient.count()]
  }

  def create() {
    [wstrackClientInstance: new WstrackClient(params)]
  }

  def save() {
    def wstrackClientInstance = new WstrackClient(params)
    if (!wstrackClientInstance.save(flush: true)) {
      render(view: "create", model: [wstrackClientInstance: wstrackClientInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [
      message(code: 'wstrackClient.label', default: 'WstrackClient'),
      wstrackClientInstance.id
    ])
    redirect(action: "show", id: wstrackClientInstance.id)
  }

  def show() {
    def wstrackClientInstance = WstrackClient.get(params.id)
    if (!wstrackClientInstance) {
      flash.message = message(code: 'default.not.found.message', args: [
        message(code: 'wstrackClient.label', default: 'WstrackClient'),
        params.id
      ])
      redirect(action: "list")
      return
    }

    [wstrackClientInstance: wstrackClientInstance]
  }

  def edit() {
    def wstrackClientInstance = WstrackClient.get(params.id)
    if (!wstrackClientInstance) {
      flash.message = message(code: 'default.not.found.message', args: [
        message(code: 'wstrackClient.label', default: 'WstrackClient'),
        params.id
      ])
      redirect(action: "list")
      return
    }

    [wstrackClientInstance: wstrackClientInstance]
  }

  def update() {
    def wstrackClientInstance = WstrackClient.get(params.id)
    if (!wstrackClientInstance) {
      flash.message = message(code: 'default.not.found.message', args: [
        message(code: 'wstrackClient.label', default: 'WstrackClient'),
        params.id
      ])
      redirect(action: "list")
      return
    }

    if (params.version) {
      def version = params.version.toLong()
      if (wstrackClientInstance.version > version) {
        wstrackClientInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [
              message(code: 'wstrackClient.label', default: 'WstrackClient')]
            as Object[],
            "Another user has updated this WstrackClient while you were editing")
        render(view: "edit", model: [wstrackClientInstance: wstrackClientInstance])
        return
      }
    }

    wstrackClientInstance.properties = params

    if (!wstrackClientInstance.save(flush: true)) {
      render(view: "edit", model: [wstrackClientInstance: wstrackClientInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [
      message(code: 'wstrackClient.label', default: 'WstrackClient'),
      wstrackClientInstance.id
    ])
    redirect(action: "show", id: wstrackClientInstance.id)
  }

  def delete() {
    def wstrackClientInstance = WstrackClient.get(params.id)
    if (!wstrackClientInstance) {
      flash.message = message(code: 'default.not.found.message', args: [
        message(code: 'wstrackClient.label', default: 'WstrackClient'),
        params.id
      ])
      redirect(action: "list")
      return
    }

    try {
      wstrackClientInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [
        message(code: 'wstrackClient.label', default: 'WstrackClient'),
        params.id
      ])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [
        message(code: 'wstrackClient.label', default: 'WstrackClient'),
        params.id
      ])
      redirect(action: "show", id: params.id)
    }
  }

  public static String generateHash(String input) {
    def hash = '';
    try {
      MessageDigest sha = MessageDigest.getInstance('SHA-1');
      byte[] hashedBytes = sha.digest(input.getBytes());
      hash= (new BASE64Encoder()).encode(hashedBytes );
    }catch (Exception e) {
      // handle error here
      println e
    }
    return hash;
  }
}