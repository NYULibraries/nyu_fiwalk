package edu.nyu.dlts.fiwalk

import scala.sys.process._
import scala.util.matching.Regex._

class FidoWrapper(file: java.io.File){

  val fido = ("fido " + file.getAbsolutePath) lines_! ProcessLogger(line => ())  
  val pattern = "^\".*\"$".r

  def getPronom(): Unit = {
    val fields = fido(0).split(",")
    println("pronomPuid: " + removeQuotes(fields(2)))
    println("pronomFormatName: " + removeQuotes(fields(3)))
    println("pronomSignatureName: " + removeQuotes(fields(4)))
    println("pronomMimeType: " + removeQuotes(fields(7)))
    println("pronomMatchType: " + removeQuotes(fields(8)))    
  }

  def removeQuotes(in: String): String = {
    if((pattern findAllIn in).isEmpty){
      in
    } else {
      in.substring(1, in.length -1)
    }
  }
}
