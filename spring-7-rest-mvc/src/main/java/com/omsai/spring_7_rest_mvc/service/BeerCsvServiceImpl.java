package com.omsai.spring_7_rest_mvc.service;

import com.omsai.spring_7_rest_mvc.model.BeerCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BeerCsvServiceImpl implements BeerCsvService {
    @Override
    public List<BeerCSVRecord> convertCsv(File csv) {

        try {
            List<BeerCSVRecord> beerCSVRecords =
                    new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csv))
                            .withType(BeerCSVRecord.class)
                            .build()
                            .parse();
            return beerCSVRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
