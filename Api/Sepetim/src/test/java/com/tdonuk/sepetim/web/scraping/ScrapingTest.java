package com.tdonuk.sepetim.web.scraping;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.sepetim.util.UserAgentGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ScrapingTest {

    @Test
    public void parseBimActual() throws Exception {
        String root = Vendor.BIM.getRootPath();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM EEE yyyy", new Locale("tr", "TR"));

        Document document = Jsoup.connect(Vendor.BIM.getDiscountsPath()).userAgent(UserAgentGenerator.generateRandom()).get();

        Elements posterAreaElements = document.select("div.posterArea div[class='row no-gutters'] div.genelgrup");

        List<DiscountDTO> discounts = new ArrayList<>();

        posterAreaElements.forEach(e -> {
            String dateTitle = e.selectFirst("a[class='subTabArea triangle'] span.text").ownText();
            List<String> bannerLinks = e.selectFirst("div.smallArea").select("a img").eachAttr("src").stream().map(link -> root+link.replaceFirst("k_", "")).collect(Collectors.toList());


            Date date;
            try {
                date = dateFormat.parse(dateTitle + " " +LocalDate.now().getYear());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            DiscountDTO discount = new DiscountDTO();
            discount.setBeginDate(date);
            discount.setBannerPageLinks(bannerLinks);
            discount.setVendor(Vendor.BIM);

            discounts.add(discount);
        });

        Assertions.assertTrue(!discounts.isEmpty());

        System.out.println(discounts.toString());

    }
}
