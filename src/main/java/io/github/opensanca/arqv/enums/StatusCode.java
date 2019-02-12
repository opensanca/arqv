package io.github.opensanca.arqv.enums;

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
