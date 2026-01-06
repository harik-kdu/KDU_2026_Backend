import java.util.List;

public class EmployeeDataProcessing {
    public static void main(String[] args) {
        List<Employee> employeeList = Employee.getSampleData();

        //High-Earning Engineers List
        List<Employee> highEarningEmployees = employeeList.stream()
        // employeeList.stream()
            .filter(e -> e.getSalary() > 70000)
            .filter(e -> e.getDepartment().equals("ENGINEERING"))
            .toList();
            // .forEach(e -> System.out.println(e + "\n"));

        System.out.println(highEarningEmployees);

        //Name Report
        List<String> nameReportList = employeeList.stream()
            .map(e -> e.getName().toUpperCase())
            .toList();

        System.out.println(nameReportList);

        //total Annual Salary Budget
        double companysAnnualSalary = employeeList.stream()
            .mapToDouble(Employee::getSalary)
            .sum();

        System.out.println("Company's Annual Salary : "+companysAnnualSalary);
    }
}
