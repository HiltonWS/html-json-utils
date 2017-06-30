/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017  - Hilton W. Silva
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Hilton W. Silva
  */
class TestHTMLTableToJSON extends FlatSpec with Matchers {

  behavior of "A HTML table to JSON"

  it should "parse tables from URL with table index to JSON" in {
    val result = HTMLTableToJSON.parseURL("http://hiltonws.com.br/demo/table-demo.html", 1)
    val expect = "[{\"1\":[{\"Coloumn 3\":[\"Row 1 Cell 3\"],\"Column 1\":[\"Row 1 Cell 1\",\"Row 2 Cell 2\",\"Row 3 Cell 1\"],\"Column 2\":[\"Row 1 Cell 2\",\"Row 2 Cell 3\"]}]}]"
    result.toString shouldBe expect
  }

  it should "parse tables from URL to JSON" in {
    val result = HTMLTableToJSON.parseURL("http://hiltonws.com.br/demo/table-demo.html")
    val expect = "[{\"0\":[{\"Year of Birth\":[\"2001\",\"2000\",\"1970\"],\"Height\":[\"172\",\"174\",\"162\"],\"Age\":[\"15\",\"16\",\"46\"],\"Name\":[\"Imad\",\"Raheel\",\"Mushtaq\"]}]},{\"1\":[{\"Coloumn 3\":[\"Row 1 Cell 3\"],\"Column 1\":[\"Row 1 Cell 1\",\"Row 2 Cell 2\",\"Row 3 Cell 1\"],\"Column 2\":[\"Row 1 Cell 2\",\"Row 2 Cell 3\"]}]}]"
    result.toString shouldBe expect
  }

  it should "parse tables from HTML to JSON" in {
    val result = HTMLTableToJSON.parseHTML("<table>" +
      "  <tr>" +
      "   <th>Company</th>" +
      "    <th>Contact</th>" +
      "   <th>Country</th>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Alfreds Futterkiste</td>" +
      "    <td>Maria Anders</td>" +
      "    <td>Germany</td>" +
      "  </tr>" +
      "  <tr>" +
      "   <td>Centro comercial Moctezuma</td>" +
      "    <td>Francisco Chang</td>" +
      "    <td>Mexico</td>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Ernst Handel</td>" +
      "    <td>Roland Mendel</td>" +
      "  <td>Austria</td>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Island Trading</td>" +
      "    <td>Helen Bennett</td>" +
      "    <td>UK</td>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Laughing Bacchus Winecellars</td>" +
      "    <td>Yoshi Tannamuri</td>" +
      "    <td>Canada</td>" +
      "  </tr>" +
      " <tr>" +
      "    <td>Magazzini Alimentari Riuniti</td>" +
      "    <td>Giovanni Rovelli</td>" +
      "    <td>Italy</td>" +
      "  </tr>" +
      "</table>")
    val expect = "[{\"0\":[{\"Company\":[\"Alfreds Futterkiste\",\"Centro comercial Moctezuma\",\"Ernst Handel\",\"Island Trading\",\"Laughing Bacchus Winecellars\",\"Magazzini Alimentari Riuniti\"],\"Country\":[\"Germany\",\"Mexico\",\"Austria\",\"UK\",\"Canada\",\"Italy\"],\"Contact\":[\"Maria Anders\",\"Francisco Chang\",\"Roland Mendel\",\"Helen Bennett\",\"Yoshi Tannamuri\",\"Giovanni Rovelli\"]}]}]"
    result.toString shouldBe expect
  }

  it should "parse tables from HTML with index to JSON" in {
    val result = HTMLTableToJSON.parseHTML("<table>" +
      "  <tr>" +
      "   <th>Company</th>" +
      "    <th>Contact</th>" +
      "   <th>Country</th>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Alfreds Futterkiste</td>" +
      "    <td>Maria Anders</td>" +
      "    <td>Germany</td>" +
      "  </tr>" +
      "  <tr>" +
      "   <td>Centro comercial Moctezuma</td>" +
      "    <td>Francisco Chang</td>" +
      "    <td>Mexico</td>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Ernst Handel</td>" +
      "    <td>Roland Mendel</td>" +
      "  <td>Austria</td>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Island Trading</td>" +
      "    <td>Helen Bennett</td>" +
      "    <td>UK</td>" +
      "  </tr>" +
      "  <tr>" +
      "    <td>Laughing Bacchus Winecellars</td>" +
      "    <td>Yoshi Tannamuri</td>" +
      "    <td>Canada</td>" +
      "  </tr>" +
      " <tr>" +
      "    <td>Magazzini Alimentari Riuniti</td>" +
      "    <td>Giovanni Rovelli</td>" +
      "    <td>Italy</td>" +
      "  </tr>" +
      "</table>", 0)
    val expect = "[{\"0\":[{\"Company\":[\"Alfreds Futterkiste\",\"Centro comercial Moctezuma\",\"Ernst Handel\",\"Island Trading\",\"Laughing Bacchus Winecellars\",\"Magazzini Alimentari Riuniti\"],\"Country\":[\"Germany\",\"Mexico\",\"Austria\",\"UK\",\"Canada\",\"Italy\"],\"Contact\":[\"Maria Anders\",\"Francisco Chang\",\"Roland Mendel\",\"Helen Bennett\",\"Yoshi Tannamuri\",\"Giovanni Rovelli\"]}]}]"
    result.toString shouldBe expect
  }

}
