package edu.nyu.dlts.fiwalk

class Input(path: String){
  val file = new java.io.File(path)
  val fido = new FidoWrapper(file)
  new virusScan(file)
  println("virusScanUuid: " + java.util.UUID.randomUUID.toString)
  fido.getPronom
  fido.getVersion
  println("identificationUuid: " + java.util.UUID.randomUUID.toString)
}

class virusScan(file: java.io.File){
  import scala.util.matching.Regex._
  val fis = new java.io.FileInputStream(file)
  val scanner = new ClamScan("localhost", 3310, 600000000);
  val scanResult = scanner.scan(fis)
  val pattern = "FOUND".r
  if((pattern findAllIn scanResult.result).isEmpty){
    println("virusFound: false")
  } else {
    println("virusFound: true")
    println("VirusSignature: " + scanResult.signature)
  }
}

object Input{
  def main(args: Array[String]){
    new Input(args(0))
  }
}
