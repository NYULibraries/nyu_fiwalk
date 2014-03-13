package edu.nyu.dlts.fiwalk

class Input(path: String){
  val file = new java.io.File(path)
  new FidoWrapper(file).getPronom
}

object Input{
  def main(args: Array[String]){
    new Input(args(0))
  }
}
