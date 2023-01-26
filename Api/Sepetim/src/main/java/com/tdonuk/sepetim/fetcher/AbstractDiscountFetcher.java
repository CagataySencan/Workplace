package com.tdonuk.sepetim.fetcher;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.sepetim.util.UserAgentGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public abstract class AbstractDiscountFetcher implements DiscountFetcher {
    protected abstract Vendor getVendor();
    protected abstract List<DiscountDTO> fetch(Document document) throws Exception;

    @Override
    public List<?> fetchDiscounts() throws Exception {
        Vendor vendor = getVendor();

        Document document = Jsoup.connect(vendor.getDiscountsPath()).userAgent(UserAgentGenerator.generateRandom()).get();

        List<DiscountDTO> discounts = fetch(document);

        return discounts;
    }
}
