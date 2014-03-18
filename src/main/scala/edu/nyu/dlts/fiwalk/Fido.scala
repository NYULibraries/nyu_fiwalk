package edu.nyu.dlts.fiwalk

import scala.sys.process._
import scala.util.matching.Regex._

class FidoWrapper(file: java.io.File){
  val fidoParse = ("fido " + file.getAbsolutePath) lines_! ProcessLogger(line => ())  
  val version = "fido -v" lines_! ProcessLogger(line => ())
  val pattern = "^\".*\"$".r
  val fidoModel = getModel

  def getModel(): FidoModel = {
    val pronomFields = fidoParse(0).split(",")
    val versFields = version(0).split(" ")

    new FidoModel(removeQuotes(pronomFields(2)), removeQuotes(pronomFields(3)), removeQuotes(pronomFields(4)), removeQuotes(pronomFields(7)), removeQuotes(pronomFields(8)),
		  versFields(2).substring(1, versFields(2).length - 1), versFields(3).substring(0, versFields(3).length - 1),  versFields(1).substring(1))
  }

  def removeQuotes(in: String): String = {
    if((pattern findAllIn in).isEmpty){
      in
    } else {
      in.substring(1, in.length -1)
    }
  }
}

class FidoModel(puid: String, format: String, sigName: String, mime: String, matchType: String, sigFile: String, contFile: String, fidoVers: String){
  import org.apache.tika.mime.MediaType
  val pronomPuid = puid
  val pronomFormat = format
  val pronomSignature = sigName
  val pronomMime = mime
  val pronomMatch = matchType
  val pronomSigFile = sigFile
  val pronomContFile = contFile
  val fidoVersion = fidoVers
  val mediaType = MediaType.parse(mime)
  
  override def toString(): String = {
    val builder = new StringBuilder()
    builder.append("pronomPuid: " + pronomPuid)
    builder.append("\npronomFormatName: " + pronomFormat)
    builder.append("\npronomSignatureName: " + pronomSignature)
    builder.append("\npronomMimeType: " + pronomMime)
    builder.append("\npronomMatchType: " + pronomMatch)
    builder.append("\npronomSignatureFileVersion: " + pronomSigFile)
    builder.append("\npronomContainerFileVersion: " + pronomContFile)
    builder.append("\nfidoVersion: " + fidoVersion)
    builder.toString
  }
}
