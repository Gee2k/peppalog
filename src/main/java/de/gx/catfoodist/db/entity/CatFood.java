package de.gx.catfoodist.db.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Entity
@Data
@Slf4j
public class CatFood extends BaseEntity {
   private String ean;
   private String name;

   public CatFood(String ean, String name) {
      this.ean = ean;
      this.name = name;
   }
}
