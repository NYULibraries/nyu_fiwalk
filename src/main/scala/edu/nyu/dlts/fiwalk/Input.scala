package edu.nyu.dlts.fiwalk

class Input(path: String){
  val file = new java.io.File(path)
  val fido = new FidoWrapper(file).fidoModel
  println(fido)
  println("identificationUuid: " + java.util.UUID.randomUUID.toString)
  new virusScan(file)
  println("virusScanUuid: " + java.util.UUID.randomUUID.toString)


}

class virusScan(file: java.io.File){
  import scala.util.matching.Regex._
  val fis = new java.io.FileInputStream(file)
  val scanner = new ClamScan("localhost", 3310, 600000000);
  val scanResult = scanner.scan(fis)
  val pattern = "FOUND".r
  if((pattern findAllIn scanResult.result).isEmpty){
    println("virusFound: false")
    println("clamavVersion: " + scanResult.version)
  } else {
    println("virusFound: true")
    println("VirusSignature: " + scanResult.signature)
    println("clamavVersion: " + scanResult.version)
  }
}

object Input{
  def main(args: Array[String]){
    new Input(args(0))
  }
}
