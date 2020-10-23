package censusanalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csvbuilder.CSVException;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaStateCodeCSV> stateCSVList = null;

    // Welcome message
    public void welcome(String[] args) {
        System.out.println("Welcome to Indian States Analyser Problem");
    }

    // Load IndiaCensusCSV file into censusCSVList
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        checkFileType(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createOpenCSVBuilder();
            censusCSVList = csvBuilder.getCsvFileList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM);
        } catch (CSVException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    // Load IndiaStateCodeCSV file into stateCSVList
    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        checkFileType(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createOpenCSVBuilder();
            stateCSVList = csvBuilder.getCsvFileList(reader, IndiaStateCodeCSV.class);
            return stateCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM);
        } catch (CSVException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    // Load into list using Commons CSV
    public int loadIndiaCensusDataCommonsCSV(String csvFilePath) throws CensusAnalyserException {
        checkFileType(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
            List<IndiaCensusCSV> censusCSVCommonsList = csvBuilder.getCsvFileList(reader, IndiaCensusCSV.class);
            return censusCSVCommonsList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM);
        } catch (CSVException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    // Get count using iterable
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEnteries;
    }

    // Check if file is CSV or not
    private void checkFileType(String csvFilePath) throws CensusAnalyserException {
        Pattern patternForCSV = Pattern.compile(".+[.csv]");
        if (!patternForCSV.matcher(csvFilePath).matches())
            throw new CensusAnalyserException("Incorrect file type",
                    CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
    }

    // Sort IndiaCensusCSV state wise and return output in JSON format
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0)
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_DATA);
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        return sortedStateCensusJson;
    }

    // Sorting function
    private void sort(Comparator<IndiaCensusCSV> censusComparator) {
        int listSize = censusCSVList.size();
        IntStream.range(0, listSize - 1).flatMap(i -> IntStream.range(1, listSize - i)).forEach(j -> {
            IndiaCensusCSV census1 = censusCSVList.get(j - 1);
            IndiaCensusCSV census2 = censusCSVList.get(j);
            if (censusComparator.compare(census1, census2) > 0) {
                censusCSVList.set(j - 1, census2);
                censusCSVList.set(j, census1);
            }
        });
    }
}
