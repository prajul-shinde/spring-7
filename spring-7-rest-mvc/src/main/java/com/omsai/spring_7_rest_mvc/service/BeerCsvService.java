package com.omsai.spring_7_rest_mvc.service;

import com.omsai.spring_7_rest_mvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {

    List<BeerCSVRecord> convertCsv(File csv);
}
