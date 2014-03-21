package edu.nyu.dlts.fiwalk
import java.io.File
import java.io.FileInputStream

class Input(path: String){
  val file = new File(path)
  val fido = new FidoWrapper(file).fidoModel
  println(fido)
  println("identificationUUID: " + java.util.UUID.randomUUID.toString)
  
  val scan = new ClamScan("localhost", 3310, 600000000).scan(new FileInputStream(file))
  println(scan)
  println("virusScanUUID: " + java.util.UUID.randomUUID.toString)

  if(fido.mediaType.getType.equals("image")){
    val exif = new Exif(file)
    println(exif)
    println("exifUUID: " + java.util.UUID.randomUUID.toString)
  }

}

object Input{
  def main(args: Array[String]){
    new Input(args(0))
  }
}
