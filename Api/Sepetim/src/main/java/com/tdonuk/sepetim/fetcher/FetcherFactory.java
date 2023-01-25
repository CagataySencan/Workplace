package com.tdonuk.sepetim.fetcher;

import com.tdonuk.constant.Vendor;
import com.tdonuk.exception.SystemException;
import com.tdonuk.sepetim.fetcher.concrete.A101AktuelFetcher;
import com.tdonuk.sepetim.fetcher.concrete.BimAktuelFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FetcherFactory {
    @Autowired
    private A101AktuelFetcher a101AktuelFetcher;

    @Autowired
    private BimAktuelFetcher bimAktuelFetcher;

    public AbstractAktuelFetcher getAktuelFetcherInstance(Vendor vendor) throws Exception {
        switch (vendor) {
            case BIM -> {
                return bimAktuelFetcher;
            }
            case A101 -> {
                return a101AktuelFetcher;
            }
            default -> throw new SystemException("Instance not supported", "Given vendor is not supported by any of the fetcher instances. vendor: " + (Objects.isNull(vendor) ? "null" : vendor.name()));
        }
    }
}
