package com.tdonuk.sepetim.task;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.sepetim.cache.Cache;
import com.tdonuk.sepetim.cache.CacheParams;
import com.tdonuk.sepetim.fetcher.FetcherFactory;
import com.tdonuk.sepetim.service.AktuelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Async
@Component
@EnableScheduling
@Slf4j
public class AktuelTask {
    @Autowired
    private AktuelService aktuelService;

    @Autowired
    private FetcherFactory fetcherFactory;

    // 0 */4 * * *
    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.HOURS, initialDelay = 0)
    public synchronized void updateAktuelHist() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<AktuelDTO> fetchedAktuels = new ArrayList<>();

        log.info("updating aktuel hist..");
        for(Vendor vendor : Vendor.values()) {
            log.info("fetching " + vendor.name());

            List<AktuelDTO> aktuels = (List<AktuelDTO>) fetcherFactory.getAktuelFetcherInstance(vendor).fetchDiscounts();

            log.info("fetched " + aktuels.size() + " aktuels for " + vendor.name());

            fetchedAktuels.addAll(aktuels);
        }

        List<AktuelDTO> hist = aktuelService.getHist();

        fetchedAktuels.forEach(akt -> hist.removeIf(a -> a.isEquals(akt)));

        hist.addAll(fetchedAktuels);

        List<AktuelDTO> result = aktuelService.updateHist(hist);

        Cache.systemCache.put(CacheParams.AKTUEL_HIST, result); // refresh the cache

        stopWatch.stop();

        log.info("finished fetch aktuels in " + stopWatch.getTotalTimeSeconds() + " seconds");
    }

}
