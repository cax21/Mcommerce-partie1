package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Product findById(int id);

    List<Product> findByPrixGreaterThan(int prixLimit);

    List<Product> findByNomLike(String recherche);

    @Query("SELECT id, nom, prix FROM Product p WHERE p.prix > :prixLimit")
    List<Product>  chercherUnProduitCher(@Param("prixLimit") int prix);

    // methode sans passer par le nommage conventionne de Spring Data JPA
    default List<Product> findAllOrderedByName() {
        return findAll(new Sort(Sort.Direction.ASC, "nom"));
    }

    // methode en passant par le nommage conventionne de Spring Data JPA
    List<Product> findAllByOrderByNomAsc();
}
