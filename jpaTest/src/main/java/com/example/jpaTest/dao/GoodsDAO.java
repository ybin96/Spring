package com.example.jpaTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaTest.vo.GoodsVO;

@Repository
public interface GoodsDAO extends JpaRepository<GoodsVO, Integer> {

}
