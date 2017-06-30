import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Parses HTML tables to JSON
 * @author hilton
 */
public class HTMLTableToJSON {

    private HTMLTableToJSON() {
        //singleton
    }

    /**
     * Parse a {@link Document} to JSON
     * @param document The document to parse
     * @param tableIndex Table index in document
     * @return A JSONArray represent HTML table(s)
     */
    public static JSONArray parse(Document document, Integer tableIndex) {
        Elements tables = document.select("table");
        JSONArray parent = new JSONArray();

        if (tableIndex == null) {
            tables.forEach(item ->
                    inTable(tables, tables.indexOf(item), parent)
            );
        } else {
            inTable(tables, tableIndex, parent);
        }

        return parent;

    }

    /**
     * Parse a HTML String contains table to JSON
     * @param htmlTable The HTML contains table
     * @param tableIndex Table table index in HTML
     * @return A JSONArray represent HTML table(s)
     */
    public static JSONArray parseHTML(String htmlTable, Integer tableIndex) {
        Document document = Jsoup.parse(htmlTable);
        return parse(document, tableIndex);
    }

    /**
     * Parse a HTML String contains table to JSON
     * @param htmlTable The HTML contains table
     * @return A JSONArray represent HTML table(s)
     */
    public static JSONArray parseHTML(String htmlTable) {
        Document document = Jsoup.parse(htmlTable);
        return parse(document, null);
    }

    /**
     * Parse a URL address contains table to JSON
     * @param urlAddress The URL contains table
     * @return A JSONArray represent HTML table(s)
     */
    public static JSONArray parseURL(String urlAddress) throws IOException {
        Document document = Jsoup.connect(urlAddress).get();
        return parse(document, null);
    }

    /**
     * Parse a URL address contains table to JSON
     * @param urlAddress The URL contains table
     * @param tableIndex Table table index in HTML
     * @return A JSONArray represent HTML table(s)
     */
    public static JSONArray parseURL(String urlAddress, Integer tableIndex) throws IOException {
        Document document = Jsoup.connect(urlAddress).get();
        return parse(document, tableIndex);
    }

    private static void inTable(Elements tables, Integer tableIndex, JSONArray parent) {
        JSONObject tableJson = new JSONObject();
        Element table = tables.get(tableIndex);
        Elements trs = table.select("tr");
        Elements ths = trs.get(0).select("th");
        JSONObject object = new JSONObject();

        int trsSize = 1;
        while (trsSize < trs.size()) {
            Elements tds = trs.get(trsSize).select("td");
            int thsSize = 0;
            while (thsSize < ths.size()) {
                if (thsSize < tds.size()) {
                    object.append(ths.get(thsSize).text(), tds.get(thsSize).text());
                }
                thsSize++;
            }
            trsSize++;
        }
        tableJson.append(String.valueOf(tableIndex), object);
        parent.put(tableJson);
    }

}


