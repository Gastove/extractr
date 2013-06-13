
import org.scalatest.FunSuite
import com.meanrecipes.extractr.ml.DigestHTML

class DigestHTMLSuite extends FunSuite {

  test ("Here we go") {
    val tpath = "/home/rdonaldson/Code/meanrecipes/extractr/lib/training_data/raw_html/2012-08-01_orangette_essex_raw.html"
    new DigestHTML(tpath)
  }

}
