import play.api.libs.json.Json

package object models {

  case class ExceptionDTO(_id: String, code: String, message: String, details: String)

  object ExceptionDTO {

    implicit val exceptionReads = Json.reads[ExceptionDTO]
    implicit val exceptionWrites = Json.writes[ExceptionDTO]
  }
}
