package edu.nyu.dlts.fiwalk
import java.io.File
import java.io.FileInputStream
import com.typesafe.config._

/*
trait Tools {

  def fido(file: File) {

  }
  /*
  val file = new File(path)
  val fido = new FidoWrapper(file).fidoModel
  println(fido)
  println("identificationUUID: " + java.util.UUID.randomUUID.toString)
  
  val scan = new ClamScan("localhost", 3310, 600000000).scan(new FileInputStream(file))
  println(scan)
  println("virusScanUUID: " + java.util.UUID.randomUUID.toString)

  if(fido.mediaType != null){
    if(fido.mediaType.getType.equals("image")){
      val exif = new Exif(file)
      println(exif)
      println("exifUUID: " + java.util.UUID.randomUUID.toString)
    }
  }
  */
}
*/

object Input extends App {
  val conf = ConfigFactory.load()
  val path = args(0)
  val file = new File(path)
  val fido = new FidoWrapper(file).fidoModel
  println("identificationUUID: " + java.util.UUID.randomUUID.toString)
  println(fido)
}
