package com.tdonuk.sepetim.fetcher.concrete;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.ProductDTO;
import com.tdonuk.sepetim.fetcher.AbstractFetcher;
import com.tdonuk.sepetim.fetcher.Fetcher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public final class BimFetcher extends AbstractFetcher {
    private static Fetcher fetcher;

    public static Fetcher instance() {
        if(Objects.isNull(fetcher)) fetcher = new BimFetcher();

        return fetcher;
    }


    @Override
    protected Vendor getVendor() {
        return Vendor.BIM;
    }

    @Override
    protected List<ProductDTO> fetchProducts(Document document) {
        return null;
    }
}
