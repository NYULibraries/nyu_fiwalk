package edu.nyu.dlts.fiwalk

class ScanResult(r: String, v: String){
  val result = r
  val version = v
  val RESPONSE_OK = "stream: OK";
  val STREAM_PREFIX = "stream: ";
  val FOUND_SUFFIX = "FOUND";
  val RESPONSE_SIZE_EXCEEDED = "INSTREAM size limit exceeded. ERROR";
  val RESPONSE_ERROR_WRITING_FILE = "Error writing to temporary file. ERROR";

  val status = setStat
  val signature = setSig
  
  def setStat(): String ={
    if(result.isEmpty){"ERROR"}
    else if (RESPONSE_OK.equals(result)){"PASSED"}
    else if(result.endsWith(FOUND_SUFFIX)){"FOUND"}
    else if (RESPONSE_SIZE_EXCEEDED.equals(result)){"ERROR"}
    else if (RESPONSE_ERROR_WRITING_FILE.equals(result)){"ERROR"}    
    else{"FAILED"}
  }

  def setSig():String = {
    if(status.equals("FOUND")){result.substring(STREAM_PREFIX.length(), result.lastIndexOf(FOUND_SUFFIX) - 1)}
    else{""}
  }
}

