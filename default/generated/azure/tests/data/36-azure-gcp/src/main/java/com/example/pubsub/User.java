
package com.example.pubsub;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Example POJO to demonstrate Spring Cloud GCP Spring Data Firestore operations. */
@Document(collectionName = "users")
public class User {

  @DocumentId String name;

  int age;

  List<Pet> pets;

  User() {
    pets = new ArrayList<>();
  }

  public User(String name, int age, List<Pet> pets) {
    this.name = name;
    this.age = age;
    this.pets = pets;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return age == user.age && Objects.equals(name, user.name) && Objects.equals(pets, user.pets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, pets);
  }

  @Override
  public String toString() {
    return "User{" + "name='" + name + '\'' + ", age=" + age + ", pets=" + pets + '}';
  }
}