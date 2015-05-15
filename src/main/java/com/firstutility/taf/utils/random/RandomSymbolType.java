package com.firstutility.taf.utils.random;

public enum RandomSymbolType {

    ALPHA         ( "ALPHA" ),
    ALPHA_NUMERIC ( "ALPHA_NUMERIC" ),
    NUMERIC       ( "NUMERIC" ),
    SPECIAL       ( "SPECIAL" );

    private String value;

    private RandomSymbolType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RandomSymbolType getByString(String value){
        return valueOf(value.toUpperCase());

    }
}

