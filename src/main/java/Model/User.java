        package Model;

        import jakarta.persistence.*;
        import lombok.Data;
        import jakarta.persistence.Entity;
        import jakarta.persistence.Id;


        @Entity
        @Data
        @Table(name = "users")
        public class User {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)

            private Long id;
            private String email;
            private String username;
            private String password;
            private String role = "Roll_User";
        }
