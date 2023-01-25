package com.tdonuk.sepetim.fetcher;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.sepetim.util.UserAgentGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public abstract class AbstractAktuelFetcher implements DiscountFetcher {
    protected abstract Vendor getVendor();
    protected abstract List<AktuelDTO> fetchAktuels(Document document) throws Exception;

    @Override
    public List<?> fetchDiscounts() throws Exception {
        Vendor vendor = getVendor();

        Document document = Jsoup.connect(vendor.getDiscountsPath()).userAgent(UserAgentGenerator.generateRandom()).get();

        List<AktuelDTO> aktuels = fetchAktuels(document);

        return aktuels;
    }
}
