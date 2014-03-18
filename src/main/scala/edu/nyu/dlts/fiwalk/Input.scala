package edu.nyu.dlts.fiwalk
import java.io.File
import java.io.FileInputStream

class Input(path: String){
  val file = new File(path)
  val fido = new FidoWrapper(file).fidoModel
  println(fido)
  println("identificationUuid: " + java.util.UUID.randomUUID.toString)
  
  val scan = new ClamScan("localhost", 3310, 600000000).scan(new FileInputStream(file))
  println(scan)
  println("virusScanUuid: " + java.util.UUID.randomUUID.toString)


}

object Input{
  def main(args: Array[String]){
    new Input(args(0))
  }
}
