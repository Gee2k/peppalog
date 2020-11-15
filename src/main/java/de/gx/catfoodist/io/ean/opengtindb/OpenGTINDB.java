package de.gx.catfoodist.io.ean.opengtindb;

public enum OpenGTINDB {

    ERROR("error"),
    ASIS("asin"),
    NAME("name"),
    DETAIL_NAME("detailname"),
    VENDOR("vendor"),
    MAIN_CAT("maincat"),
    SUB_CAT("subcat"),
    MAIN_CAT_NUM("maincatnum"),
    SUB_CAT_NUM("subcatnum"),
    CONTENTS("contents"),
    PACK("pack"),
    ORIGIN("origin"),
    DESCRIPTION("descr"),
    NAME_EN("name_en"),
    DETAIL_NAME_EN("detailname_en"),
    DESCRIPTION_EN("descr_en"),
    VALIDATED("validated");

    private String value;

    private OpenGTINDB(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OpenGTINDB fromValue(String value) {
        for (OpenGTINDB k : OpenGTINDB.values()) {
            if (k.value.equalsIgnoreCase(value)) {
                return k;
            }
        }
        return null;
    }
}
