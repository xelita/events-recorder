package services

import models.ExceptionDTO
import play.api.Play.current
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ExceptionRecorderService {

  lazy val reactiveMongoApi = current.injector.instanceOf[ReactiveMongoApi]

  def collection(name: String): JSONCollection = reactiveMongoApi.db.collection[JSONCollection](name)

  def check: Future[Option[ExceptionDTO]] = {
    collection("exceptions").find(Json.obj()).one[ExceptionDTO]
  }
}
