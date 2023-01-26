package com.tdonuk.sepetim.fetcher.concrete;

import com.tdonuk.constant.Vendor;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.sepetim.fetcher.AbstractDiscountFetcher;
import com.tdonuk.sepetim.fetcher.DiscountFetcher;
import com.tdonuk.sepetim.util.DiscountUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public final class BimDiscountFetcher extends AbstractDiscountFetcher {
    private static final SimpleDateFormat bimDateFormat = new SimpleDateFormat("dd MMMM EEE yyyy", new Locale("tr", "TR"));

    private static DiscountFetcher fetcher;

    public static DiscountFetcher instance() {
        if(Objects.isNull(fetcher)) fetcher = new BimDiscountFetcher();

        return fetcher;
    }

    @Override
    protected Vendor getVendor() {
        return Vendor.BIM;
    }

    @Override
    protected List<DiscountDTO> fetch(Document document) throws Exception {
        Elements posterAreaElements = document.select("div.posterArea div[class='row no-gutters'] div.genelgrup");

        List<DiscountDTO> discounts = new ArrayList<>();

        Iterator<Element> iterator = posterAreaElements.iterator();

        DiscountDTO discount;
        while(iterator.hasNext()) {
            Element e = iterator.next();

            String dateTitle = e.selectFirst("a[class='subTabArea triangle'] span.text").ownText();
            List<String> bannerLinks = e.selectFirst("div.smallArea").select("a img").eachAttr("src").stream().map(link -> getVendor().getRootPath()+link.replaceFirst("k_", "")).collect(Collectors.toList());

            Date date = bimDateFormat.parse(dateTitle + " " + LocalDate.now().getYear());

            discount = new DiscountDTO();

            discount.setBeginDate(date);
            discount.setBannerPageLinks(bannerLinks);
            discount.setVendor(Vendor.BIM);

            DiscountUtils.generateId(discount);

            discounts.add(discount);
        }

        return discounts;
    }
}
