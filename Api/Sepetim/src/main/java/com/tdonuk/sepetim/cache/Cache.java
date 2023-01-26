package com.tdonuk.sepetim.cache;

import com.tdonuk.dto.domain.product.DiscountDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Cache {
    public static final Map<String, Object> systemCache = new HashMap<>();


    public static List<DiscountDTO> getDiscountHist() {
        Object cachedHist = Cache.systemCache.get(CacheParams.DISCOUNT_HIST);
        return Objects.isNull(cachedHist) ? null : ((List<DiscountDTO>) cachedHist);
    }

}