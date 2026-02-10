package banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicação Spring Boot para Sistema Bancário com Design Patterns
 */
@SpringBootApplication
public class DesignPatternsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignPatternsApplication.class, args);
        
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║    SISTEMA BANCÁRIO - DESIGN PATTERNS COM SPRING          ║");
        System.out.println("║                                                          ║");
        System.out.println("║  ✅ CREATIONAL PATTERNS: Factory, Builder, Singleton     ║");
        System.out.println("║  ✅ STRUCTURAL PATTERNS: Decorator, Facade, Adapter      ║");
        System.out.println("║  ✅ BEHAVIORAL PATTERNS: Observer, Strategy, Command     ║");
        System.out.println("║                                                          ║");
        System.out.println("║  REST API iniciada em: http://localhost:8080             ║");
        System.out.println("║  Swagger UI: http://localhost:8080/swagger-ui.html       ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }
}