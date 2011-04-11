package com.bryanjswift.simplenote.net

import com.bryanjswift.simplenote.model.Credentials
import org.apache.http.HttpStatus
import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class SimplenoteApiSpec extends WordSpec with ShouldMatchers {
  val userAgent = "SimplenoteJavaApiTest/1.0"
  val credentials = new Credentials(null, "swiftnote@bryanjswift.com", "simplenote1234")
  val badCredentials = new Credentials(null, "swiftnote@bryanjswift.com", "bad password");
  val unauthorized = new SimplenoteApi(userAgent)

  "SimplenoteApi" should {
    "return Credentials with valid auth value on successful login" in {
      val response = unauthorized.login(credentials.email, credentials.password)
      response.status should be (HttpStatus.SC_OK)
      response.payload.email should be (credentials.email)
      response.payload.auth.length should be > 0
      response.payload.password should be (null)
    }
    "return with unauthorized status on unsuccessful login" in {
      val response = unauthorized.login(badCredentials.email, badCredentials.password)
      response.status should not be (HttpStatus.SC_UNAUTHORIZED)
      response.payload.email should be (badCredentials.email)
      response.payload.password should be (null)
    }
  }

  "Properly credentialed SimplenoteApi" should {
    val loginResponse = unauthorized.login(credentials.email, credentials.password)
    val authorized = unauthorized.using(loginResponse.payload)
    
    "be able to list the index" in {
      val response = authorized.index
      response.status should be (HttpStatus.SC_OK)
      response.payload.count should be > (0)
      response.payload.notes.size should be (response.payload.count)
    }

    "be able to retrieve a fully populated note" in {
      val indexResponse = authorized.index
      indexResponse.status should be (HttpStatus.SC_OK)
      indexResponse.payload.count should be > (0)
      val indexNote = indexResponse.payload.notes.get(0)
      val key = indexNote.key
      val noteResponse = authorized.get(key)
      noteResponse.status should be (HttpStatus.SC_OK)
      noteResponse.payload should not be (null)
      val getNote = noteResponse.payload
      getNote.key should be (indexNote.key)
      getNote.createdate should be (indexNote.createdate)
      getNote.content.length should be > (0)
    }
  }
}
