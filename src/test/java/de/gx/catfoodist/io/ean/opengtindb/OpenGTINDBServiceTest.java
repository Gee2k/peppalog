package de.gx.catfoodist.io.ean.opengtindb;

import de.gx.catfoodist.io.ean.Ean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenGTINDBServiceTest {

    OpenGTINService openGTINService = new OpenGTINService();


    String vitaColaResult = "\n" +
            "error=0\n" +
            "---\n" +
            "asin=\n" +
            "name=Cola\n" +
            "detailname=Vita Cola Original, 1.5L\n" +
            "vendor=Thüringer Waldquell Mineralbrunnen GmbH\n" +
            "maincat=Getränke, Alkohol\n" +
            "subcat=Limonaden\n" +
            "maincatnum=11\n" +
            "subcatnum=7\n" +
            "contents=0\n" +
            "pack=512\n" +
            "origin=Deutschland\n" +
            "descr=mit Vitamin C\n" +
            "name_en=\n" +
            "detailname_en=\n" +
            "descr_en=\n" +
            "validated=100 %\n" +
            "---\n";

    String srirachaResult = "\n" +
            "error=0\n" +
            "---\n" +
            "asin=\n" +
            "name=\n" +
            "detailname=FLYING GOOSE Sriracha Mayoo Sauce - Mayonnaise, leicht scharf, orange Kappe, Würzsauce aus Thailand, 2er Pack (2 x 455 ml)\n" +
            "vendor=Kreyenhop & Kluge GmbH & Co. KG\n" +
            "maincat=\n" +
            "subcat=\n" +
            "maincatnum=-1\n" +
            "subcatnum=\n" +
            "contents=\n" +
            "pack=\n" +
            "origin=Thailand\n" +
            "descr=Die beliebte Würzsauce kreiert in der thailändischen Küstenstadt Si Racha: Flying Goose Sriracha Mayoo Sauce aus sonnen gereiften Chilischoten, leicht scharfe Mayonnaise Für alle die es würzig lieben, die cremige Mayonnaise überzeugt mit einer einzigartigen Kombination aus leichter Schärfe und frischer Mayo Die pikante Sriracha Mayonnaise der weltbekannten Flying Goose Brand macht jedes Gericht zu einem besonders würzigen Geschmackserlebnis - ideal zum Würzen, Dippen oder Marinieren Die köstliche Chilicreme schmeckt hervorragend zu zahlreichen Gerichten, probieren Sie die vielseitige Würzsoße und verfeinern Sie schnell und einfach Pommes, Hamburger und Grillfleisch Lieferumfang: FLYING GOOSE Sriracha Mayoo Sauce, leicht scharf - vegetarisch und glutenfrei, Produkt kann Spuren von Erdnüssen enthalten- 2er Pack (2 x 455 ml) Aus sonnengereiften Chilischoten hergestellt Ideal als Dip zu Sushi oder Brot Vegetarische Sauce ohne Ei hergestellt Exzellente Qualität dank ausgesuchter Zutaten Kreyenhop & Kluge GmbH & Co. KG, 28876 Oyten\n" +
            "name_en=\n" +
            "detailname_en=\n" +
            "descr_en=\n" +
            "validated=0 %\n" +
            "---\n";

    @Test
    public void testFindSubString() throws Exception {
        String cola = openGTINService.findSubString(OpenGTINDB.NAME, vitaColaResult);
        String sriracha = openGTINService.findSubString(OpenGTINDB.DETAIL_NAME, srirachaResult);

        Assert.assertEquals("name not found", "Cola", cola);
        Assert.assertEquals("name not found", "FLYING GOOSE Sriracha Mayoo Sauce - Mayonnaise, leicht scharf, orange Kappe, Würzsauce aus Thailand, 2er Pack (2 x 455 ml)", sriracha);
    }

    @Test
    public void testGetInfoForEan() throws Exception {
        String vitaColaEan = "4013595644173";
        String srirachaEan = "8853662056661";

        //will not work without providing an opengtin queryID

        Ean vitaColaResult = openGTINService.getInfoForEan(vitaColaEan);
        Ean srirachaResult = openGTINService.getInfoForEan(srirachaEan);

        Assert.assertEquals("sriracha ean does not match", srirachaEan, srirachaResult.getEan());
        Assert.assertEquals("vitacola ean does not match", vitaColaEan, vitaColaResult.getEan());
    }
}