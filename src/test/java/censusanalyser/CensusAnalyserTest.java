package censusanalyser;

import com.google.gson.Gson;
import csvbuilder.CSVException;
import org.junit.Assert;
import org.junit.Before;
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

    CensusAnalyser censusAnalyser;

    @Before
    public void initialize() {
        censusAnalyser = new CensusAnalyser();
    }

    @Test
    public void givenIndianCensusCSVFile_whenCorrectAndUsingCommonsCSV_returnsCorrectRecords() {
        try {
            int numOfRecords = censusAnalyser.loadIndiaCensusDataCommonsCSV(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenCorrect_returnsCorrectRecords() {
        try {
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongFilePath_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongFileType_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongDelimiter_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_withWrongHeader_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_whenCorrect_shouldReturnExactCount() {
        try {
            int numberOfStateCode = censusAnalyser.loadIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfStateCode);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongFilePath_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongFileType_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongDelimiter_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_withWrongHeader_shouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndianStateCode(WRONG_INDIA_STATE_CSV_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnState_shouldReturnSortedResult() {
        try {
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
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            int censusCSVLength = censusCSV.length;
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
            Assert.assertEquals("Sikkim", censusCSV[censusCSVLength - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnPopulationDensity_shouldReturnSortedResultInDescendingOrder() {
        try {
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            int censusCSVLength = censusCSV.length;
            Assert.assertEquals("Bihar", censusCSV[0].state);
            Assert.assertEquals("Arunachal Pradesh", censusCSV[censusCSVLength - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnArea_shouldReturnSortedResultInDescendingOrder() {
        try {
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            int censusCSVLength = censusCSV.length;
            Assert.assertEquals("Rajasthan", censusCSV[0].state);
            Assert.assertEquals("Goa", censusCSV[censusCSVLength - 1].state);
        } catch (CensusAnalyserException e) {
        }
    }
}
