package edu.nyu.dlts.fiwalk

class ClamScan(host: String, port: Int, timeout: Int){

  val CHUNK_SIZE = 2048
  val INSTREAM = "zINSTREAM\0".getBytes()
  val PING = "zPING\0".getBytes()
  val STATS = "nSTATS\n".getBytes()

  def stats(): String ={
    return cmd(STATS);
  }

  def ping(): Boolean = {
    return "PONG\0".equals(cmd(PING));
  }

  def cmd(command: Array[Byte]): String = {
    val socket = getSocket()
    val response = new StringBuilder()
    val dos = getDOS(socket)
    dos.write(command)
    dos.flush
    val is = socket.getInputStream
    val buffer = new Array[Byte](CHUNK_SIZE)

    Stream.continually(is.read(buffer)).takeWhile(_ != -1).foreach{i => 
      response.append(new String(buffer, 0, i))
    }

    dos.close
    socket.close
    response.toString()
  }

  def scan(in : Array[Byte]): ScanResult = {
    return scan(new java.io.ByteArrayInputStream(in));
  }

  def scan(is: java.io.InputStream): ScanResult = {
    val socket = getSocket
    val dos = getDOS(socket)
    dos.write(INSTREAM)
    val buffer = new Array[Byte](CHUNK_SIZE)
    var response = ""
    Stream.continually(is.read(buffer)).takeWhile(_ != -1).foreach{i =>
      if(i > 0){
        dos.writeInt(i);
        dos.write(buffer, 0, i);
      }
    }
    dos.writeInt(0)
    dos.flush()

    val read = socket.getInputStream().read(buffer);
    if (read > 0) response = new String(buffer, 0, read);
    dos.close;
    socket.close;
    new ScanResult(response.trim());
  }

  def getDOS(socket: java.net.Socket): java.io.DataOutputStream = {   
    val dos = new java.io.DataOutputStream(socket.getOutputStream());
    dos
  }

  def getSocket(): java.net.Socket = {
    val socket = new java.net.Socket()   
    try{
      socket.connect(new java.net.InetSocketAddress(host, port))
      socket.setSoTimeout(timeout)
    } catch {
      case ioe: java.io.IOException => println("Could not connect to clamd server")
      case soe: java.net.SocketException => println("Could not set socket timeout")
      System.exit(1)
    }
    socket
  }
}
