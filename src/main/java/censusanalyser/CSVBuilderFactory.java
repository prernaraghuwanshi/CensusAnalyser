package censusanalyser;

import csvbuilder.CommonsCSVBuilder;
import csvbuilder.ICSVBuilder;
import csvbuilder.OpenCSVBuilder;

public class CSVBuilderFactory {
    public static ICSVBuilder createOpenCSVBuilder() {
        return new OpenCSVBuilder();
    }

    public static ICSVBuilder createCommonsCSVBuilder() {
        return new CommonsCSVBuilder();
    }
}
