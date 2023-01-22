package com.tdonuk.constant;

public enum Vendor {
    BIM(VendorType.CHAIN_STORE, "BİM", "https://www.bim.com.tr", "https://www.bim.com.tr/Categories/680/afisler.aspx");

    private VendorType type;
    private String title;
    private String rootPath;
    private String discountsPath;

    Vendor(VendorType type, String title, String rootPath, String discountsPath) {
        this.type = type;
        this.title = title;
        this.rootPath = rootPath;
        this.discountsPath = discountsPath;
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

    public String getDiscountsPath() {
        return discountsPath;
    }

}
