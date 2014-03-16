package edu.nyu.dlts.fiwalk

class ScanResult(r: String){
  val result = r
  var status = "FAILED"
  var signature = ""

  val STREAM_PREFIX = "stream: ";
  val RESPONSE_OK = "stream: OK";
  val FOUND_SUFFIX = "FOUND";
  val RESPONSE_SIZE_EXCEEDED = "INSTREAM size limit exceeded. ERROR";
  val RESPONSE_ERROR_WRITING_FILE = "Error writing to temporary file. ERROR";

  def setResult(result: String): Unit = {
    if(result == null){status = "ERROR"}
    else if (RESPONSE_OK.equals(result)) {
          setStatus("PASSED");
    }
    else if(result.endsWith(FOUND_SUFFIX)){
      setSignature(result.substring(STREAM_PREFIX.length(), result.lastIndexOf(FOUND_SUFFIX) - 1));
    } else if (RESPONSE_SIZE_EXCEEDED.equals(result)) {
            setStatus("ERROR");
    } else if (RESPONSE_ERROR_WRITING_FILE.equals(result)) {
            setStatus("Error");
    }
  }

  def setSignature(signature: String):Unit = {
    this.signature = signature
  }

  def setStatus(status: String): Unit = {
    this.status = status
  } 

  def getSignature(): String = {
    signature
  }

  def getResult(): String = {
    result
  }
}
