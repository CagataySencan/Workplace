package com.tdonuk.sepetim.fetcher;

import java.util.List;

public interface DiscountFetcher {
    List<?> fetchDiscounts() throws Exception;
}
