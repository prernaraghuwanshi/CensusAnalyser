package censusanalyser;

import com.google.gson.Gson;
import csvbuilder.CSVException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String WRONG_DELIMITER_PATH = "./src/test/resources/IndiaStateCensusDataWrongDelimiter.csv";
    private static final String WRONG_HEADER_PATH = "./src/test/resources/IndiaStateCensusDataWrongHeader.csv";
    private static final String INDIA_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_INDIA_STATE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_INDIA_STATE_CSV_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCode.txt";
    private static final String WRONG_INDIA_STATE_CSV_DELIMITER_PATH = "./src/test/resources/IndiaStateCodeWrongDelimiter.csv";
    private static final String WRONG_INDIA_STATE_CSV_HEADER_PATH = "./src/test/resources/IndiaStateCodeWrongHeader.csv";


    @Test
    public void givenIndianCensusCSVFile_whenCorrectAndUsingCommonsCSV_returnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusDataCommonsCSV(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenCorrect_returnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongFilePath_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongFileType_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongDelimiter_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongHeader_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_whenCorrect_shouldReturnExactCount() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfStateCode = censusAnalyser.loadIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfStateCode);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongFilePath_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongFileType_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongDelimiter_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongHeader_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnState_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            int censusCSVLength = censusCSV.length;
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
            Assert.assertEquals("West Bengal", censusCSV[censusCSVLength - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCSV_whenSortedOnStateCode_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
            String sortedStateData = censusAnalyser.getStateCodeWiseSortedStateData();
            IndiaStateCodeCSV[] stateCSV = new Gson().fromJson(sortedStateData, IndiaStateCodeCSV[].class);
            int stateCSVLength = stateCSV.length;
            Assert.assertEquals("AD", stateCSV[0].stateCode);
            Assert.assertEquals("WB", stateCSV[stateCSVLength - 1].stateCode);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnPopulation_shouldReturnSortedResultInDescendingOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            int censusCSVLength = censusCSV.length;
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
            Assert.assertEquals("Sikkim", censusCSV[censusCSVLength - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }
}
