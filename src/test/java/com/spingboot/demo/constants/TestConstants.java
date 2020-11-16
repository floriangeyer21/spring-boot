package com.spingboot.demo.constants;

public class TestConstants {
    public static final String CORRECT_PATH = "src/test/resources/test_text.txt";
    public static final String CORRECT_PATH_TO_cSV_FILE = "src/test/resources/test_csv.csv";
    public static final String WRONG_PATH = "wrong_path";
    public static final String CORRECT_MOCK_DATA = "id,name,age\n1,Alice,20\n2,Bob,30\n";
    public static final String WRONG_MOCK_DATA =
            "id,name,age\n1,A,falice,dssxv.,/\20\n2,,,faafdBob,30\n";
}
