package controllers

import play.api.libs.json.Json
import play.api.mvc._
import services.ExceptionRecorderService

import scala.concurrent.ExecutionContext.Implicits.global

class ExceptionRecorderController extends Controller {

  /**
   * Check the application status
   * @return JSON
   */
  def check = Action.async {
    val resultFuture = ExceptionRecorderService.check
    resultFuture.map(result => Ok(Json.toJson(result)))
  }
}
