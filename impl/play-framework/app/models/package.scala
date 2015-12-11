import play.api.libs.json.{Format, Json}
import play.modules.reactivemongo.json.BSONFormats.BSONObjectIDFormat
import reactivemongo.bson._

/**
  * Models pacakges.
  * @author xelita
  */
package object models {

  case class EventRecordDTO(_id: BSONObjectID, applicationName: Option[String], event: Option[EventDTO])

  object EventRecordDTO {

    implicit val eventRecordFormat: Format[EventRecordDTO] = Json.format[EventRecordDTO]
  }

  case class EventDTO(eventType: Option[String], eventData: Option[String])

  object EventDTO {

    implicit val eventFormat: Format[EventDTO] = Json.format[EventDTO]

    // Needed to avoid intellij to remove the import play.modules.reactivemongo.json.BSONFormats.BSONObjectIDFormat
    val forcedImport = BSONObjectIDFormat
  }

}
