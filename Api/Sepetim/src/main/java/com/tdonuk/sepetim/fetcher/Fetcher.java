package com.tdonuk.sepetim.fetcher;

import com.tdonuk.dto.domain.product.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface Fetcher {
    List<ProductDTO> fetchActual() throws IOException;
}
