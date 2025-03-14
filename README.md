# Secure Software Development
## Content
1. Software Secure Development
2. Software Development Life Cycle.
3. Software Development Life Cycle Models
4. Software Secure Development with Java.

## 1. Software Secure Development

Secure Software Development (SSD) is a crucial process for creating software that is resilient to vulnerabilities and threats. It integrates security practices and principles throughout the entire software development lifecycle (SDLC) rather than addressing security as an afterthought. Here's an overview of what it entails:

### Key Principles of Secure Software Development:
1. **Secure Coding Standards**: Following established practices to prevent vulnerabilities like SQL injection or cross-site scripting (XSS).
2. **Threat Modeling**: Identifying potential threats and designing countermeasures early in the design phase.
3. **Regular Security Testing**:
   - **Static Application Security Testing (SAST)**: Analyzing source code for vulnerabilities during development.
   - **Dynamic Application Security Testing (DAST)**: Testing a running application to identify runtime vulnerabilities.
4. **Least Privilege Principle**: Ensuring components and users have only the necessary permissions to perform their tasks.
5. **Input Validation**: Validating and sanitizing user input to prevent exploitation.
6. **Dependency Management**: Monitoring and updating third-party libraries or frameworks to address known vulnerabilities.
7. **Continuous Monitoring and Patch Management**: Keeping software updated to mitigate newly discovered vulnerabilities.

### Benefits of SSD:
- Reduces the cost of fixing issues by addressing vulnerabilities early.
- Protects sensitive data and user privacy.
- Enhances customer trust and complies with regulatory requirements.

Would you like to dive deeper into specific practices, tools, or frameworks for secure software development? Or, maybe you'd like to explore real-world case studies? Let me know!

## 2. Software Development Life Cycle.
**SDLC** (*Software Development Life Cycle*) is the whole process of any new software development in an organization from the first moment when it is a simple idea until it is already implemented in production.

Nowadays, there are many software development models and it is a challenge for the test engineer to adapt to each of them in order to cover all tasks and activities in an optimal way.

Software development is a historically mature engineering process, which has as its basic methodology the software development life cycle (SDLC). This cycle, which has different application models, always consists of five traditional stages: **Requirements Analysis**, **Design**, **Development**, **Testing** and **Maintenance**.
![Figure. Software Development Life Cicle](images/SDLC.jpg)

## 3. Software Development Life Cycle Models
To this day, we have more than 50 recognized SDLC models in use. But None of them is perfect, and each brings its favorable aspects and disadvantages for a specific software development project or a team.

Here, we have listed the top five most popular SDLC models:

### 3.1. Waterfall Model
It is the fundamental model of the software development life cycle. This is a very simple model. The waterfall model is not in practice anymore, but it is the basis for all other SDLC models. Because of its simple structure, the waterfall model is easier to use and provides a tangible output. In the waterfall model, once a phase seems to be completed, it cannot be changed, and due to this less flexible nature, the waterfall model is not in practice anymore. 
![Figure. Waterfall Model](https://miro.medium.com/v2/resize:fit:500/1*tGKCSfTfV8E8t4atqrLE4A.png)

### 3.2. Agile Model
The agile model in SDLC was mainly designed to adapt to changing requests quickly. The main goal of the Agile model is to facilitate quick project completion. The agile model refers to a group of development processes. These processes have some similar characteristics but also possess certain subtle differences among themselves.
![Figure. Agile Model](https://cdn.educba.com/academy/wp-content/uploads/2019/09/Agile-in-SDLC.png)


### 3.3. Iterative Model
In the Iterative model in SDLC, each cycle results in a semi-developed but deployable version; with each cycle, some requirements are added to the software, and the final cycle results in the software with the complete requirement specification.
![Figure. Interactive Model](/images/interactiveModel_SDLC.png) 

### 3.4. Spiral Model
The spiral model in SDLC is one of the most crucial SDLC models that provides support for risk handling. It has various spirals in its diagrammatic representation; the number of spirals depends upon the type of project. Each loop in the spiral structure indicates the Phases of the Spiral model.  
![Figure. ](https://www.softwaretestingmaterial.com/wp-content/uploads/2016/03/Spiral-Model-In-SDLC.png)
### 3.5. V-Shaped Model
The V-shaped model in SDLC is executed in a sequential manner in V-shape. Each stage or phase of this model is integrated with a testing phase. After every development phase, a testing phase is associated with it, and the next phase will start once the previous phase is completed, i.e., development & testing. It is also known as the verification or validation model.
![Figure. V-Shaphed Model](images/ModelV_SDLC.png) 

### 3.6. Big Bang Model
The Big Bang model in SDLC is a term used to describe an informal and unstructured approach to software development, where there is no specific planning, documentation, or well-defined phases.
he Big bang model is an SDLC model that starts from nothing. It is the simplest model in SDLC (Software Development Life Cycle) as it requires almost no planning. However, it requires lots of funds and coding and takes more time. The name big bang model was set after the “Great Big Bang” which led to the development of galaxies, stars, planets, etc. Similarly, this SDLC model combines time, efforts, and resources to build a product. The product is gradually built as the requirements from the customer come, however, the end product might not meet the actual requirements.
![Figure SDLC Big Bang Model](/images/bigbang_SDLC.png)

## 4. Secure Software Development with Java.

### 4.1. SQL Injection in Java
**SQL injection** is a common security vulnerability where an attacker can manipulate SQL queries by inserting malicious input. Here's an example of how this might occur in a Java application:

#### Insecure Example:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username:");
            String username = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
            Statement statement = connection.createStatement();
            
            // Vulnerable SQL query
            String query = "SELECT * FROM users WHERE username = '" + username + "';";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Welcome, " + resultSet.getString("username"));
            } else {
                System.out.println("User not found.");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### Vulnerability Explanation:
- If the user inputs something like: `' OR '1'='1`, the query becomes:
  ```sql
  SELECT * FROM users WHERE username = '' OR '1'='1';
  ```
  This will likely return all rows in the `users` table, allowing unauthorized access.

#### Secure Solution Using Prepared Statements:
To mitigate SQL injection, you should use **prepared statements** with parameterized queries:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SecureSQLExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username:");
            String username = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
            
            // Secure query using PreparedStatement
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Welcome, " + resultSet.getString("username"));
            } else {
                System.out.println("User not found.");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 4.2. Example Cross-Site Scripting (XSS) in Java
**Cross-Site Scripting (XSS)** is a common web security vulnerability that allows attackers to inject malicious scripts into web applications. Here's an example of a potential XSS vulnerability and how it might manifest in a Java-based application:

#### Example Scenario
Consider a Java-based web application that retrieves user input and displays it back on a web page without proper validation or sanitization:

```java
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class XSSExampleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("userInput");

        // Vulnerable: Displaying user input without sanitization
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Welcome!</h1>");
        response.getWriter().println("<p>" + userInput + "</p>"); // XSS vulnerability
        response.getWriter().println("</body></html>");
    }
}
```
#### How the Attack Works
If the user submits a script as input, such as:

```html
<script>alert('XSS Attack!');</script>
```
The application would render it on the page, resulting in the script being executed in the victim's browser. This can lead to stealing cookies, session hijacking, or other malicious activities.

#### How to Mitigate
To prevent XSS vulnerabilities in Java applications, you should:

Sanitize user input: Use libraries like *OWASP Java Encoder* to encode user input before displaying it.

Use input validation: Restrict input to only expected values using validation frameworks.

Implement a **Content Security Policy (CSP):** This can block malicious scripts from executing.

Escape special characters: Avoid directly embedding user input in HTML, JavaScript, or CSS without escaping characters properly.

For example, using OWASP Java Encoder:

```java
import org.owasp.encoder.Encode;

response.getWriter().println("<p>" + Encode.forHtml(userInput) + "</p>");
```

This simple change ensures that any special characters in the user input are safely encoded, preventing them from being treated as executable code.


#### Key Takeaways:
1. Always use `PreparedStatement` or similar mechanisms to avoid directly concatenating user input into SQL queries.
2. Validate and sanitize user input, even when using prepared statements.
3. Employ tools and libraries that help prevent SQL injection, like ORM frameworks (e.g., Hibernate, JPA).

## References:
- [SDLC:: geeksforgeeks.org](https://www.geeksforgeeks.org/software-development-life-cycle-sdlc/)