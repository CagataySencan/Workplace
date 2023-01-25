package com.tdonuk.sepetim.cache;

import com.tdonuk.dto.domain.product.AktuelDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Cache {
    public static final Map<String, Object> systemCache = new HashMap<>();


    public static List<AktuelDTO> getAktuelHist() {
        Object cachedHist = Cache.systemCache.get(CacheParams.AKTUEL_HIST);
        return Objects.isNull(cachedHist) ? null : ((List<AktuelDTO>) cachedHist);
    }

}