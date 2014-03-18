nyu_fiwalk
==========

DGI script for fiwalk

prerequisite for build: 
* java 7
* sbt
* fido.py running as an alias or link on your system as 'fido'
* clamav daemon running on port 3310

prequisite for deployment
* sleuthkit 4

how to build:
-------------

$sbt

\> assembly

how to test it works
--------------------
\>java -jar target/scala-2.10/nyudgi.jar src/main/resources/test.odt<br />

<b>this should output</b><br />
pronomPuid: fmt/291<br />
pronomFormatName: OpenDocument Text<br />
pronomSignatureName: ODF 1.2 text<br />
pronomMimeType: application/vnd.oasis.opendocument.text<br />
pronomMatchType: signature<br />
pronomSignatureFileVersion: formats-v70.xml<br />
pronomContainerFileVersion: container-signature-20130501.xml<br />
fidoVersion: 1.3.1<br />
identificationUuid: 8b07e40d-df3f-4bfc-80b8-085859a05ba5<br />
scanStatus: PASSED<br />
clamAVVersion: ClamAV 0.98.1<br />
virusScanUuid: cfc4a6e8-0817-46dc-ada8-f7b6d4b721f6<br />

how to run
----------
you will need to edit fiwalk.conf to reflect the location of nyudgi.jar<br/>

you can then run:<br />
\>fiwalk -xc fiwalk.conf [image_name] > [outputfilename]<br >
<i>example: $ fiwalk -xc fiwalk.config nps-2009-canon2-gen1.E01 > nps-2009-canon2-gen1.dfxml.xml</i><br />

<b>your dfxml output should now include plugin process tags</b><br />

```xml
  <!-- plugin_process -->
      <pronomPuid>x-fmt/391</pronomPuid>
      <pronomFormatName>Exchangeable Image File Format (Compressed)</pronomFormatName>
      <pronomSignatureName>EXIF Compressed Image 2.2 (little-endian)</pronomSignatureName>
      <pronomMimeType>image/jpeg</pronomMimeType>
      <pronomMatchType>signature</pronomMatchType>
      <pronomSignatureFileVersion>formats-v70.xml</pronomSignatureFileVersion>
      <pronomContainerFileVersion>container-signature-20130501.xml</pronomContainerFileVersion>
      <fidoVersion>1.3.1</fidoVersion>
      <identificationUuid>49050200-e308-4060-886a-14a8efd82078</identificationUuid>
      <scanStatus>PASSED</scanStatus>
      <clamAVVersion>ClamAV 0.98.1</clamAVVersion>
      <virusScanUuid>6dea8d54-107a-43d0-a1fe-beb9c6bc4a21</virusScanUuid>
```


