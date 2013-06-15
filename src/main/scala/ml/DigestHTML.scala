
package com.meanrecipes.extractr.ml

import scala.io.Source
import java.io.File
import org.htmlcleaner.HtmlCleaner
//import collection.JavaConversions._
import java.net.URL

/*
Right. So this thing is going to take incoming HTML and spit out smaller
Chunks of Stuff. This does *not* do any of the ML tasks required; this
thing should do everything to take an incoming web page and create
some kind of object ML can operate on.

Resources:
http://alvinalexander.com/scala/scala-html-parsing-htmlcleaner-parser
http://en.wikipedia.org/wiki/Document_Object_Model
http://myadventuresincoding.wordpress.com/2011/09/29/scala-hello-world-rest-api-with-scalatra-and-simple-build-tool/


*/

class DigestHTML(filePath: String) {
  // Core object. Takes an incoming resource, returns cleaned pieces; should be able to be fed
  // directly into an ML solution.

  val textHeap = loadAndParseFile(filePath)

  def loadAndParseFile(filePath: String): List[String] = {
    // This function... well, ideally will work for all Blogspot blogs. Hrm.
    val cleaner = new HtmlCleaner
//    val rootNode = cleaner.clean(new URL(urlToClean).openStream)
    val rootNode = cleaner.clean(new File(filePath) )
    val elements = rootNode.getElementsByName("div", true)
    elements.map{ elem =>
      elem.getAttributeByName("class") match {
        case "post" | "post-body" => elem.getText.toString.replaceAll("&nbsp;", "")
        case _ => 
      }
    }.toList
//    .foldLeft(List[String]())( (text, acc) => if (text != "" && !text.isEmpty) text :: acc else acc )
  }

}
