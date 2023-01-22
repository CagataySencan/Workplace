package com.tdonuk.sepetim.task;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.sepetim.fetcher.concrete.BimAktuelFetcher;
import com.tdonuk.sepetim.service.AktuelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Async
@Component
@EnableScheduling
@Slf4j
public class AktuelTask {
    @Autowired
    private AktuelService aktuelService;

    @Autowired
    private BimAktuelFetcher bimFetcher;

    // 0 */4 * * *
    @Scheduled(fixedRate = 6, timeUnit = TimeUnit.HOURS, initialDelay = 0)
    public void updateHist() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("fetching new aktuels");

        // fetch new discounts
        List<AktuelDTO> bimAktuels = (List<AktuelDTO>) bimFetcher.fetchDiscounts();
        log.info("found "+bimAktuels.size()+" aktuels for " + Vendor.BIM.getTitle());

        List<AktuelDTO> fetchedAktuels = new ArrayList<>();
        fetchedAktuels.addAll(bimAktuels);

        log.info("fetched discount size: " + fetchedAktuels.size());

        List<AktuelDTO> hist = aktuelService.getHist();
        for(Vendor vendor : Vendor.values()) {
            long count = hist.stream().filter(a -> a.getVendor().equals(vendor)).count();
            log.info(""+vendor.getTitle().toUpperCase()+"\t"+count);
        }
        log.info("TOTAL HIST SIZE\t"+hist.size());

        fetchedAktuels.forEach(akt -> hist.removeIf(a -> a.isEquals(akt)));

        hist.addAll(fetchedAktuels);

        aktuelService.updateHist(hist);

        stopWatch.stop();

        log.info("finished fetch aktuels in " + stopWatch.getTotalTimeSeconds() + " seconds");
    }

}
