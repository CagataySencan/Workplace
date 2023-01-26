package com.tdonuk.sepetim.fetcher;

import com.tdonuk.constant.Vendor;
import com.tdonuk.sepetim.fetcher.concrete.A101DiscountFetcher;
import com.tdonuk.sepetim.fetcher.concrete.BimDiscountFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class FetcherFactory {
    @Autowired
    private A101DiscountFetcher a101DiscountFetcher;

    @Autowired
    private BimDiscountFetcher bimDiscountFetcher;

    public AbstractDiscountFetcher getDiscountFetcherInstance(Vendor vendor) throws Exception {
        switch (vendor) {
            case BIM -> {
                return bimDiscountFetcher;
            }
            case A101 -> {
                return a101DiscountFetcher;
            }
            default -> {
                log.error("can not find any instance for vendor: " + (Objects.isNull(vendor) ? "null" : vendor.getTitle()));
                return null;
            }
        }
    }
}
