package services

import models.{EventDTO, EventRecordDTO}
import play.api.Play.current
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * EventRecorderService.
  * @author xelita
  */
object EventRecorderService {

  lazy val reactiveMongoApi = current.injector.instanceOf[ReactiveMongoApi]

  /**
    * Get a reactive mongo collection.
    * @param name the name of the collection to retrieved
    * @return JSONCollection
    */
  def collection(name: String): JSONCollection = reactiveMongoApi.db.collection[JSONCollection](name)

  /**
    * Check the application status
    * @return Future
    */
  def check: Future[Option[EventRecordDTO]] = {
    collection("events").find(Json.obj()).one[EventRecordDTO]
  }
}
