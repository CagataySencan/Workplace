package com.tdonuk.sepetim.fetcher.concrete;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.sepetim.fetcher.AbstractAktuelFetcher;
import com.tdonuk.sepetim.util.AktuelUtils;
import com.tdonuk.sepetim.util.UserAgentGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class A101AktuelFetcher extends AbstractAktuelFetcher {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("tr", "TR"));

    @Override
    protected Vendor getVendor() {
        return Vendor.A101;
    }

    @Override
    protected List<AktuelDTO> fetchAktuels(Document document) throws Exception {
        Document thisWeek = document;
        Document nextWeek = Jsoup.connect(getVendor().getRootPath()+"/aldin-aldin-gelecek-hafta-brosuru").userAgent(UserAgentGenerator.generateRandom()).get();

        AktuelDTO thisWeekAktuel = new AktuelDTO();

        Date thisWeekDate = extractDate(thisWeek);
        List<String> thisWeekPageLinks = extractPageLinks(thisWeek);

        thisWeekAktuel.setVendor(getVendor());
        thisWeekAktuel.setDate(thisWeekDate);
        thisWeekAktuel.setBannerPageLinks(thisWeekPageLinks);
        AktuelUtils.generateId(thisWeekAktuel);

        AktuelDTO nextWeekAktuel = new AktuelDTO();

        Date nextWeekDate = extractDate(nextWeek);
        List<String> nextWeekPageLinks = extractPageLinks(nextWeek);

        nextWeekAktuel.setVendor(getVendor());
        nextWeekAktuel.setDate(nextWeekDate);
        nextWeekAktuel.setBannerPageLinks(nextWeekPageLinks);
        AktuelUtils.generateId(nextWeekAktuel);

        return List.of(thisWeekAktuel, nextWeekAktuel);
    }

    private List<String> extractPageLinks(Document document) {
        Elements slides = document.select("div.content img");

        return slides.eachAttr("src").stream().filter(link -> link.contains("/cms/")).collect(Collectors.toList());
    }

    private Date extractDate(Document document) throws Exception {
        Element dateTitleEl = document.select("ul[class='brochures-actions-list'] li:eq(1) div.dates").first();

        String dateTitle = dateTitleEl.attr("data-date");

        dateTitle = dateTitle.replaceAll("'tan İtibaren", "") + " " + LocalDate.now().getYear();

        Date date = dateFormatter.parse(dateTitle);

        return date;
    }
}
