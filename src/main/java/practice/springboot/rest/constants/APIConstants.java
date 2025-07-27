package practice.springboot.rest.constants;

/**
 * This class is responsible for maintaining all API related constants used across the application.
 */
public final class APIConstants {
    // Common Constants
    public static final String API_PREFIX = "/api";
    public static final String API_VERSION = "/v1";

    // Base Constants
    public static final String API_BASE_EMPLOYEE = "/employee";

    // Root Path Constants
    public static final String ROOT_PATH_EMPLOYEE = API_PREFIX + API_VERSION + API_BASE_EMPLOYEE;

    // Endpoint Constants
    public static final String PING = "/ping";
    public static final String EMPLOYEE_ADD = "/add";
    public static final String EMPLOYEE_ALL = "/all";
    public static final String EMPLOYEE_BY_ID = "/id/{id}";

    // Request/Response object type
    public static final String APPLICATION_JSON = "application/json";

    // Restrict instantiation
    private APIConstants() {
        throw new IllegalStateException("Utility class should not be instantiated!");
    }

}
