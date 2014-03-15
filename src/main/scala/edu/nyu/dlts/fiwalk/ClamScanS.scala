package edu.nyu.dlts.fiwalk

class ClamScanS(host: String, port: Int, timeout: Int){
  println("scanner")
  val CHUNK_SIZE = 2048
  val INSTREAM = "zINSTREAM\0".getBytes()
  val PING = "zPING\0".getBytes()
  val STATS = "nSTATS\n".getBytes()

  def cmd(command: Array[Byte]): String = {
    val socket = new java.net.Socket()
    
    try{
      socket.connect(new java.net.InetSocketAddress(host, port))
      socket.setSoTimeout(timeout)
    } catch {
      case ioe: java.io.IOException => println("Could not connect to clamd server")
      case soe: java.net.SocketException => println("Could not set socket timeout")
    }

    val response = new StringBuilder()
    val dos = new java.io.DataOutputStream(socket.getOutputStream)
    dos.write(command)
    dos.flush
    val is = socket.getInputStream
    var read = CHUNK_SIZE
    val buffer = new Array[Byte](CHUNK_SIZE)
    while(read == buffer){
      read = is.read(buffer)
      response.append(new String(buffer, 0, read))
    }

    dos.close
    socket.close
    response.toString()
  }
}
