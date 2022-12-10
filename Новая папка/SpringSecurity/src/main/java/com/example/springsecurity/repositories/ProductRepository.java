package com.example.springsecurity.repositories;

import com.example.springsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Находим продукты по части наименования без учета регистра
    List<Product> findByTitleContainingIgnoreCase(String name);

    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3)", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(String title, float after, float before);

    // Поиск по наименованию, фильтрация по диапазону цены, сортировка по возрастанию цены
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by  price", nativeQuery = true)
    List<Product> findByTitleOrderByPriceAsc(String title, float after, float before);

    // Поиск по наименованию, фильтрация по диапазону цены, сортировка по убыванию цены
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by  price desc ", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDest(String title, float after, float before);

    // Поиск по наименованию,по категории,  фильтрация по диапазону цены, сортировка по возрастанию цены
    @Query(value = "select * from product where category_id=?4 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by  price", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceAsc(String title, float after, float before, int category);

    // Поиск по наименованию,по категории,  фильтрация по диапазону цены, сортировка по убыванию цены
    @Query(value = "select * from product where category_id=?4 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by  price desc ", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float after, float before, int category);

}
