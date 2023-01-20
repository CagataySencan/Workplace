package com.tdonuk.sepetim.fetcher;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.ProductDTO;
import com.tdonuk.sepetim.util.UserAgentGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public abstract class AbstractFetcher implements Fetcher {
    protected abstract Vendor getVendor();
    protected abstract List<ProductDTO> fetchProducts(Document document);


    @Override
    public List<ProductDTO> fetchActual() throws IOException {
        Vendor vendor = getVendor();

        Document document = Jsoup.connect(vendor.getActualPath()).userAgent(UserAgentGenerator.generateRandom()).get();

        List<ProductDTO> products = fetchProducts(document);

        return products;
    }
}
