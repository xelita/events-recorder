package controllers

import play.api.libs.json.Json
import play.api.mvc._
import services.EventRecorderService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * EventRecorderController.
  * @author xelita
  */
class EventRecorderController extends Controller {

  /**
   * Check the application status
   * @return JSON
   */
  def check = Action.async {
    val resultFuture = EventRecorderService.check
    resultFuture.map(result => Ok(Json.toJson(result)))
  }
}
