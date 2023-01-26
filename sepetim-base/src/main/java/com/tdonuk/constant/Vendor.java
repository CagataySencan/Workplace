package com.tdonuk.constant;

public enum Vendor {
    BIM(VendorType.CHAIN_STORE, "BİM", "https://www.bim.com.tr", "https://www.bim.com.tr/Categories/680/afisler.aspx"),
    A101(VendorType.CHAIN_STORE, "A101", "https://www.a101.com.tr", "https://www.a101.com.tr/aldin-aldin-bu-hafta-brosuru/"),
    MIG(VendorType.CHAIN_STORE, "Migros", "https://www.migros.com.tr", "https://www.migros.com.tr/cumadan-cumaya-indirimleri-l-b7a1739");

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
