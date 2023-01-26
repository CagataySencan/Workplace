package com.tdonuk.sepetim.fetcher.concrete;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.sepetim.fetcher.AbstractDiscountFetcher;
import org.jsoup.nodes.Document;

import java.util.List;

public class MigrosDiscountFetcher extends AbstractDiscountFetcher {
    @Override
    protected Vendor getVendor() {
        return Vendor.MIG;
    }

    @Override
    protected List<DiscountDTO> fetch(Document document) throws Exception {
        return null;
    }

}
