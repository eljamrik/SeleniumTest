package TestMaven.Report;

public class TestCase {
	private String testCaseName;
    private String testCaseDrescription;
    private String TestCaseResultStatus;
    private String testCaseExecutionTime;

    public TestCase() {
        this.testCaseName = "";
        this.testCaseDrescription = "";
        this.TestCaseResultStatus = "";
        this.testCaseExecutionTime = "";
    }

    public TestCase(String testcasename, String testResultstatus) {
        this.setTestCaseName(testcasename);
        this.setTestCaseResultStatus(testResultstatus);
    }

    public TestCase(TestCase testCase) {
        this.setTestCaseName(testCase.getTestCaseName());
        this.setTestCaseResultStatus(testCase.getTestCaseResultStatus());
    }

    public String getTestCaseResultStatus() {
        return this.TestCaseResultStatus;
    }

    public void setTestCaseResultStatus(String testCaseResultStatus) {
        this.TestCaseResultStatus = testCaseResultStatus;
    }

    public String getTestCaseName() {
        return this.testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestCaseDrescription() {
        return this.testCaseDrescription;
    }

    public void setTestCaseDrescription(String testCaseDrescription) {
        this.testCaseDrescription = testCaseDrescription;
    }

    public String getTestCaseExecutionTime() {
        return this.testCaseExecutionTime;
    }

    public void setTestCaseExecutionTime(String testCaseExecutionTime) {
        this.testCaseExecutionTime = testCaseExecutionTime;
    }
}
