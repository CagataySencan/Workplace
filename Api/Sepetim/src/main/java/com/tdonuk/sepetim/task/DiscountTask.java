package com.tdonuk.sepetim.task;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.sepetim.cache.Cache;
import com.tdonuk.sepetim.cache.CacheParams;
import com.tdonuk.sepetim.fetcher.DiscountFetcher;
import com.tdonuk.sepetim.fetcher.FetcherFactory;
import com.tdonuk.sepetim.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Async
@Component
@EnableScheduling
@Slf4j
public class DiscountTask {
    @Autowired
    private DiscountService discountService;

    @Autowired
    private FetcherFactory fetcherFactory;

    // 0 */4 * * *
    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.HOURS, initialDelay = 0)
    public synchronized void updateDiscountHist() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<DiscountDTO> fetchedDiscounts = new ArrayList<>();

        log.info("updating discount hist..");
        for(Vendor vendor : Vendor.values()) {
            log.info("fetching " + vendor.name());

            DiscountFetcher fetcher = fetcherFactory.getDiscountFetcherInstance(vendor);

            if(Objects.isNull(fetcher)) continue;

            List<DiscountDTO> discounts = (List<DiscountDTO>) fetcher.fetchDiscounts();

            log.info("fetched " + discounts.size() + " discounts for " + vendor.name());

            fetchedDiscounts.addAll(discounts);
        }

        List<DiscountDTO> hist = discountService.getHist();

        fetchedDiscounts.forEach(akt -> hist.removeIf(a -> a.isEquals(akt)));

        hist.addAll(fetchedDiscounts);

        List<DiscountDTO> result = discountService.updateHist(hist);

        Cache.systemCache.put(CacheParams.DISCOUNT_HIST, result); // refresh the cache

        stopWatch.stop();

        log.info("finished fetch discounts in " + stopWatch.getTotalTimeSeconds() + " seconds");
    }

}
