package edu.nyu.dlts.fiwalk

import scala.sys.process._
import scala.collection.JavaConversions._
import java.io.File
import java.util.HashMap

class Exif(file: File){
  val exifmap = new HashMap[String, String]
  val exif = ("exiftool " + file.getAbsolutePath) lines_! ProcessLogger(line => ())
  
  exif.foreach{entry =>
    exifmap.put(formatKey(entry.split(" : ")(0)), entry.split(" : ")(1).trim)
  }

  def formatKey(key:String): String = {
    var k = key.trim
    k = k.replaceAll(" ", "")
    k = k.replaceAll("/", "")
    Character.toLowerCase(k(0)).toString() + k.substring(1)
  }

  override def toString(): String = {
    val builder = new StringBuilder()
    exifmap.foreach{kv =>
      builder.append(kv._1 + ": " + kv._2 + "\n")
    }

    val s = builder.toString
    s.substring(0, s.length - 1)
  }
  
}
