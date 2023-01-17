package com.tdonuk.constant;

public enum Vendor {
    BIM(VendorType.CHAIN_STORE, "BİM", "https://bim.com.tr", "https://bim.com.tr/aktuel?dummy");

    private VendorType type;
    private String title;
    private String rootPath;
    private String actualPath;

    Vendor(VendorType type, String title, String rootPath, String actualPath) {
        this.type = type;
        this.title = title;
        this.rootPath = rootPath;
        this.actualPath = actualPath;
    }

    public VendorType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getActualPath() {
        return actualPath;
    }

}
