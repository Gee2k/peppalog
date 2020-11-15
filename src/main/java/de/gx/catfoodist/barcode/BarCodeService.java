package de.gx.catfoodist.barcode;

import com.google.zxing.DecodeHintType;
import org.apache.camel.LoggingLevel;
import org.apache.camel.dataformat.barcode.BarcodeDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BarCodeService extends SpringRouteBuilder {

    DataFormat code = new BarcodeDataFormat();


    @Override
    public void configure() throws Exception {
//        code.addToHintMap(DecodeHintType.TRY_HARDER, Boolean.true);
//        from("file://barcode_in?noop=true")
//        from("file://barcode_in")
        from("file://C:/Users/Gee/IdeaProjects/CatFoodist/barcode_in")
                .unmarshal(code) // for unmarshalling, the instance doesn't matter
                .log(LoggingLevel.DEBUG, "code: " + code)
                .to("mock:out");
//                .to("file://C:/Users/Gee/IdeaProjects/CatFoodist/barcode_out");
    }
}
