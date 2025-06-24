package com.stylescoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stylescoder.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
