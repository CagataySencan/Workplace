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
    @Scheduled(cron = "0 * */4 * * *")
    public void updateAktuels() throws Exception {
        log.info("updating aktuels..");

        Map<String, AktuelDTO> aktuelMap = new HashMap<>();

        log.info("fetching hist..");
        List<AktuelDTO> hist = aktuelService.getHist();

        for(Vendor vendor : Vendor.values()) {
            long count = hist.stream().filter(a -> a.getVendor().equals(vendor)).count();
            log.info("["+vendor.getTitle().toUpperCase()+"]\t"+count);
        }
        log.info("[TOTAL]\t"+hist.size());

        List<AktuelDTO> outdatedAktuels = new ArrayList<>();
        Iterator<AktuelDTO> iterator = hist.iterator();
        while(iterator.hasNext()) {
            AktuelDTO aktuel = iterator.next();

            LocalDate aktuelDate = LocalDate.ofInstant(aktuel.getDate().toInstant(), ZoneId.systemDefault());

            if(LocalDate.now().minusDays(30).isAfter(aktuelDate)) {
                hist.remove(aktuel);
                outdatedAktuels.add(aktuel);
            }

            aktuelMap.put(aktuel.getVendor().getTitle()+"_"+aktuel.getDate().toInstant(),aktuel);
        }
        log.info(outdatedAktuels.size() + " aktuel will be deleted for being older than 30 days");
        // delete the discount records that is older than 30 days
        aktuelService.delete(outdatedAktuels);

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        log.info("feetching new aktuels..");
        // fetch new discounts
        List<AktuelDTO> bimAktuels = (List<AktuelDTO>) bimFetcher.fetchDiscounts();
        log.info("found "+bimAktuels.size()+" aktuels for " + Vendor.BIM.getTitle());

        // .. Other aktuels

        List<AktuelDTO> totalAktuels = new ArrayList<>();
        totalAktuels.addAll(bimAktuels);

        stopWatch.stop();

        log.info("fetched " + totalAktuels.size() + " aktuels in " + stopWatch.getTotalTimeSeconds() + " seconds");

        addToMap(totalAktuels, aktuelMap);

        log.info("updating aktuel hist for " + aktuelMap.values().size() + " records..");

        hist.clear();

        for(AktuelDTO aktuel : aktuelMap.values()) {
            hist.add(aktuel);
        }

        aktuelService.save(hist);
    }

    private void addToMap(List<AktuelDTO> aktuels, Map<String, AktuelDTO> aktuelMap) {
        for(AktuelDTO aktuel : aktuels) {
            LocalDate date = LocalDate.ofInstant(aktuel.getDate().toInstant(), ZoneId.systemDefault());

            if(! LocalDate.now().minusDays(30).isAfter(date)) aktuelMap.put(aktuel.getVendor().getTitle()+"_"+aktuel.getDate().toInstant(),aktuel);
        }
    }
}
