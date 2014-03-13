package edu.nyu.dlts.fiwalk

import scala.sys.process._

class FidoWrapper(file: java.io.File){
  val fido = ("fido " + file.getAbsolutePath) lines_! ProcessLogger(line => ())  
  def getPronom(): Unit = {
    val fields = fido(0).split(",")
    println("pronomPuid: " + fields(2))
    println("pronomFormatName: " + fields(3))
    println("pronomSignatureName: " + fields(4))
    println("pronomMimeType: " + fields(7))
    println("pronomMatchType: " + fields(8))    
  }
}
