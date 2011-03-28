package com.bryanjswift.simplenote.net

import com.bryanjswift.simplenote.model.Credentials
import org.apache.http.HttpStatus
import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class SimplenoteApiSpec extends WordSpec with ShouldMatchers {
  val userAgent = "SimplenoteJavaApiTest/1.0"
  val credentials = new Credentials(null, "swiftnote@bryanjswift.com", "simplenote1234")
  val badCredentials = new Credentials(null, "swiftnote@bryanjswift.com", "bad password");
  val api = new SimplenoteApi(userAgent)

  "SimplenoteApi" should {
    "return Credentials with valid auth value on successful login" in {
      val response = api.login(credentials.email, credentials.password)
      response.status should be (HttpStatus.SC_OK)
      response.payload.email should be (credentials.email)
      response.payload.auth.length should be > 0
      response.payload.password should be (null)
    }
    "return with unauthorized status on unsuccessful login" in {
      val response = api.login(badCredentials.email, badCredentials.password)
      response.status should not be (HttpStatus.SC_UNAUTHORIZED)
      response.payload.email should be (badCredentials.email)
      response.payload.password should be (null)
    }
  }
}
