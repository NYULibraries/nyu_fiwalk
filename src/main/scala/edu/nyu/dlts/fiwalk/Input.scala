package edu.nyu.dlts.fiwalk

class Input(path: String){
  val file = new java.io.File(path)
  println(file.exists)
}

object Input{
  def main(args: Array[String]){
    val path = "src/main/resources/testfile.txt"
    new Input(path)
  }
}
