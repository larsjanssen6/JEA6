package domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "User")
public class User implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Long id;

   @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
   private String first_name;

   private String last_name;

   public Long getId() {
      return id;
   }

   public void setLast_name(String last_name) {
      this.last_name = last_name;
   }

   public String getLast_name() {
      return last_name;
   }

   public void setFirst_name(String first_name) {
      this.first_name = first_name;
   }

   public String getFirst_name() {
      return first_name;
   }

   @Override
   public String toString() {
      return "User{" + "id=" + id + ", first_name='" + first_name + '\'' + ", last_name='" + last_name + '\'' + '}';
   }
}