package com.tdonuk.sepetim.fetcher.concrete;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.sepetim.fetcher.AbstractDiscountFetcher;
import com.tdonuk.sepetim.util.DiscountUtils;
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
public class A101DiscountFetcher extends AbstractDiscountFetcher {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("tr", "TR"));

    @Override
    protected Vendor getVendor() {
        return Vendor.A101;
    }

    @Override
    protected List<DiscountDTO> fetch(Document document) throws Exception {
        Document thisWeek = document;
        Document nextWeek = Jsoup.connect(getVendor().getRootPath()+"/aldin-aldin-gelecek-hafta-brosuru/").userAgent(UserAgentGenerator.generateRandom()).get();

        DiscountDTO thisWeekDiscount = new DiscountDTO();

        Date thisWeekDate = extractDate(thisWeek, "Bu Hafta");
        List<String> thisWeekPageLinks = extractPageLinks(thisWeek);

        thisWeekDiscount.setVendor(getVendor());
        thisWeekDiscount.setBeginDate(thisWeekDate);
        thisWeekDiscount.setBannerPageLinks(thisWeekPageLinks);
        DiscountUtils.generateId(thisWeekDiscount);

        DiscountDTO nextWeekDiscount = new DiscountDTO();

        Date nextWeekDate = extractDate(nextWeek, "Gelecek Hafta");
        List<String> nextWeekPageLinks = extractPageLinks(nextWeek);

        nextWeekDiscount.setVendor(getVendor());
        nextWeekDiscount.setBeginDate(nextWeekDate);
        nextWeekDiscount.setBannerPageLinks(nextWeekPageLinks);
        DiscountUtils.generateId(nextWeekDiscount);

        return List.of(thisWeekDiscount, nextWeekDiscount);
    }

    private List<String> extractPageLinks(Document document) {
        Elements slides = document.select("div.content img");

        return slides.eachAttr("src").stream().filter(link -> link.contains("/cms/")).collect(Collectors.toList());
    }

    private Date extractDate(Document document, String search) throws Exception {
        Element dateTitleEl = document.select("ul[class='brochures-actions-list'] li a[title*='"+search+"'] div.dates").first(); // burayı düzelt, her zaman ilkini alor

        String dateTitle = dateTitleEl.attr("data-date");

        dateTitle = dateTitle.replaceAll("'tan İtibaren", "") + " " + LocalDate.now().getYear();

        Date date = dateFormatter.parse(dateTitle);

        return date;
    }
}
