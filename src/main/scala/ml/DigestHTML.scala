
package com.meanrecipes.extractr.ml

import scala.io.Source
import java.io.File
import org.htmlcleaner.HtmlCleaner
//import collection.JavaConversions._
import java.net.URL

/*
 * Takes an incoming HTML page, extracts elements from the DOM, returns them as a list of strings
 * 
 * ...might need to return a List of Lists. Erghm.
 * @param filePath = file path to the page in question
 * @returns List[String]
*/

object DigestHTML {

  def processFileToList(filePath: String): List[String] = loadAndParseFile(filePath)

  def processFileToListOfLists(filePath: String): List[List[String]] = loadAndParseFile(filePath).map(_.split("\n").toList).toList

  private def loadAndParseFile(filePath: String): List[String] = {
    // This function... well, ideally will work for all Blogspot blogs. Hrm.
//    val rootNode = cleaner.clean(new URL(urlToClean).openStream)
    val cleaner = new HtmlCleaner
    val rootNode = cleaner.clean(new File(filePath) )
    val postBody = rootNode
      .getElementsByName("div", true)
      .map{ elem =>
        elem.getAttributeByName("class") match {
          case "post" | "post-body" => elem.getText.toString.replaceAll("&nbsp;", "")
          case _ =>
        }
      }
      .toList
    postBody.foldLeft(List[String]())(
      (acc, entry) => entry match {
        case entry: String => entry :: acc
        case entry: Any => acc
      }
    )
  }

}
