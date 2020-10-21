package censusanalyser;

import csvbuilder.ICSVBuilder;
import csvbuilder.OpenCSVBuilder;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}
