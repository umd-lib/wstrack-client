package edu.umd.lib.wstrack.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.varia.NullAppender;

import sun.misc.BASE64Encoder;

public class ClientTracking {

  public static final Logger log = Logger.getLogger(ClientTracking.class);

  /**
   * @param args
   */

  public static String generateHash(String input) {
    String hash = "";
    try {
      MessageDigest sha = MessageDigest.getInstance("MD5");
      byte[] hashedBytes = sha.digest(input.getBytes());
      hash = (new BASE64Encoder().encode(hashedBytes));
    } catch (Exception e) {
      log.debug("The exception is " + e);
    }
    return hash;
  }

  /*
   * @Javadoc - Main method to retrieve the workstation tracking details.
   */
  public static void main(String[] args) throws MalformedURLException,
      IOException {

    // configure logging
    boolean debug = System.getProperty("wstrack.debug", "false").equals("true");

    if (debug) {
      // debug logging to the console
      Appender console = new ConsoleAppender(new PatternLayout(
          "%d [%-5p]: (%c)%n%m%n%n"));
      Logger.getRootLogger().addAppender(console);
      Logger.getRootLogger().setLevel(Level.DEBUG);
    } else {
      Logger.getRootLogger().addAppender(new NullAppender());
    }

    // map environment to baseUrl
    String env = System.getProperty("wstrack.env", "local");
    String baseUrl = null;

    if (env.equals("prod")) {
      baseUrl = "http://www.lib.umd.edu/wstrack/track";

    } else if (env.equals("stage")) {
      baseUrl = "http://wwwstage.lib.umd.edu/wstrack/track";

    } else if (env.equals("dev")) {
      baseUrl = "http://wwwdev.lib.umd.edu/wstrack/track";

    } else {
      baseUrl = "http://localhost:8080/wstrack-server/track";
    }
    log.debug("base url: " + baseUrl);

    String username = "user.name";
    String usernameProperty = System.getProperty(username);
    log.debug("username: " + usernameProperty);

    String nameOS = "os.name";
    String property = System.getProperty(nameOS);
    log.debug("The OS Name is :" + property);

    String hostName = InetAddress.getLocalHost().getHostName();
    log.debug("The hostname is :" + hostName);

    String hostAddress = InetAddress.getLocalHost().getHostAddress();
    log.debug("The host address is :" + hostAddress);

    String hashedUsername = generateHash(usernameProperty);

    boolean guestFlag = false;
    if (usernameProperty.startsWith("libguest")) {
      guestFlag = true;

    }
    /*
     * @Javadoc- Code to submit URL
     */
    try {

      // build tracking url
      StringBuffer sb = new StringBuffer(baseUrl);
      sb.append("/" + URLEncoder.encode("1.1.1.1"));
      sb.append("/" + "login");
      sb.append("/" + URLEncoder.encode(hostAddress, "UTF-8"));
      sb.append("/" + URLEncoder.encode(property));
      sb.append("/" + guestFlag);
      sb.append("/" + URLEncoder.encode(hashedUsername));

      // open the connection
      URL url = new URL(sb.toString());
      URLConnection conn = url.openConnection();

      // Get the response
      BufferedReader rd = new BufferedReader(new InputStreamReader(
          conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
        log.debug("response: " + line);
      }
      // wr.close();
      rd.close();
    } catch (Exception e) {
      log.error("Error in transmission:", e);
    }

  }

}
