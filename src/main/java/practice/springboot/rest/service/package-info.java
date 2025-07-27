/**
 * Contains all business logic that are used in the application.
 * Separate services should be created corresponding to each controller.
 * <ul>
 *     <li>EmployeeService to handle all business logic that are exposed through EmployeeController</li>
 *     <li>OrganizationService to handle all business logic that are exposed through OrganizationController</li>
 * </ul>
 * and so on.
 * Depending on the complexity of the business logic, you can create multiple service implementations too.
 * Say, multiple third parties have different rulesets to perform a set of business logic.
 * In such cases, similar implementations can be created:
 * <ul>
 *     <li>EmployeeServiceWip for handling core business logic of Wip employees</li>
 *     <li>EmployeeServiceInf for handling core business logic of Inf employees</li>
 * </ul>
 * and so on. And their common interface can be EmployeeServiceI.
 */
package practice.springboot.rest.service;