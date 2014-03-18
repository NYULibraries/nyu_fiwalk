package edu.nyu.dlts.fiwalk

import scala.sys.process._
import scala.util.matching.Regex._

class FidoWrapper(file: java.io.File){
  val fido = ("fido " + file.getAbsolutePath) lines_! ProcessLogger(line => ())  
  val version = "fido -v" lines_! ProcessLogger(line => ())
  val pattern = "^\".*\"$".r

  def getPronom(): Unit = {
    val fields = fido(0).split(",")
    println("pronomPuid: " + removeQuotes(fields(2)))
    println("pronomFormatName: " + removeQuotes(fields(3)))
    println("pronomSignatureName: " + removeQuotes(fields(4)))
    println("pronomMimeType: " + removeQuotes(fields(7)))
    println("pronomMatchType: " + removeQuotes(fields(8)))    
  }

  def getVersion(): Unit = {
    val fields = version(0).split(" ")
    println("pronomSignatureFileVersion: " + fields(2).substring(1, fields(2).length - 1))
    println("pronomContainerFileVersion: " + fields(3).substring(0, fields(3).length - 1))
    println("fidoVersion: " + fields(1).substring(1))
  }


  def removeQuotes(in: String): String = {
    if((pattern findAllIn in).isEmpty){
      in
    } else {
      in.substring(1, in.length -1)
    }
  }
}
