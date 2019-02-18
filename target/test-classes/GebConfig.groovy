import org.openqa.selenium.firefox.FirefoxDriver

driver = { new FirefoxDriver() }

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true