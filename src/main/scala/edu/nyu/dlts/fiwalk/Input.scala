package edu.nyu.dlts.fiwalk

class Input(path: String){
  val file = new java.io.File(path)
  new FidoWrapper(file).getPronom
  new VirusScan(file)
  println("uuid: " + java.util.UUID.randomUUID.toString)

}

class VirusScan(file: java.io.File){
  import scala.util.matching.Regex._
  val fis = new java.io.FileInputStream(file)
  val scanner = new ClamScan("localhost", 3310, 600000000);
  val result = scanner.scan(fis)
  val pattern = "FOUND".r
  if((pattern findAllIn result.getResult).isEmpty){
    println("virusFound: false")
  } else {
    println("virusFound: true")
    println("VirusSignature: " + result.getSignature)
  }
}

object Input{
  def main(args: Array[String]){
    new Input(args(0))
  }
}
