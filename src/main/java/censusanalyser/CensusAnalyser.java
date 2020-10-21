package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csvbuilder.CSVException;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createOpenCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCsvFileIterator(reader, IndiaCensusCSV.class);
            return getCount(censusCSVIterator);
        } catch (IOException | RuntimeException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public int loadIndianStateCode(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createOpenCSVBuilder();
            Iterator<IndiaStateCodeCSV> stateCSVIterator = csvBuilder.getCsvFileIterator(reader, IndiaStateCodeCSV.class);
            return getCount(stateCSVIterator);
        } catch (IOException | RuntimeException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public int loadIndiaCensusDataCommonsCSV(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCsvFileIterator(reader, IndiaCensusCSV.class);
            return getCount(censusCSVIterator);
        } catch (IOException | RuntimeException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEnteries;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Indian States Analyser Problem");
    }
}
