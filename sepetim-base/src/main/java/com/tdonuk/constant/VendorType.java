package com.tdonuk.constant;

public enum VendorType {
    CHAIN_STORE("Zincir marketler"), ECOMMERCE("E-Ticaret siteleri");

    private String label;

    VendorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
