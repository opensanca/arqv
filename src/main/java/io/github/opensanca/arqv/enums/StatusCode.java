package io.github.opensanca.arqv.utils;

public enum StatusCode {
    CREATED("created"), OK("ok"), NO_CONTENT("noContent");

    private String value;

    StatusCode(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
